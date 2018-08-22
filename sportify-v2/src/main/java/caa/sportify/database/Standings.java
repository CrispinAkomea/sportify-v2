package caa.sportify.database;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.vendor.App;
import com.vendor.contract.QueryBuilder;
import com.vendor.database.DB;
import com.vendor.utility.DBUtil;
import com.vendor.utility.ModelUtil;

import caa.sportify.contract.TableUpdater;
import caa.sportify.model.League;
import caa.sportify.model.Standing;
import caa.sportify.model.Statistic;
import caa.sportify.model.Team;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Crispin A.
 *
 */
public class Standings implements TableUpdater {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private final String table = "Standings";
	private static Standings singleInstance;
	private DoubleProperty progress;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	private Standings() {
		progress = new SimpleDoubleProperty(0);
	}

	/**************************************************************************
	 * 
	 * Class method that restricts the instantiation of class and ensures that only
	 * one instance of the class exists.
	 * 
	 **************************************************************************/

	public static Standings getInstance() {
		if (singleInstance == null) {
			synchronized (Standings.class) {
				if (singleInstance == null)
					singleInstance = new Standings();
			}
		}
		return singleInstance;
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public DoubleProperty getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress.set(progress);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.matchup.contract.TableUpdater#updateAll()
	 */
	@Override
	public void updateAll() throws SQLException, InterruptedException {
		DB.table(table).truncate();
		League[] leagues = ModelUtil.toModels((String) DB.table("Leagues").get(), League[].class);
		int totalTeams = ModelUtil.toModels((String) DB.table("Teams").get(), Team[].class).length;
		double iteration = 0;
		for (League league : leagues) {
			for (Team team : league.getTeams()) {
				update(team);
				progress.set(++iteration / totalTeams);
				Thread.sleep(20);
			}
			insertPostionAndMovement(league);
		}
	}

	/**
	 * 
	 * Updates the standings of the team that is passed as an argument.
	 * 
	 * @param team
	 *            - the Team object to be updated
	 * @throws SQLException
	 */
	private void update(Object team) throws SQLException {
		int plays, wins, draws, losses, GF, GA, points;
		plays = wins = draws = losses = GF = GA = points = 0;
		String alias = ((Team) team).getAlias();
		Statistic[] statistics = ModelUtil.toModels(
				(String) DB.table("Statistics").where("HomeTeam", alias).or("AwayTeam", alias).get(),
				Statistic[].class);

		for (Statistic statistic : statistics) {
			if (statistic.getHomeTeam().equals(alias)) {
				if (statistic.getFTR() == 'H') {
					wins++;
					points += 3;
				} else if (statistic.getFTR() == 'D') {
					draws++;
					points++;
				} else
					losses++;
				GF += statistic.getFTHG();
				GA += statistic.getFTAG();
			} else {
				if (statistic.getFTR() == 'A') {
					wins++;
					points += 3;
				} else if (statistic.getFTR() == 'D') {
					draws++;
					points++;
				} else
					losses++;
				GF += statistic.getFTAG();
				GA += statistic.getFTHG();
			}
			plays++;
		}
		insert(((Team) team).getDivision(), ((Team) team).getName(), new Integer[] { plays, wins, draws, losses, GF, GA,
				(GF - GA), points, (points - getLastMatchPoints(statistics, alias)) });
	}

	/**
	 * 
	 * Calculates the points a team had before its most current fixture by
	 * subtracting the points obtained by a team in its last fixture.
	 * 
	 * @param statistics
	 *            - array of Statistic objects of the team being updated
	 * @param alias
	 *            - name of the team in the statistic records
	 * @return points obtained in last fixture (most current fixture)
	 */
	private int getLastMatchPoints(Statistic[] statistics, String alias) {
		int points = 0;
		Statistic lastMatch = statistics[(statistics.length - 1)];
		if (lastMatch.getHomeTeam().equals(alias)) {
			if (lastMatch.getFTR() == 'H') {
				points += 3;
			} else if (lastMatch.getFTR() == 'D') {
				points++;
			}
		} else {
			if (lastMatch.getFTR() == 'A') {
				points += 3;
			} else if (lastMatch.getFTR() == 'D') {
				points++;
			}
		}
		return points;
	}

	/**
	 * 
	 * Inserts the movement for each team standing. Done as update queries. The
	 * movement field is left blank on the initial insert.
	 * 
	 * @param league
	 *            - name of the league for which standings are being updated
	 * @throws SQLException
	 */
	private void insertPostionAndMovement(League league) throws SQLException {
		List<Standing> currentStandings = league.getStandings();
		List<Standing> pastStandings = league.getStandings();
		Collections.sort(currentStandings, Standing.compareP());
		Collections.sort(pastStandings, Standing.comparePP());

		HashMap<String, Object> update = null;
		for (String team : league.getTeamNames()) {
			int index = 1;
			int currentPosition, pastPosition;
			currentPosition = pastPosition = 0;
			update = new HashMap<String, Object>();
			for (Standing standing : currentStandings) {
				if (standing.getName().equals(team))
					currentPosition = index;
				index++;
			}
			index = 1;
			for (Standing standing : pastStandings) {
				if (standing.getName().equals(team))
					pastPosition = index;
				index++;
			}
			update.put("position", currentPosition);
			update.put("movement", (pastPosition - currentPosition));
			DB.table(table).where("name", team).update(update);
		}
	}

	/**
	 * 
	 * Insert query to that standing table. Used in the update method.
	 * 
	 * @param division
	 *            - the division that the team is in
	 * @param name
	 *            - the name of the team
	 * @param records
	 *            - array of ints that represent each field of a Standing object
	 * @throws SQLException
	 */
	private void insert(String division, String name, Integer[] records) throws SQLException {
		DB.raw("INSERT INTO " + table + " (" + QueryBuilder.getValues(DBUtil.getColumnNames(App.getDatabase(), table))
				+ ")" + " VALUES ('" + division + "', '" + name + "', NULL, " + QueryBuilder.getValues(records)
				+ ", NULL" + ")").perform();
	}

}

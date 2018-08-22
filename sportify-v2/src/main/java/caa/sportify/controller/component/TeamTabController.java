package caa.sportify.controller.component;

import java.sql.SQLException;

import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import caa.sportify.controller.CenterPaneController;
import caa.sportify.model.Standing;
import caa.sportify.model.Statistic;
import caa.sportify.model.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * @author Crispin A.
 *
 */
public class TeamTabController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private TeamInformationController teamInformationController;
	@FXML
	private LeagueTableViewController leagueTableViewController;
	@FXML
	private TeamTableViewController teamTableViewController;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private CenterPaneController centerPaneController;
	private StringProperty teamProperty = new SimpleStringProperty();
	private final int TAB_INDEX = 1;

	/**
	 * 
	 * Updates the entire teamTab and makes teamTab the selected tab.
	 * 
	 */
	private ChangeListener<String> teamPropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			try {
				updateTeamInfromation(newValue);
				updateLeagueTableView(newValue);
				updateTeamTableView(newValue);
				centerPaneController.setSelectedTab(TAB_INDEX);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		teamProperty.addListener(teamPropertyChangeListener);
		teamTableViewController.bindTeamProperty(teamProperty);
	}

	/**************************************************************************
	 * 
	 * Controller Injection Methods
	 * 
	 **************************************************************************/

	public void injectCenterTabContoller(CenterPaneController centerPaneController) {
		this.centerPaneController = centerPaneController;
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public TeamInformationController getTeamInformationController() {
		return teamInformationController;
	}

	public LeagueTableViewController getLeagueTableViewController() {
		return leagueTableViewController;
	}

	public TeamTableViewController getTeamTableViewController() {
		return teamTableViewController;
	}

	public String getTeamProperty() {
		return teamProperty.getValue();
	}

	public void setTeamProperty(String team) {
		this.teamProperty.set(team);
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * TableView Methods *
	 **************************************************************************/

	/**
	 * 
	 * Attempts to update the leagueTableViewController TableView<Standing>
	 * leagueTableView with five standing objects. Gets the standing object of the
	 * team passed at an argument, retrieves its position in the league and uses
	 * that int to get the upper 2 and lower 2 standing objects.
	 * 
	 * For example; if a team is in the 5th position. This method will create a
	 * table with 3rd, 4th, 5th, 6th and 7th positions included.
	 * 
	 * If the team is in the 1st position. Only 3 standing objects will be
	 * retrieved.
	 * 
	 * @param teamName
	 *            - name of the team
	 * @throws SQLException
	 */
	private void updateLeagueTableView(String teamName) throws SQLException {
		ObservableList<Standing> standings = FXCollections.observableArrayList();
		Team team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
		Standing standing = team.getStanding();
		int position = standing.getPosition();
		Object[] positions = new Object[] { position - 2, position - 1, position, position + 1, position + 2 };
		Standing[] fiveStanding = ModelUtil.toModels((String) DB.table("Standings").whereIn("position", positions)
				.andWhere("division", team.getDivision()).get(), Standing[].class);
		for (Standing item : fiveStanding)
			standings.add(item);
		FXCollections.sort(standings, Standing.compareP());
		leagueTableViewController.setItems(standings, standings.size(), 1);
		leagueTableViewController.getLeagueTableView().getSelectionModel().select(standing);

	}

	/**
	 * 
	 * Updates the teamTableViewController TableView<Statistic> teamTableView with
	 * statistical data on the team. Past fixtures records.
	 * 
	 * @param teamName
	 *            - name of the team
	 * @throws SQLException
	 */
	private void updateTeamTableView(String teamName) throws SQLException {
		ObservableList<Statistic> statistics = FXCollections.observableArrayList();
		Team team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
		for (Statistic statistic : team.getStatistics())
			statistics.add(statistic);
		teamTableViewController.setItems(statistics, statistics.size(), 1);
	}

	/**
	 * 
	 * Updates the information of the team. that is the badge, name, position,
	 * record and form.
	 * 
	 * @param teamName
	 *            - name of the team
	 * @throws SQLException
	 */
	private void updateTeamInfromation(String teamName) throws SQLException {
		teamInformationController.updateTeamInformation(teamName);
	}
}

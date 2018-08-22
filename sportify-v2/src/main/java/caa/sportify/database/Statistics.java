package caa.sportify.database;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.vendor.App;
import com.vendor.contract.QueryBuilder;
import com.vendor.database.DB;
import com.vendor.utility.DBUtil;
import com.vendor.utility.ModelUtil;

import caa.sportify.contract.TableUpdater;
import caa.sportify.model.League;
import caa.sportify.utility.CsvUtil;
import caa.sportify.utility.HttpUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Crispin A.
 *
 */
public class Statistics implements TableUpdater {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private final String table = "Statistics";
	private static Statistics singleInstance;
	private DoubleProperty progress;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	private Statistics() {
		progress = new SimpleDoubleProperty(0);
	}

	/**************************************************************************
	 * 
	 * Class method that restricts the instantiation of class and ensures that only
	 * one instance of the class exists.
	 * 
	 **************************************************************************/

	public static Statistics getInstance() {
		if (singleInstance == null) {
			synchronized (Statistics.class) {
				if (singleInstance == null)
					singleInstance = new Statistics();
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
	public void updateAll() throws SQLException, MalformedURLException, IOException, InterruptedException {
		DB.table(table).truncate();
		League[] leagues = ModelUtil.toModels((String) DB.table("Leagues").get(), League[].class);
		double iteration = 0;
		for (League league : leagues) {
			update(league);
			progress.set(++iteration / leagues.length);
			Thread.sleep(100);
		}
	}

	/**
	 * 
	 * Updates the statistics of the teams in the specific league that is passed as
	 * an argument.
	 * 
	 * @param league
	 *            - the League object to be updated
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void update(Object league) throws MalformedURLException, IOException, SQLException {
		String division = ((League) league).getDivision();
		String url = App.getStatisticsUrl() + division + ".csv";
		String file = App.getResourcePathTemp() + division + ".csv";
		HttpUtil.downloadFile(url, file);
		removeIncompleteStatistics();
		DB.raw("INSERT INTO " + table + " (" + QueryBuilder.getValues(DBUtil.getColumnNames(App.getDatabase(), table))
				+ ")" + " VALUES" + CsvUtil.csvToQuery(file, 11, false)).perform();
		FileUtils.forceDelete(new File(file));
	}

	/**
	 * 
	 * Removes the statistics that are incomplete from the table.
	 * 
	 * @throws SQLException
	 */
	private void removeIncompleteStatistics() throws SQLException {
		DB.table(table).where("FTHG", "").orWhere("FTAG", "").orWhere("FTR", "").delete();
	}

}

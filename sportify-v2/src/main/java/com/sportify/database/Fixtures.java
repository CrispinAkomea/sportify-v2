package com.sportify.database;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;

import com.sportify.contract.TableUpdater;
import com.sportify.model.Fixture;
import com.sportify.utility.CsvUtil;
import com.sportify.utility.HttpUtil;
import com.vendor.App;
import com.vendor.contract.QueryBuilder;
import com.vendor.database.DB;
import com.vendor.utility.DBUtil;
import com.vendor.utility.ModelUtil;

/**
 * @author Crispin A.
 *
 */
public class Fixtures implements TableUpdater {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private final String table = "Fixtures";
	private static Fixtures singleInstance;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	private Fixtures() {

	}

	/**************************************************************************
	 * 
	 * Class method that restricts the instantiation of class and ensures that only
	 * one instance of the class exists.
	 * 
	 **************************************************************************/

	public static Fixtures getInstance() {
		if (singleInstance == null) {
			synchronized (Fixtures.class) {
				if (singleInstance == null)
					singleInstance = new Fixtures();
			}
		}
		return singleInstance;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.matchup.contract.TableUpdater#updateAll()
	 */
	@Override
	public void updateAll() throws MalformedURLException, IOException, SQLException {
		DB.table(table).truncate();
		String url = App.getFixturesUrl();
		String file = App.getResourcePathTemp() + "fixtures.csv";
		HttpUtil.downloadFile(url, file);
		DB.raw("INSERT INTO " + table + " ("
				+ QueryBuilder.getValues(DBUtil.getColumnNames(App.getDatabase(), table)) + ")" + " VALUES"
				+ CsvUtil.csvToQuery(file, 7, true) + ";").perform();
		FileUtils.forceDelete(new File(file));
	}

	/**
	 * 
	 * Updates the fixture table if the records are not up-to-date. Criteria for
	 * up-to-date being at least 1 entry from today (NOW).
	 * 
	 * @throws MalformedURLException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void startUpUpdate() throws MalformedURLException, SQLException, ParseException, IOException {
		if (!Fixtures.getInstance().fixturesUpToDate())
			Fixtures.getInstance().updateAll();
	}

	/**
	 * 
	 * Checks if the fixture table is up-to-date. Criteria for up-to-date being at
	 * least 1 entry from today (NOW).
	 * 
	 * @return true if the date of the fixture is today; false otherwise
	 * @throws MalformedURLException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException
	 */
	private boolean fixturesUpToDate() throws SQLException, ParseException {
		Fixture[] fixtures = ModelUtil.toModels((String) DB.table(table).get(), Fixture[].class);
		for (Fixture fixture : fixtures) {
			if (fixture.isToday())
				return true;
		}
		return false;
	}

}

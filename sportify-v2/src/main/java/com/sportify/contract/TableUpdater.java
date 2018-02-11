package com.sportify.contract;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

/**
 * 
 * The <code>TableUpdater</code> interface specifies the behaviour of classes
 * that update a table.
 * 
 * @author Crispin A.
 *
 */
public interface TableUpdater {

	/**
	 * 
	 * Updates the table correctly. Truncates the table and inserts new records.
	 * 
	 * @throws SQLException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	void updateAll() throws SQLException, MalformedURLException, IOException, InterruptedException;

}

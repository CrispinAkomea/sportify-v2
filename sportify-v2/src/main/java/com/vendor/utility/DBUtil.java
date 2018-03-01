package com.vendor.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtil {

	private static final Logger logger = LogManager.getLogger(DBUtil.class);

	/**
	 * 
	 * Creates a connection to the database and returns the connection.
	 * 
	 * @param database
	 *            - database URL
	 * @return a connection to the database
	 * @throws SQLException
	 */
	public static Connection createConnection(String database) throws SQLException {
		try {
			return DriverManager.getConnection(database);
		} catch (SQLException e) {
			logger.error("DB_LOG - Error during connection to database.\n" + e + "\n");
			throw new SQLException();
		}
	}

	/**
	 * 
	 * Closes the connection to the database. Doesn't if null.
	 * 
	 * @param connection
	 *            - the connection to be closed
	 * @throws SQLException
	 */
	public static void closeConnection(Connection connection) throws SQLException {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("DB_LOG - Error during closure of connection object.\n" + e + "\n");
				throw new SQLException();
			}
	}

	/**
	 * 
	 * Returns an array of column names from the table excluding the id column.
	 * 
	 * @param database
	 *            - database URL containing table
	 * @param table
	 *            - table to be checked
	 * @return - array of column names from the table excluding the id column
	 * @throws SQLException
	 */
	public static String[] getColumnNames(String database, String table) throws SQLException {
		String[] columns = null;
		Connection connection = DBUtil.createConnection(database);
		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM " + table + " WHERE id = '1';")) {
			int count = result.getMetaData().getColumnCount();
			columns = new String[count];
			for (int i = 0; i < count; i++)
				columns[i] = result.getMetaData().getColumnName(i + 1);
		}
		DBUtil.closeConnection(connection);
		return ArrayUtils.removeElement(columns, "id");
	}

}

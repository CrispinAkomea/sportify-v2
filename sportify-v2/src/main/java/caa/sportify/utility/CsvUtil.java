package caa.sportify.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import com.opencsv.CSVReader;

/**
 * 
 * Utility class that can be utilized in the conversion of CSV files to queries.
 *
 * @author Crispin A.
 *
 */
public class CsvUtil {

	/**
	 * 
	 * Returns a query string representation of the VALUES from a CSV file to be
	 * inserted into a table. Take for example the query "INSERT INTO table_name
	 * (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);". The
	 * "(value1, value2, value3, ...)" part will be returned from this method. Same
	 * for the query "INSERT INTO table_name VALUES (value1, value2, value3, ...);".
	 * 
	 * @param cvsFile
	 *            - the CSV file from which values are to be gotten
	 * @param columns
	 *            - the number of columns you want from the CSV file; columns are
	 *            counted from left to right hence can only leave out columns
	 *            towards the right
	 * @param skipEmpty
	 *            - if true, columns with no values will be skipped i.e 2,,4 there
	 *            is no value between 2 and 4 and this will be skipped; false
	 *            otherwise
	 * @return query - string representation of the VALUES to be inserted into a
	 *         table
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String csvToQuery(String cvsFile, int columns, boolean skipEmpty)
			throws FileNotFoundException, IOException, SQLException {
		String query = "";
		String values = "";
		try (CSVReader reader = new CSVReader(new FileReader(cvsFile))) {
			String[] nextLine;
			reader.readNext();
			if (skipEmpty) {
				while ((nextLine = reader.readNext()) != null)
					values += "(" + queryValuesSkipEmpty(nextLine, columns) + "),";
			} else {
				while ((nextLine = reader.readNext()) != null)
					values += "(" + queryValues(nextLine, columns) + "),";
			}
			query = values.substring(0, values.length() - 1);
		}
		return query;
	}

	/**
	 * 
	 * Returns a query string representation of the VALUES from a single line of
	 * from the CSV file to be inserted into a table.
	 * 
	 * NOTE: Includes columns with no values
	 * 
	 * @param nextLine
	 *            - single line from the CSV file
	 * @param columns
	 *            - the number of columns you want from the CSV file; columns are
	 *            counted from left to right hence can only leave out columns
	 *            towards the right
	 * @return string representing the values to be inserted
	 */
	private static String queryValues(String[] nextLine, int columns) {
		String values = "";
		for (int i = 0; i < columns; i++)
			values += "'" + nextLine[i].replace("\'", "''") + "', ";
		return values.substring(0, values.length() - 2);
	}

	/**
	 * 
	 * Returns a query string representation of the VALUES from a single line of
	 * from the CSV file to be inserted into a table.
	 * 
	 * Useful when you know some values for columns will be empty and therefore skip
	 * the column entirely. The column names should also have bee skipped correctly.
	 * 
	 * NOTE: Excludes columns with no values
	 * 
	 * @param nextLine
	 *            - single line from the CSV file
	 * @param columns
	 *            - the number of columns you want from the CSV file; columns are
	 *            counted from left to right hence can only leave out columns
	 *            towards the right
	 * @return string representing the values to be inserted
	 */
	private static String queryValuesSkipEmpty(String[] nextLine, int columns) {
		String values = "";
		for (int i = 0; i < columns; i++) {
			if (!nextLine[i].isEmpty())
				values += "'" + nextLine[i].replace("\'", "''") + "', ";
			else
				columns++;
		}
		return values.substring(0, values.length() - 2);
	}

}

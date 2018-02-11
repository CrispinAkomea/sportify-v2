package com.vendor.contract;

import java.util.Map;

/**
 * 
 * The <code>QueryBuilder</code> interface specifies the behaviour of a query
 * builder class. Users of this interface can build queries that are ready for
 * execution without the verboseness that normally goes along with making
 * queries.
 * <p>
 * All interface methods return a reference to instance of class that implements
 * the <code>QueryBuilder</code> interface. This enables the use of the dot
 * operator after every method call making the use of multiple query filters
 * easy.
 * <p>
 * The easiest way to implement this interface is to have a <code>String</code>
 * or <code>StringBuilder</code> object. Use this object to build the query with
 * each method by concatenating/appending to it.
 * <p>
 * The <code>static</code> methods assist in query building process.
 * 
 * @author Crispin A.
 *
 */
public interface QueryBuilder {

	/**
	 * 
	 * Adds the desired column to be selected to the select query.
	 * 
	 * @param selection
	 *            - the column to be selected
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder select(String selection);

	/**
	 * 
	 * Adds the desired columns to be selected to the select query.
	 * 
	 * @param selections
	 *            - the columns to be selected
	 * 
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder select(String[] selections);

	/**
	 * 
	 * Adds the where clause to the query. Used with select, update and delete
	 * statements.
	 * 
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder where(String column, String value);

	/**
	 * 
	 * Adds the where clause with custom operator to the query. Used with select,
	 * update and delete statements.
	 * 
	 * @param column
	 *            - the column from which you want the value to fit a certain
	 *            criteria
	 * @param operator
	 *            - operator to be used in the where clause
	 * @param value
	 *            - the value you want to test your criteria with
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder where(String column, String operator, Object value);

	/**
	 * 
	 * Adds the where in clause to the query. Used with select, update and delete
	 * statements.
	 * 
	 * @param column
	 *            - the column from which you want the value to fit a certain
	 *            criteria
	 * @param values
	 *            - values for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder whereIn(String column, Object[] values);

	/**
	 * 
	 * Add one more condition to the where clause with the AND operator.
	 * 
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder and(String column, String value);

	/**
	 * 
	 * Adds one more condition with custom operator to the where clause with the AND
	 * operator.
	 * 
	 * @param column
	 *            - the column from which you want the value to fit a certain
	 *            criteria
	 * @param operator
	 *            - operator to be used in the where clause
	 * @param value
	 *            - the value you want to test your criteria with
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder and(String column, String operator, Object value);

	/**
	 * 
	 * Add one more condition to the where clause with the OR operator.
	 * 
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder or(String column, String value);

	/**
	 * 
	 * Adds one more condition with custom operator to the where clause with the OR
	 * operator.
	 * 
	 * @param column
	 *            - the column from which you want the value to fit a certain
	 *            criteria
	 * @param operator
	 *            - operator to be used in the where clause
	 * @param value
	 *            - the value you want to test your criteria with
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder or(String column, String operator, Object value);

	/**
	 * 
	 * ANDS a where clause with an additional separate where clause. Used after a
	 * where clause.
	 * 
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder andWhere(String column, String value);

	/**
	 * 
	 * ANDS a where clause with an additional separate where clause with custom
	 * operator. Used after a where clause.
	 *
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder andWhere(String column, String operator, Object value);

	/**
	 * 
	 * ANDS a where clause with an additional separate where in clause. Used after a
	 * where clause.
	 * 
	 * @param column
	 *            - the column from which you want the value to fit a certain
	 *            criteria
	 * @param values
	 *            - values for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder andWhereIn(String column, Object[] values);

	/**
	 * 
	 * ORS a where clause with an additional separate where clause. Used after a
	 * where clause.
	 * 
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder orWhere(String column, String value);

	/**
	 * 
	 * ORS a where clause with an additional separate where clause with custom
	 * operator. Used after a where clause.
	 *
	 * @param column
	 *            - the column from which you want an exact value
	 * @param value
	 *            - value for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder orWhere(String column, String operator, Object value);

	/**
	 * 
	 * ORS a where clause with an additional separate where in clause with custom
	 * operator. Used after a where clause.
	 * 
	 * @param column
	 *            - the column from which you want the value to fit a certain
	 *            criteria
	 * @param values
	 *            - values for the column
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder orWhereIn(String column, Object[] values);

	/**
	 * 
	 * Sorts by column in ascending or descending order.
	 * 
	 * @param column
	 *            - column to be sorted
	 * @param order
	 *            - ascending order or descending order
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder orderBy(String column, String order);

	/**
	 * 
	 * Sorts by columns in ascending or descending order.
	 * 
	 * @param columns
	 *            - columns to be sorted
	 * @param order
	 *            - ascending order or descending order
	 * @return a reference to instance of class that implements the interface
	 */
	QueryBuilder orderBy(String[] columns, String order);

	/**
	 * 
	 * Creates a string of the array of values passed as a parameter. It places each
	 * value between single quote characters and separates then with commas.
	 * <p>
	 * Returns the resulting string.
	 * 
	 * @param values
	 *            - array of values to be placed in quotes and separated with commas
	 * @return string of column names between single quote character a string
	 *         separated by a comma
	 */
	static String getValues(Object[] values) {
		String query = "";
		for (int i = 0; i < values.length; i++)
			query += "'" + values[i] + "', ";
		return query.substring(0, query.length() - 2);
	}

	/**
	 * 
	 * Creates a string of the form (column1, column2, column3, ...) VALUES (value1,
	 * value2, value3, ...) using the map parameter.
	 * <p>
	 * The columns are the map keys, the values are the map values.
	 * 
	 * @param pairs
	 *            - column names with corresponding value to be inserted in the
	 *            column
	 * @return string in the form of (column1, column2, column3, ...) VALUES
	 *         (value1, value2, value3, ...). Columns are map keys, values are map
	 *         values.
	 */
	static String getValuePairs(Map<String, Object> pairs) {
		String query = "";
		query = "(" + getValues(pairs.keySet().toArray(new String[0])) + ") VALUES ("
				+ getValues(pairs.values().toArray(new String[0])) + ")";
		return query;
	}

	/**
	 * 
	 * Creates a string of the form column1 = value1, column2 = value2, ... using
	 * the map parameter.
	 * <p>
	 * The columns are the map keys, the values are the map values.
	 * 
	 * @param pairs
	 *            - column names with corresponding value to be inserted in the
	 *            column
	 * @return string of the form column1 = value1, column2 = value2, ... using the
	 *         map parameter. The columns are the map keys, the values are the map
	 *         values.
	 */
	static String getValueSets(Map<String, Object> pairs) {
		String query = "";
		for (String key : pairs.keySet())
			query += " " + key + " = '" + pairs.get(key) + "',";
		return query.substring(0, query.length() - 1);
	}

}

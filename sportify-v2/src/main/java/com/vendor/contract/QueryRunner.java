package com.vendor.contract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 
 * The <code>QueryRunner</code> interface specifies the behaviour of classes
 * that deal with the execution of queries. Contains methods that execute
 * select, insert, update and delete queries.
 * <p>
 * Users of this interface should have access to the query in a
 * <code>String</code> or <code>StringBuilder</code> object. Some methods
 * execute the query as it is while others make changes to the query and then
 * execute it.
 * <p>
 * Use the <code>QueryRunner</code> interface in conjunction with the
 * <code>QueryBuilder</code> interface.
 * 
 * @author Crispin A.
 *
 */
public interface QueryRunner {

	/**
	 * 
	 * Executes the select query and returns the results.
	 * 
	 * @return the results of the query
	 * @throws SQLException
	 * @throws IOException
	 */
	Object get() throws SQLException;

	/**
	 * 
	 * Executes the select query and returns the first record in the results.
	 * 
	 * @return the first record in the results
	 * @throws SQLException
	 * @throws IOException
	 */
	Object first() throws SQLException;

	/**
	 * 
	 * Executes the select query and returns a certain number of records.
	 * 
	 * @param number
	 *            - number of records to return
	 * @return the results of the query
	 * @throws SQLException
	 * @throws IOException
	 */
	Object take(int number) throws SQLException;

	/**
	 * 
	 * Executes the insert, update, or delete query.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	void perform() throws SQLException;

	/**
	 * 
	 * Executes the insert query.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	void insert(Map<String, Object> values) throws SQLException;

	/**
	 * 
	 * Executes the update query.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	void update(Map<String, Object> values) throws SQLException;

	/**
	 * 
	 * Executes the delete query.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	void delete() throws SQLException;

	/**
	 * 
	 * Executes the truncate query.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	void truncate() throws SQLException;

}

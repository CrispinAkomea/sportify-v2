package com.vendor.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.vendor.App;
import com.vendor.contract.QueryBuilder;
import com.vendor.contract.QueryRunner;
import com.vendor.utility.DBUtil;
import com.vendor.utility.GsonBuilderUtil;
import com.vendor.utility.GsonUtil;

/**
 * 
 * Simple SQLite implementation of the QueryBuilder and QueryRunner interfaces.
 * 
 * @author Crispin A.
 *
 */
public class DB implements QueryBuilder, QueryRunner {

	private String table;
	private StringBuilder query;

	private DB() {
		query = new StringBuilder();
	}

	private DB(String table) {
		this.table = table;
		query = new StringBuilder("SELECT * FROM " + table);
	}

	public static DB table(String table) {
		return new DB(table);
	}

	public static DB raw(String query) {
		DB db = new DB();
		db.query.append(query);
		return db;
	}

	public void setQuery(String query) {
		this.query.setLength(0);
		this.query.append(query);
	}

	public String getQuery() {
		return query.toString();
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	@Override
	public DB select(String selection) {
		query.setLength(0);
		query.append("SELECT " + selection + " FROM " + table);
		return this;
	}

	@Override
	public DB select(String[] selections) {
		query.setLength(0);
		query.append("SELECT " + QueryBuilder.getValues(selections) + " FROM " + table);
		return this;
	}

	@Override
	public DB where(String column, String value) {
		query.append(" WHERE (" + column + " = '" + value.replace("\'", "''") + "')");
		return this;
	}

	@Override
	public DB where(String column, String operator, Object value) {
		query.append(" WHERE (" + column + " " + operator + " '" + value + "')");
		return this;
	}

	public DB whereIn(String column, Object[] values) {
		query.append(" WHERE " + column + " IN (" + QueryBuilder.getValues(values) + ")");
		return this;
	}

	@Override
	public DB and(String column, String value) {
		query.setLength(query.length() - 1);
		query.append(" AND " + column + " = '" + value.replace("\'", "''") + "')");
		return this;
	}

	@Override
	public DB and(String column, String operator, Object value) {
		query.setLength(query.length() - 1);
		query.append(" AND " + column + " " + operator + " '" + value + "')");
		return this;
	}

	@Override
	public DB or(String column, String value) {
		query.setLength(query.length() - 1);
		query.append(" OR " + column + " = '" + value.replace("\'", "''") + "')");
		return this;
	}

	@Override
	public DB or(String column, String operator, Object value) {
		query.setLength(query.length() - 1);
		query.append(" OR " + column + " " + operator + " '" + value + "')");
		return this;
	}

	@Override
	public DB andWhere(String column, String value) {
		query.append(" AND (" + column + " = '" + value.replace("\'", "''") + "')");
		return this;
	}

	@Override
	public DB andWhere(String column, String operator, Object value) {
		query.append(" AND (" + column + " " + operator + " '" + value + "')");
		return this;
	}

	@Override
	public QueryBuilder andWhereIn(String column, Object[] values) {
		query.append(" AND (" + column + " IN (" + QueryBuilder.getValues(values) + "))");
		return this;
	}

	@Override
	public DB orWhere(String column, String value) {
		query.append(" OR (" + column + " = '" + value.replace("\'", "''") + "')");
		return this;
	}

	@Override
	public DB orWhere(String column, String operator, Object value) {
		query.append(" OR (" + column + " " + operator + " '" + value + "')");
		return this;
	}

	@Override
	public QueryBuilder orWhereIn(String column, Object[] values) {
		query.append(" OR (" + column + " IN (" + QueryBuilder.getValues(values) + "))");
		return this;
	}

	@Override
	public DB orderBy(String column, String order) {
		query.append(" ORDER BY " + column + " " + order.toUpperCase());
		return this;
	}

	@Override
	public DB orderBy(String[] columns, String order) {
		String columnValues = QueryBuilder.getValues(columns);
		query.append(" ORDER BY " + columnValues + " " + order.toUpperCase());
		return this;
	}

	@Override
	public Object get() throws SQLException {
		query.append(";");
		return execute(query.toString());
	}

	@Override
	public Object first() throws SQLException {
		query.append(" LIMIT 1;");
		return GsonUtil.removeFromArray(execute(query.toString()));
	}

	@Override
	public Object take(int number) throws SQLException {
		query.append(" LIMIT " + number + ";");
		if (number == 1)
			return GsonUtil.removeFromArray(execute(query.toString()));
		else
			return execute(query.toString());
	}

	@Override
	public void perform() throws SQLException {
		query.append(";");
		update(query.toString());
	}

	@Override
	public void insert(Map<String, Object> pairs) throws SQLException {
		query.setLength(0);
		query.append("INSERT INTO " + table + " " + QueryBuilder.getValuePairs(pairs) + ";");
		update(query.toString());
	}

	@Override
	public void update(Map<String, Object> pairs) throws SQLException {
		query.delete(0, query.indexOf("WHERE"));
		query.insert(0, "UPDATE " + table + " SET" + QueryBuilder.getValueSets(pairs) + " ").append(";");
		update(query.toString());
	}

	@Override
	public void delete() throws SQLException {
		query.delete(0, query.indexOf("WHERE"));
		query.insert(0, "DELETE FROM " + table + " ").append(";");
		update(query.toString());
	}

	@Override
	public void truncate() throws SQLException {
		query.setLength(0);
		query.append("DELETE FROM " + table + "; VACUUM;");
		update(query.toString());
	}

	private String execute(String query) throws SQLException {
		Connection connection = DBUtil.createConnection(App.getDatabase());
		List<Map<String, Object>> listOfMaps = new org.apache.commons.dbutils.QueryRunner().query(connection, query,
				new MapListHandler());
		DBUtil.closeConnection(connection);
		return GsonBuilderUtil.createGson().toJson(listOfMaps);
	}

	private void update(String query) throws SQLException {
		Connection connection = DBUtil.createConnection(App.getDatabase());
		new org.apache.commons.dbutils.QueryRunner().update(connection, query);
		DBUtil.closeConnection(connection);
	}

}

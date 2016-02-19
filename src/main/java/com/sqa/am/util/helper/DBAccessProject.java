/**
 * File Name: DBAccessProject.java<br>
 * Musick, Annette<br>
 * Java Boot Camp Exercise<br>
 * Instructor<br>
 * Created: Jan 16, 2016
 */
package com.sqa.am.util.helper;

import java.sql.*;
import java.util.*;

/**
 * DBAccessProject
 *
 * @author Musick, Annette
 * @version 1.0.0
 * @since 1.0
 */
public class DBAccessProject {

	/**
	 * @param myResults
	 */
	public static void displayResults(ResultSet myResults) {
		try {
			ResultSetMetaData rsmd = myResults.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (myResults.next()) {
				for (int i = 0; i < columnCount; i++) {
					System.out.print(rsmd.getColumnLabel(i + 1) + ":" + myResults.getString(i + 1) + " ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param myResults
	 */
	public static void displayResults(ResultSet myResults, String... columns) {
		try {
			while (myResults.next()) {
				for (int i = 0; i < columns.length; i++) {
					System.out.print(columns[i] + ":" + myResults.getString(columns[i]) + " ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param myResults
	 */
	public static Object[][] getResultsObject(ResultSet myResults) {
		ArrayList<Object> data = new ArrayList<>();
		try {
			ResultSetMetaData rsmd = myResults.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (myResults.next()) {
				Object[] obj = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					System.out.print(rsmd.getColumnLabel(i + 1) + ":" + myResults.getString(i + 1) + " ");
					obj[i] = myResults.getString(i + 1);
				}
				System.out.println();
				data.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Object[][] objData = new Object[data.size()][];
		for (int i = 0; i < objData.length; i++) {
			Object[] row = (Object[]) data.get(i);
			objData[i] = row;
		}
		return objData;
	}

	String myDriver;

	String myUrl;

	String password;

	String username;

	/**
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 */
	public DBAccessProject(String myDriver, String myUrl, String username, String password) {
		this.myDriver = myDriver;
		this.myUrl = myUrl;
		this.username = username;
		this.password = password;
	}

	public boolean deleteRecords(String selectString) {
		// TODO Create implementation for Driver Management, Connecting and
		// deleting records.
		return false;
	}

	public ResultSet getRecords(String selectString) {
		ResultSet resultSet = null;
		try {
			Class.forName(this.myDriver);
			Connection conn = DriverManager.getConnection(this.myUrl, this.username, this.password);
			String query = selectString;
			Statement st = conn.createStatement();
			resultSet = st.executeQuery(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public boolean insertRecords(String selectString) {
		// Put code in Try Catch to catch exceptions
		try {
			// Get driver
			Class.forName(this.myDriver);
			// Create connection object from the DriverManger's createConnection
			// method given the 3 fields of the instance of DBAccessProject
			Connection conn = DriverManager.getConnection(this.myUrl, this.username, this.password);
			// Create Statement object from the createStatment
			Statement statement = conn.createStatement();
			// Perform the executeUpdate method of the Statement instance to
			// prepare for execution
			statement.executeUpdate(selectString);
			// If AutoCommit is set to true, the conn.commit is not needed. If
			// you wanted to take advantage of auto commit, you would need to
			// set the autoCommit to false
			conn.setAutoCommit(false);
			// This line is not needed if autoCommit is set to true
			conn.commit();
		} catch (ClassNotFoundException e) {
			// Print stack
			e.printStackTrace();
			// Return false to signify that execution did not work
			return false;
		} catch (SQLException e) {
			// Print stack
			e.printStackTrace();
			// Return false to signify that execution did not work
			return false;
		}
		return true;
	}

	public boolean updateRecords(String selectString) {
		// TODO Create implementation for Driver Management, Connecting and
		// updating records.
		return false;
	}
}

/**
 * File Name: Data.java<br>
 * Nepton, Jean-francois<br>
 * Java Boot Camp Exercise<br>
 * Instructor<br>
 * Created: Jan 15, 2016
 */
package com.sqa.am.util.helper;

import java.sql.ResultSet;

/**
 * Data
 *
 * @author Musick, Annette
 * @version 1.0.0
 * @since 1.0
 */
public class Data {

	public static void display2DMatrix(Object[][] data) {
		int totalRows = data.length; // Total test cases
		int totalCols = data[0].length; // should be i, not 0
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data[0].length; i++) {
			sb.append("--------");
		}
		System.out.println(sb);
		for (int row = 0; row < totalRows; row++) {
			// Before Displaying Row
			System.out.print("| ");
			for (int column = 0; column < totalCols; column++) {
				// Before Displaying Matrix Column
				System.out.print(data[row][column] + "\t");
				// After Displaying Matrix row
			}
			// After Displaying Matrix Row
			System.out.println("|");
			// System.out.println(segment);
		}
		System.out.println(sb);
		// After Displaying Matrix
	}

	public static Object[][] getDatabaseData(String myDriver, String myUrl, String username, String password,
			String tableName) {
		DBAccessProject project = new DBAccessProject(myDriver, myUrl, username, password);
		ResultSet myResults = project.getRecords("SELECT * FROM `" + tableName + "`");
		// Static called from an instance
		return project.getResultsObject(myResults);
	}

	public static Object[][] getExceleData(String fileName, int colOffset, int rowOffset) {
		return null;
	}
}

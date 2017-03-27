package com.dba.ora.dh;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

//import oracle.jdbc.*;  // for OracleResultSet

public class TestFormatPrintResultSet {

	public static void main(String[] args) {
		try {

			// ResultSet resultSet = new DhOracle().queryResultSet("username",
			// "password", "hostOrScanName", "port",
			// "serviceNameOrSID", "query");

			ResultSet resultSet = new DhOracle().queryResultSet("gm",
					"g$4gggg", "mycluster-scan", "1522", "myclusterDBname",
					// "select name,value from v$parameter");
					// "select name from v$database");
					"alter session set current_schema=gm");
					//"insert into t1 values (1,1)");
					//"select * from t1");
					//"update t1 set c1=2");

			System.out.println("Got resultSet1");

			ResultSetMetaData rsMetadata = resultSet.getMetaData();
			int colCount = rsMetadata.getColumnCount();
			System.out.println("Column Count - " + colCount);				

			// For non-queries (like insert/update column-count of result set will be zero
			if (colCount > 0) {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					// System.out.println ("outer loop");
					for (int i = 1; i <= colCount; i++) {
						// System.out.println ("inner loop");
						System.out.println(rsMetadata.getColumnLabel(i) + " : "
								+ resultSet.getString(i));
					}
				}
				resultSet.close();
				System.out.println("Closed resultSet1");
			}
			
			System.out.println("Completed run");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERR - Error while executing query");
		}

	}

}

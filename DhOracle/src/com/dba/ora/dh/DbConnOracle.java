package com.dba.ora.dh;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnOracle {

	Connection connection;

	public Connection getDBConnJDBCThin(String username, String password,
			String hostOrScanName, String port, String serviceNameOrSID)
			throws Exception {

		/*
		System.out.println("Making jdbc thin connection to " + "user: "
				+ username + " password: " + password + " hostOrScanName: "
				+ hostOrScanName + " port: " + port + " serviceNameOrSID: "
				+ serviceNameOrSID);
		*/

		// Try connecting with scan listener and service name
		try {

			System.out.println("INFO - Trying to connect using scan and service Name");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// URL to use when using scan name
			// jdbc:oracle:thin:username/password@//myhost:scanPort/myservicename

			String jdbcURL = "jdbc:oracle:thin:" + username + "/" + password
					+ "@//" + hostOrScanName + ":" + port + "/"
					+ serviceNameOrSID;
			connection = DriverManager.getConnection(jdbcURL);

			System.out.println("INFO - Connected using Scan and Service Name");
			
			return connection;

		} catch (Exception e1) {

			try { // Now, try connecting with non-scan listener and SID

				System.out.println("INFO - Trying to connect using SID Name");

				Class.forName("oracle.jdbc.driver.OracleDriver");

				// URL to use when using host and SID
				// jdbc:oracle:thin:@localhost:nonScanPort:SID","username","password"
				String jdbcURL = "jdbc:oracle:thin:@" + hostOrScanName + ":"
						+ port + ":" + serviceNameOrSID + "," + username + ","
						+ password;
				connection = DriverManager.getConnection(jdbcURL);

				System.out.println("INFO - Connected using SID Name");
				
				return connection;
			} 
			catch (Exception e2) {
				//e2.printStackTrace();
				throw e2;
			}
		}
	} // getOraDBConnJDBCThin method

}

// TBD - Method - Get connection using wallet

// TBD - Method - Get connection using OCI driver

// TBD - Method - Get connection using OCI driver and wallet

// TBD - Method - Close connection

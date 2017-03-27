package com.dba.ora.dh;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

public class DbConnOraclePooled {

	OracleDataSource ds;
	Connection connection;

	public Connection getDBConnJDBCThin(String username, String password,
			String hostOrScanName, String port, String serviceNameOrSID)
			throws SQLException {

		ds = new OracleDataSource();

		try {
			// original
			// This is URL that works with service-name
			// jdbc:oracle:thin:scott/tiger@//myhost:1521/myservicename
			String jdbcURL = "jdbc:oracle:thin:" + username + "/" + password
					+ "@//" + hostOrScanName + ":" + port + "/"
					+ serviceNameOrSID;

			// System.out.println ("INFO - jdbcURL in DBConn is - " + jdbcURL);

			ds.setURL(jdbcURL);
			connection = ds.getConnection();

			System.out.println("INFO - Connected using Service Name");

			return connection;
		} catch (SQLException e1) {

			try {

				System.out.println("INFO - Could not connect using Service Name, trying as SID");

				// This is URL that works for SID
				// jdbc:oracle:thin:@oracle.hostserver1.mydomain.ca:1521:XYZ
				String jdbcURL = "jdbc:oracle:thin:@" + hostOrScanName + ":"
						+ port + ":" + serviceNameOrSID;

				ds.setURL(jdbcURL);
				connection = ds.getConnection(username, password);

				System.out.println("INFO - Connected using SID");

				return connection;
			}

			catch (SQLException e2) {

				System.out.println("INFO - Could not connect Service Name or SID, trying service name in another way");

				// This is URL that works with service-name, but username and
				// password passed separately

				String jdbcURL = "jdbc:oracle:thin:@" + hostOrScanName + ":"
						+ port + "/" + serviceNameOrSID;

				ds.setURL(jdbcURL);
				connection = ds.getConnection(username, password);

				System.out
						.println("INFO - Finally Connected using Service Name in another way");

				return connection;
			}

		}
	} // getOraDBConnJDBCThin method
	
	
	// TBD - Method - Get connection using wallet
	
	// TBD - Method - Get connection using OCI driver
	
	// TBD - Method - Get connection using OCI driver and wallet
	
	// TBD - Method - Close connection
	

} // DbConnOracle class

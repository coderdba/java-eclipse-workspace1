package com.dba.ora.dh;

import java.sql.*;

import oracle.xml.sql.query.*;
import oracle.xml.xslt.*;

public class DhOracle {

	// This WILL NOT CLOSE the connection within the method (it does not mean that it will persist)
	public ResultSet queryResultSet(Connection connection, String query)
			throws SQLException {

		Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery(query);

		return resultSet;
	}

	// This WILL NOT CLOSE the connection within the method (it does not mean that it will persist)
	public ResultSet queryResultSet(String username, String password,
			String hostOrScanName, String port, String serviceNameOrSID,
			String query) throws SQLException {

		ResultSet resultSet = null;
		
		try{
		Connection connection = new DbConnOracle().getDBConnJDBCThin(username,
				password, hostOrScanName, port, serviceNameOrSID);
		
		resultSet = queryResultSet (connection, query);

		}
		catch (Exception e) {
			e.printStackTrace();;
		}
		
		return resultSet;
	}
	
	// This WILL NOT CLOSE the connection within the method (it does not mean that it will persist)
	public OracleXMLQuery queryResultSetXMLFlat (Connection connection,
			String query) throws SQLException {
		
		OracleXMLQuery XMLResultSet = new OracleXMLQuery(connection, queryResultSet (connection, query));
		
		return XMLResultSet;
	}

	// This WILL NOT CLOSE the connection within the method (it does not mean that it will persist)
	public OracleXMLQuery queryResultSetXMLFlat (String username, String password,
			String hostOrScanName, String port, String serviceNameOrSID,
			String query) throws SQLException {

		OracleXMLQuery XMLResultSet = null;
		
		try {
		// do not close this connection after new OracleXMLQuery
		Connection connection = new DbConnOracle().getDBConnJDBCThin(username,
				password, hostOrScanName, port, serviceNameOrSID);
		
		XMLResultSet = new OracleXMLQuery(connection, queryResultSet (connection, query));

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return XMLResultSet;
	}
	
	// This WILL NOT CLOSE the connection within the method (it does not mean that it will persist)
	public void runSQL(Connection connection, String sql)
			throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeQuery(sql);
	}
}
package com.dba.ora.dh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.xml.sql.query.*;
import oracle.xml.xslt.*;

public class TestSQLRun {

	public static void main(String[] args) {
			
		try {
			
			//ResultSet resultSet = new DhOracle().queryResultSet("username", "password", "hostOrScanName", "port", 
			//														"serviceNameOrSID", "query");
			
			ResultSet resultSet = new DhOracle().queryResultSet("gm", "g$4gggg", "mycluster-scan", "1522", "myclusterdb", 
																	"select name from v$database");
			
			System.out.println ("Got resultSet1");
			resultSet.close();
						
			Connection dbConn = new DbConnOracle().getDBConnJDBCThin("gm", "g$4gggg", "mycluster-scan", "1522", "myclusterdb");
			ResultSet resultSet2 = new DhOracle().queryResultSet(dbConn, "select profile, resource_name, resource_type, limit from dba_profiles where rownum < 4");
			System.out.println ("Got resultSet2");
			// OracleXMLQuery object - does not give heirarchy - treats every row as a page
			//   If you want real heirarchy then use XML-SQL instead of this.
			OracleXMLQuery XMLResultset = new OracleXMLQuery(dbConn,resultSet2); 
			XMLResultset.keepObjectOpen(true); // save state to scroll 
			System.out.println (XMLResultset.getXMLString()); // prints every record as a flat xml
			resultSet2.close();
			dbConn.close();
			XMLResultset.close();
			
			System.out.println("\n\n\n ============== ");
			
			XMLResultset = new DhOracle().queryResultSetXMLFlat("gm", "g$4gggg", "l04-scan", "1522", "L4DB3_E", 
												"select profile, resource_name, resource_type, limit from dba_profiles where rownum < 5");
			XMLResultset.keepObjectOpen(true); // save state to scroll 
			System.out.println (XMLResultset.getXMLString()); // prints every record as a flat xml
			XMLResultset.close();

			System.out.println("\n\n\n ============== ");
			dbConn = new DbConnOracle().getDBConnJDBCThin("gm", "g$4gggg", "l04-scan", "1522", "L4DB3_E");
			XMLResultset = new DhOracle().queryResultSetXMLFlat(dbConn, "select profile, resource_name, resource_type, limit from dba_profiles where rownum < 6");
			XMLResultset.keepObjectOpen(true); // save state to scroll 
			System.out.println (XMLResultset.getXMLString()); // prints every record as a flat xml
			XMLResultset.close();
			dbConn.close();
			
			//System.out.println("\n\n\n ==== WILL NOW TEST A HEIRARCHICAL XML-SQL ============== ");
			
		}
		catch (Exception e)
		{
			System.out.println ("ERR - Error while executing query");
		}

	}

}

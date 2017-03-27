package com.dba.ora.um;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.dba.utils.*;
import com.dba.ora.dh.*;

public class UserDao {
	
	// DB operations - for DB users
	
	public void runDBASQL (User user, String sqlStatement) throws Exception {
	
		try {
			Connection connection = new DbConnOracle().getDBConnJDBCThin("gm2",
					"a$b$123", user.getDbScanOrHost(), user.getPort(), user.getDbServiceOrSID());
			
			new DhOracle().runSQL(connection, sqlStatement);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public String resetPasswordOra (User user) throws Exception {
		
		String password = "PW$" + new RandomString().getRandomString().substring(1,8) + "_12" ;
		String sqlStatement = "alter user " + user.getId() + " identified by " + password ;
		
		try {
			Connection connection = new DbConnOracle().getDBConnJDBCThin("gm2",
					"a$b$123", user.getDbScanOrHost(), user.getPort(), user.getDbServiceOrSID());
			
			new DhOracle().runSQL(connection, sqlStatement);
		}
		catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
		
		return password;
	}
	
	public String resetPasswordOra (User user, Connection connection) throws Exception {
		String password = "PW$" + new RandomString().getRandomString().substring(1,8) + "_12" ;
		String sqlStatement = "alter user " + user.getId() + " identified by " + password ;
		
		try {
			new DhOracle().runSQL(connection, sqlStatement);
		}
		catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
		
		return password;
	}
	
	
	public void unlockUserOra (User user) throws Exception {
		
		String sqlStatement = "alter user " + user.getId() + " account unlock ";
		
		try {
			runDBASQL (user, sqlStatement);
		}
		catch (Exception e) {
			throw e;
		}

	}
	
}

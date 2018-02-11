package org.itstep.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
	
	
 private static final String URL="jdbc:postgresql://localhost:5432/shopping";	 
 private static final String USER_NAME="postgres";
 private static final String USER_PASSWORD="111111111";
 public static Connection getConnection()  {
	 
 Connection connection = null;
 
 try {
	connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
} catch (SQLException e) {
	e.printStackTrace();
}	 
  return connection;
 }  
}

package org.itstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import org.apache.commons.net.daytime.DaytimeUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.itstep.model.Account;
import org.itstep.model.Action;
import org.omg.CORBA.Current;

public class ActionDAO {
	public void saveAction(String asin, Account account, String action) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO goods_action(asin, login, action, action_time) VALUES(?, ?, ?, ?)";
				
		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, asin);
			statement.setString(2, account.getLogin());
			statement.setString(3, action);	
			Date dtame = new Date();			
			statement.setLong(4, dtame.getTime());
			
	
			statement.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public Action getAction(String asin) {
		
				Action action = new Action();
				Connection connection = null;
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				String sql = "SELECT * FROM goods_action WHERE asin=?";
								
				try {
					connection = ConnectionToDB.getConnection();
					statement = connection.prepareStatement(sql);
					statement.setString(1, asin);
					resultSet = statement.executeQuery();
					while (resultSet.next())
					{
						action.setAsin(resultSet.getString("asin"));
						action.setLogin(resultSet.getString("login"));
						action.setAction(resultSet.getString("action"));
						action.setActionTime(resultSet.getLong("action_time"));
																	
					}					
				} catch (SQLException e) {					
					e.printStackTrace();
				}				
				finally {					
					try {
						resultSet.close();
					} catch (SQLException e) {						
						e.printStackTrace();
					}
					try {
						statement.close();
					} catch (SQLException e) {						
						e.printStackTrace();
					}
					try {
						connection.close();
					} catch (SQLException e) {						
						e.printStackTrace();
					}
				}
				return action;					
	}

}

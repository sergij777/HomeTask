package org.itstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.itstep.model.Account;

public class AccountDAO {
	public void saveAccount(Account account) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO accounts(login, password, first_name, second_name) VALUES(?, ?, ?, ?)";
		
		
		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, account.getLogin());
			statement.setString(2, account.getPassword());
			statement.setString(3, account.getFirstName());			
			statement.setString(4, account.getSecondName());
	
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
	
	public  Account getAccount(String login) {
		
				Account account = new Account();
				Connection connection = null;
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				String sql = "SELECT * FROM accounts WHERE login=?";
				
				
				
				try {
					connection = ConnectionToDB.getConnection();
					statement = connection.prepareStatement(sql);
					statement.setString(1, login);
					resultSet = statement.executeQuery();
					while (resultSet.next())
					{
						account.setLogin(resultSet.getString("login"));
						account.setPassword(resultSet.getString("password"));
						account.setFirstName(resultSet.getString("first_name"));
						account.setSecondName(resultSet.getString("second_name"));
											
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
				return account;					
	}
}

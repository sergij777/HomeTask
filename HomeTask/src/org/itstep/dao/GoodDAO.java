package org.itstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.itstep.model.Good;

public class GoodDAO {
	
	public void saveGood(Good good) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO goods(asin, name, price, img_url, good_url, initial_price) VALUES(?, ?, ?, ?, ?, ?)";
		
		
		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, good.getAsin());
			statement.setString(2, good.getName());
			statement.setInt(3, good.getPrice());	
			statement.setString(4, good.getImgUrl());
			statement.setString(5, good.getGoodUrl());
			statement.setInt(6, good.getInitialPrice());
	
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
	
	public Good getGood(String asin) {
		
				Good good = new Good();				
				Connection connection = null;
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				String sql = "SELECT * FROM goods WHERE asin=?";
								
				try {
					connection = ConnectionToDB.getConnection();
					statement = connection.prepareStatement(sql);
					statement.setString(1, asin);
					resultSet = statement.executeQuery();
					while (resultSet.next())
					{
						good.setAsin(resultSet.getString("asin"));
						good.setName(resultSet.getString("name"));
						good.setPrice(resultSet.getInt("price"));
						good.setInitialPrice(resultSet.getInt("initial_price"));
						good.setImgUrl(resultSet.getString("img_url"));
						good.setGoodUrl(resultSet.getString("good_url"));
											
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
				return good;					
	}
	
	
	public ArrayList<String> getAllGoodsAsin() {
		
		ArrayList<String> asins = new ArrayList<String>();			
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT asin FROM goods";
						
		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			
			resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				asins.add(resultSet.getString("asin"));
				
													
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
		return asins;
	}
}

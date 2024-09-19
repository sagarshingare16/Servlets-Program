package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;
import utility.JdbcUtil;

public class RegisterUser {
	
	public static boolean registerUser(User user) {
		try(Connection connection = JdbcUtil.getConnection()){
			
			String sqlStatement = "insert into user_info(userId,userName,userEmail,userPassword) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			
			preparedStatement.setString(1, user.getUserId());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getUserEmail());
			preparedStatement.setString(4, user.getUserPassword());
			
			int rowInserted = preparedStatement.executeUpdate();
			
			return rowInserted>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}

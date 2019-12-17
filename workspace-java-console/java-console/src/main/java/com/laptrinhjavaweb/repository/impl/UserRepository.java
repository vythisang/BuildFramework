package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;

public class UserRepository extends SimpleJpaRepository<UserEntity> implements IUserRepository{


	@Override
	public UserEntity save(UserEntity userEntity) {
		String sql  = new String("insert into user(username,fullname,password,status) values(?,?,?,?)");
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		try {
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userEntity.getUserName());
			preparedStatement.setString(2, userEntity.getFullName());
			preparedStatement.setString(3, userEntity.getPassword());
			preparedStatement.setString(4, userEntity.getStatus());
			preparedStatement.executeUpdate();
			connection.commit();
			return userEntity;
		}catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				}catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
			
			
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				
			}catch (SQLException e) {
				e.getMessage();
			}
		}
		
		return new UserEntity();
	}

}

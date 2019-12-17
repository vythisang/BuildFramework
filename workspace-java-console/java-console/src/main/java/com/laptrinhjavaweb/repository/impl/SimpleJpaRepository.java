package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.JpaRepository;

public class SimpleJpaRepository<T> implements JpaRepository<T> {

	private Class<T> zClass;
	
	@SuppressWarnings("unchecked")
	public SimpleJpaRepository() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass  = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	
	@Override
	public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... where) {
		String tableName ="";
		if(zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		StringBuilder sql = new StringBuilder("select * from "+tableName);
		
		sql.append(" A where 1=1 ");
		sql = createSQLfindAll(sql,properties);
		if(where != null && where.length  == 1) {
			sql.append(where[0]);
		}
		sql.append(" limit ").append(pageable.getOffset()).append(", ").append(pageable.getLimit());
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			return resultSetMapper.mapRow(resultSet, this.zClass);
		}catch (SQLException e) {
			return new ArrayList<>();
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			}catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}


	protected StringBuilder createSQLfindAll(StringBuilder where, Map<String, Object> params) {
		if(params != null && params.size() >0) {
			String[] keys = new String[params.size()];
			Object[] values = new Object[params.size()];
			int i=0;
			for(Map.Entry<String, Object> item : params.entrySet()) {
				keys[i] = item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for(int j = 0; j < values.length; j++) {
				if((values[j] instanceof String) && (StringUtils.isNotBlank(values[j].toString()))) {
					where.append(" AND LOWER(A."+keys[j]+") like '%"+values[j].toString()+"%' ");
				}else if((values[j] instanceof Integer) && (values[j] != null)) {
					where.append(" AND LOWER(A."+keys[j]+") = "+values[j]+" ");
				}else if((values[j] instanceof Long) && (values[j] != null)) {
					where.append(" AND LOWER(A."+keys[j]+") = "+values[j]+" ");
				}
			}
		}
		return where;
	}


	@Override
	public List<T> findAll(Map<String, Object> properties, Object... where) {
		String tableName ="";
		if(zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		StringBuilder sql = new StringBuilder("select * from "+tableName +" A where 1=1 ");
		sql = createSQLfindAll(sql,properties);
		if(where != null && where.length >0) {
			sql.append(where[0]);
		}
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			return resultSetMapper.mapRow(resultSet, this.zClass);
		}catch (SQLException e) {
			return new ArrayList<>();
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			}catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}


	@Override
	public List<T> findAll(String sqlSearch, Pageable pageable, Object... objects) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		StringBuilder sql = new StringBuilder(sqlSearch);
		sql.append(" limit ").append(pageable.getOffset()).append(", ").append(pageable.getLimit());
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			return resultSetMapper.mapRow(resultSet, this.zClass);
		}catch (SQLException e) {
			return new ArrayList<>();
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			}catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}


	@Override
	public Long insert(Object object) {
		String sql = createSQLInsert();
		Connection connection = null;
		PreparedStatement preparableStatement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			preparableStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			Class<?> aClass = object.getClass();
			Field[] fields = aClass.getDeclaredFields();
			for(int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				preparableStatement.setObject(i+1, field.get(object));
			}
			
			Class<?> parentClass = aClass.getSuperclass();
			int indexParent = fields.length + 1;
			while(parentClass != null) {
				Field[] fieldsParent  = parentClass.getDeclaredFields();
				for(int i = 0; i < fieldsParent.length; i++) {
					Field field = fieldsParent[i];
					field.setAccessible(true);
					preparableStatement.setObject(indexParent, field.get(object));
					indexParent++;
				}
				parentClass = parentClass.getSuperclass();
			}
			
			preparableStatement.executeUpdate();
			resultSet  = preparableStatement.getGeneratedKeys();
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			
			connection.commit();
			return id;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparableStatement != null) {
					preparableStatement.close();
				}
			} catch (SQLException  e) {
				e.printStackTrace();
			}

		}
		return null;
	}


	private String createSQLInsert() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		
		String sql = "insert into " + tableName + "(" + fields.toString() + ")" + " values" + "(" + params.toString() + ")";
		return sql;
	}

}

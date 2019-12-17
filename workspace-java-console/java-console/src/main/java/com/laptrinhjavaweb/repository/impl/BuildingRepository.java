package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.mysql.cj.xdevapi.PreparableStatement;

public class BuildingRepository extends SimpleJpaRepository<BuildingEntity> implements IBuildingRepository{

	@Override
	public List<BuildingEntity> findAll(Pageable pageable, Map<String, Object> params, BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sqlSearch = new StringBuilder("select * from building A ");
		if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) {
			sqlSearch.append(" inner join assignmentstaff on A.id = assignmentstaff.buildingid ");
		}
		sqlSearch.append(" where 1=1 ");
		sqlSearch = this.createSQLfindAll(sqlSearch, params);
		String sqlSpecial = buildSqlSpecial(buildingSearchBuilder);
		sqlSearch.append(sqlSpecial);
		return this.findAll(sqlSearch.toString(), pageable);
	}

	private String buildSqlSpecial(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder stringBuilder = new StringBuilder("");
		if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentFrom())) {
			stringBuilder.append(" AND A.costrent >="+buildingSearchBuilder.getCostRentFrom());
		}
		if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentTo())) {
			stringBuilder.append(" AND A.costrent <="+buildingSearchBuilder.getCostRentTo());
		}
		if(buildingSearchBuilder.getBuildingTypes().length >0 ) {
			stringBuilder.append(" AND (");
			
			/*int i = 0;
			for(String item : buildingSearchBuilder.getBuildingTypes()) {
				if(i == 0) {
					stringBuilder.append("A.TYPE like '%"+item+"%' ");
				}else {
					stringBuilder.append("OR A.TYPE like '%"+item+"%' ");
				}
				i++;
			}*/
			/*stringBuilder.append(" A.TYPE like '%"+buildingSearchBuilder.getBuildingTypes()[0]+"%' ");
			Arrays.stream(buildingSearchBuilder.getBuildingTypes())
			.filter(item -> !item.equals(buildingSearchBuilder.getBuildingTypes()[0]))
			.forEach(item -> stringBuilder.append(" OR A.TYPE like '%"+item+"%' "));*/
			Arrays.stream(buildingSearchBuilder.getBuildingTypes())
			.map(item -> "(A.type like '%"+item+"%')")
			.collect(Collectors.joining(" OR "));
			stringBuilder.append(" )");
		}
		if(StringUtils.isNotBlank(buildingSearchBuilder.getAreaRentFrom()) || StringUtils.isNotBlank(buildingSearchBuilder.getAreaRentTo())){
			stringBuilder.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id ");
			if(buildingSearchBuilder.getAreaRentFrom() != null) {
				stringBuilder.append(" AND ra.value >="+buildingSearchBuilder.getAreaRentFrom()+" ");
			}
			if(buildingSearchBuilder.getAreaRentTo() != null) {
				stringBuilder.append(" AND ra.value <="+buildingSearchBuilder.getAreaRentTo()+" ");
			}
			stringBuilder.append("))");
		}
		if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) {
			stringBuilder.append(" AND assignmentstaff.staffid = "+buildingSearchBuilder.getStaffId()+" ");
		}
		return stringBuilder.toString();
	}


	@Override
	public BuildingEntity save(BuildingEntity buildingEntity) {
		String sql = "insert into building(name,street,district,ward) values(?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparableStatement = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			preparableStatement = connection.prepareStatement(sql);
			preparableStatement.setString(1, buildingEntity.getName());
			preparableStatement.setString(2, buildingEntity.getStreet());
			preparableStatement.setString(3, buildingEntity.getDistrict());
			preparableStatement.setString(4, buildingEntity.getWard());
			preparableStatement.executeUpdate();
			connection.commit();
			return buildingEntity;
		} catch (SQLException e) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return new BuildingEntity();
	}
	
	private void setParameter(PreparableStatement preparableStatement, Object...params) {
		
			for(int i = 0; i < params.length; i++) {
				
			}
			
			
		
	}

}

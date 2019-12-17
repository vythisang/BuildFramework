package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingRepository extends JpaRepository<BuildingEntity>{

	List<BuildingEntity> findAll(Pageable pageable, Map<String, Object> params, BuildingSearchBuilder buildingSearchBuilder);
	BuildingEntity save(BuildingEntity buildingEntity);

}

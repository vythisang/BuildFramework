package com.laptrinhjavaweb.service;


import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {

	List<BuildingDTO> findAll(Pageable pageable, BuildingSearchBuilder buildingSearchBuilder);
	BuildingDTO save(BuildingDTO buildingDTO);
}

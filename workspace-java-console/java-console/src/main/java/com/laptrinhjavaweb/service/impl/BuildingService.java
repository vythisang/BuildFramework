package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService{

	private IBuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;
	public BuildingService() {
		buildingRepository = new BuildingRepository();
		buildingConverter = new BuildingConverter();
	}
	
	
	@Override
	public List<BuildingDTO> findAll(Pageable pageable , BuildingSearchBuilder buildingSearchBuilder) {
//		//Java 7
//		List<BuildingDTO> results = new ArrayList<>();
//		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
//		for(BuildingEntity item : buildingEntities) {
//			results.add(buildingConverter.convertToDTO(item));
//		}
//		return results;
		
		// java 8
		Map<String, Object> properties = convertToMapProperties(buildingSearchBuilder);
		return buildingRepository.findAll(pageable,properties,buildingSearchBuilder)
				.stream()
				.map(item -> buildingConverter.convertToDTO(item))
				.collect(Collectors.toList());
	}


	


	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder buildingSearchBuilder) {
		Map<String, Object> properties = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("areaRent") && !field.getName().equals("staffId")) {
					if (field.get(buildingSearchBuilder) instanceof String) {
						if (field.getName().equals("buildingArea") || field.getName().equals("numberOfBasement")) {
							if (field.get(buildingSearchBuilder) != null
									&& StringUtils.isNotEmpty((String) field.get(buildingSearchBuilder))) {
								properties.put(field.getName().toLowerCase(),
										Integer.parseInt((String) field.get(buildingSearchBuilder)));
							}
						} else {
							properties.put(field.getName().toLowerCase(), field.get(buildingSearchBuilder));
						}
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return properties;
	}


	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreateDate(new Date());
		buildingEntity.setCreatedBy("Thi pham");
		Long id = buildingRepository.insert(buildingEntity);
		
		return buildingDTO;
	}

	

}

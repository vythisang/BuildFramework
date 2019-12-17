package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-building"})
public class BuildingAPI extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private IBuildingService buildingService  = new BuildingService();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingDTO = buildingService.save(buildingDTO);
		objectMapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = FormUtil.toModel(BuildingDTO.class, request);
		
		Pageable pageable = new PageRequest(buildingDTO.getPage(), buildingDTO.getLimit());
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
															.setName(buildingDTO.getName()).setDistrict(buildingDTO.getDistrict())
															.setBuildingArea(buildingDTO.getBuildingArea())
															.setNumberOfBasement(buildingDTO.getNumberOfBasement())
															.setStreet(buildingDTO.getStreet()).setWard(buildingDTO.getWard())
															.setBuildingTypes(buildingDTO.getBuildingTypes())
															.setAreaRentFrom(buildingDTO.getAreaRentFrom()).setAreaRentTo(buildingDTO.getAreaRentTo())
															.setCostRentFrom(buildingDTO.getCostRentFrom()).setCostRentTo(buildingDTO.getCostRentTo())
															.setStaffId(buildingDTO.getStaffId())
															.build();
		List<BuildingDTO> buildings = buildingService.findAll(pageable, buildingSearchBuilder);
		
		mapper.writeValue(response.getOutputStream(), buildings);
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}
}

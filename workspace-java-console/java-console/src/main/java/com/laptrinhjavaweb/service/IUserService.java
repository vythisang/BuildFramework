package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.UserDTO;

public interface IUserService {

	UserDTO save(UserDTO userDTO);
	List<UserDTO> findStaffs(Long buildingId);
}

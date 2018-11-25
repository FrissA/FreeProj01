package com.pokemon.app.service;

import java.util.HashMap;
import java.util.Map;

import com.pokemon.app.dto.UserMapDto;

public class UserMapping {
	Map<Integer, UserMapDto> map = new HashMap<Integer, UserMapDto>();
	private static UserMapping userMapping = null;

	public static UserMapping getInstance() {
		if (userMapping == null)
			userMapping = new UserMapping();
		return userMapping;
	}

	public Map<Integer, UserMapDto> getMap() {
		return map;
	}

	public void setMap(Map<Integer, UserMapDto> map) {
		this.map = map;
	}
}
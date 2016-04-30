package com.skycast.weather.manager;

import java.util.TreeSet;

import com.skycast.weather.service.SkycastUserService;

public class SkycastUserManager {
	
	
	public boolean validateUser(String username){
		return SkycastUserService.validateUser(username);
	}
	
	public boolean registerUser(String username){
		return SkycastUserService.registerUser(username);
	}
	
	public void addAddress(String username, String address){
		SkycastUserService.addAddress(username, address);
	}
	
	public TreeSet<String> getUserHistory(String username){
		return SkycastUserService.getUserHistory(username);
	}

}

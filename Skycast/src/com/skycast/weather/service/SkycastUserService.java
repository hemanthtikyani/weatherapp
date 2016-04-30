package com.skycast.weather.service;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SkycastUserService {
	
	private static TreeMap<String, TreeSet<String>> userDatabase;
	
	public static boolean validateUser(String username){
		boolean flag = false;
		if(null==userDatabase){
			return false;
		}else{
			for(Map.Entry<String,TreeSet<String>> entry : userDatabase.entrySet()) {
				if(entry.getKey().equals(username)){
					return true;
				}
			}
		}
		return flag;
	}
	
	public static boolean registerUser(String username){
		if(null==userDatabase){
			userDatabase = new TreeMap<String, TreeSet<String>>();
			userDatabase.put(username, null);
			return true;
		}else{
			for(Map.Entry<String,TreeSet<String>> entry : userDatabase.entrySet()) {
				if(entry.getKey().equals(username)){
					return false;
				}
			}
			userDatabase.put(username, null);
			return true;
		}
	}
	
	public static void addAddress(String username, String address){
		for(Map.Entry<String,TreeSet<String>> entry : userDatabase.entrySet()) {
			if(entry.getKey().equals(username)){
				TreeSet<String> set;
				if(null==entry.getValue()){
					set = new TreeSet<String>();
					set.add(address.toLowerCase());
				}else{
					set = userDatabase.get(username);
					set.add(address.toLowerCase());
				}
				userDatabase.put(username, set);
			}
		}
	}
	
	public static TreeSet<String> getUserHistory(String username){
		return userDatabase.get(username);
	}
}

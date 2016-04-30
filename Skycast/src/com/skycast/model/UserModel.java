package com.skycast.model;

import java.util.List;

public class UserModel {

	private String username;
	private List<String> userHistory;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getUserHistory() {
		return userHistory;
	}
	public void setUserHistory(List<String> userHistory) {
		this.userHistory = userHistory;
	} 
}

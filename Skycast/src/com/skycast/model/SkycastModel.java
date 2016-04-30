package com.skycast.model;

import com.skycast.forecastapi.pojo.ForecastData;

public class SkycastModel {
	
	private String locationName;
	private String formattedAddress;
	private String latitude;
	private String longitude;
	private String status;
	
	private ForecastData forecastData;
	
	private ForecastData pastData;
	
	private int[] temperature;
	
	private String[] date;
	
	private String graphData;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public ForecastData getForecastData() {
		return forecastData;
	}

	public void setForecastData(ForecastData forecastData) {
		this.forecastData = forecastData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ForecastData getPastData() {
		return pastData;
	}

	public void setPastData(ForecastData pastData) {
		this.pastData = pastData;
	}

	public int[] getTemperature() {
		return temperature;
	}

	public void setTemperature(int[] temperature) {
		this.temperature = temperature;
	}

	public String[] getDate() {
		return date;
	}

	public void setDate(String[] date) {
		this.date = date;
	}

	public String getGraphData() {
		return graphData;
	}

	public void setGraphData(String graphData) {
		this.graphData = graphData;
	}
	
}

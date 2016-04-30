package com.skycast.weather.manager;

import org.apache.log4j.Logger;

import com.skycast.forecastapi.pojo.ForecastData;
import com.skycast.geolocationapi.pojo.LocationDetails;
import com.skycast.model.SkycastModel;
import com.skycast.utils.SkycastConstants;
import com.skycast.weather.service.SkycastWeatherService;

public class SkycastWeatherManager {
	
	Logger logger = Logger.getLogger(SkycastWeatherManager.class);
	
	public SkycastModel getLocationDetails(String address){
		
		 SkycastModel model = null;
		
		 LocationDetails locationDetails = SkycastWeatherService.getLocationDetails(address);
		 
		 if(null!=locationDetails && checkStatus(locationDetails)){
			 
			 if(locationDetails.getResults().length > 0) {
				 
				 model = new SkycastModel();
				 
				 model.setStatus("success");
				 model.setFormattedAddress(locationDetails.getResults()[0].getFormatted_address());
				 model.setLocationName(address);
				 model.setLatitude(locationDetails.getResults()[0].getGeometry().getLocation().getLat());
				 model.setLongitude(locationDetails.getResults()[0].getGeometry().getLocation().getLng());
				 
				 ForecastData forecastData = SkycastWeatherService.getWeatherForecast(model.getLatitude(), model.getLongitude());
				 
				 if(null!=forecastData){
					 model.setForecastData(forecastData);
					 StringBuffer pastData = SkycastWeatherService.getWeatherHistory(model.getLatitude(), model.getLongitude());
					 if(null!=pastData){
						pastData.append(forecastData.getCurrently().getDate().concat(":").concat(forecastData.getCurrently().getTemperature()));
					 }
					 if(pastData.toString().length()>0){
						 pastData.substring(0, pastData.length()-1);
					 }	
					 model.setGraphData(pastData.toString());
				 }
			}
		}
		 
		 if(null==model){
			 logger.debug("No results found for address - "+address);
			 model = new SkycastModel();
			 model.setStatus("error");
		 }
		 return model;
	}
	
	public SkycastModel getWeatherForecast(String latitude, String longitude){
		SkycastModel model = null;
		
		ForecastData forecastData = SkycastWeatherService.getWeatherForecast(latitude, longitude);
		 
		 if(null!=forecastData){
			 model = new SkycastModel();
			 model.setForecastData(forecastData);
			 StringBuffer pastData = SkycastWeatherService.getWeatherHistory(model.getLatitude(), model.getLongitude());
			 if(null!=pastData){
				pastData.append(forecastData.getCurrently().getDate().concat(":").concat(forecastData.getCurrently().getTemperature()));
			 }
			 if(pastData.toString().length()>0){
				 pastData.substring(0, pastData.length()-1);
			 }	
			 model.setGraphData(pastData.toString());
		 }
		 if(null==model){
			 logger.debug("No results found for address - "+latitude + "and" +longitude);
			 model = new SkycastModel();
			 model.setStatus("error");
		 }
		return model;
	}
	
	private boolean checkStatus(LocationDetails locationDetails) {
        boolean isSuccess = false;
        if (SkycastConstants.OK_RESULTS.equalsIgnoreCase(locationDetails.getStatus())) {
            isSuccess = true;
        }
        return isSuccess;
    }
	
	

}

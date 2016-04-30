package com.skycast.weather.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.skycast.forecastapi.pojo.ForecastData;
import com.skycast.geolocationapi.pojo.LocationDetails;
import com.skycast.utils.SkycastConstants;
import com.skycast.utils.SkycastRestUtil;

public class SkycastWeatherService {
	
	static Logger logger = Logger.getLogger(SkycastWeatherService.class);
	
	public static LocationDetails getLocationDetails(String address){
		
		LocationDetails locationDetails = null;
        try {
            String jsonString = SkycastRestUtil.httpGetJSONObject(SkycastConstants.GOOGLE_GET_LOCATION_URL + address).toString();
            ObjectMapper mapper = new ObjectMapper();
            locationDetails = mapper.readValue(jsonString, LocationDetails.class);
        } catch (JsonGenerationException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        }
        return locationDetails;
	}
	
	public static ForecastData getWeatherForecast(String latitude, String longitude) {

        ForecastData foreCastData = null;
        try {
            String jsonString = SkycastRestUtil.httpGetJSONObject(SkycastConstants.FORECAST_GET_LOCATION_URL + SkycastConstants.KEY + SkycastConstants.SLASH_SEPARATOR + latitude + SkycastConstants.COMMA_SEPARATOR + longitude).toString();
            ObjectMapper mapper = new ObjectMapper();
            foreCastData = mapper.readValue(jsonString, ForecastData.class);
        } catch (JsonGenerationException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        }
        return foreCastData;
    }
	
	public static StringBuffer getWeatherHistory(String latitude, String longitude){
		ForecastData foreCastData = null;
		StringBuffer sb = new StringBuffer();
        try {
        	for(int i = 5; i>=0; i--){
        		String unixTime = String.valueOf((System.currentTimeMillis()/ 1000L) - ((i+1)*86400));
                String jsonString = SkycastRestUtil.httpGetJSONObject(SkycastConstants.FORECAST_GET_LOCATION_URL + SkycastConstants.KEY + SkycastConstants.SLASH_SEPARATOR + latitude + SkycastConstants.COMMA_SEPARATOR + longitude + SkycastConstants.COMMA_SEPARATOR + unixTime).toString();
                ObjectMapper mapper = new ObjectMapper();
                foreCastData = mapper.readValue(jsonString, ForecastData.class);
                
                if(null!=foreCastData && null!= foreCastData.getCurrently()){
                	if(null!=foreCastData.getCurrently().getTemperature()){
                		sb.append(foreCastData.getCurrently().getDate().concat(":").concat(foreCastData.getCurrently().getTemperature()).concat(","));
                	}
                }
        	}
        } catch (JsonGenerationException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
        	logger.debug("Exception occured in getLocationDetails method - "+e.getMessage());
            e.printStackTrace();
        }
        return sb;
	}

}

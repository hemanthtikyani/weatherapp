package com.skycast.test;

import com.skycast.model.SkycastModel;
import com.skycast.weather.manager.SkycastWeatherManager;

public class WeatherTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SkycastWeatherManager impl = new SkycastWeatherManager();
		SkycastModel model = impl.getLocationDetails("indianapolis");
		
		System.out.println(model.getFormattedAddress());
		System.out.println(model.getForecastData().getCurrently().getTemperature());

	}

}

package com.skycast.forecastapi.pojo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Data
{
    private String summary;

    private String icon;

    private String pressure;

    private String cloudCover;

    private String apparentTemperature;

    private String precipIntensity;

    private String temperature;

    private String dewPoint;

    private String ozone;

    private String time;

    private String windSpeed;

    private String humidity;

    private String windBearing;

    private String precipProbability;
    
    private String day;
    
    private String currentTime;
    
    private String date;
    
    private String temperatureMin;
    
    private String temperatureMax;
    
    private String sunriseTime;
    
    private String sunsetTime;
    
    DecimalFormat df = new DecimalFormat("###");

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (String summary)
    {
        this.summary = summary;
    }

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getPressure ()
    {
        return pressure;
    }

    public void setPressure (String pressure)
    {
        this.pressure = df.format(Double.parseDouble(pressure));
    }

    public String getCloudCover ()
    {
        return cloudCover;
    }

    public void setCloudCover (String cloudCover)
    {
        this.cloudCover = cloudCover;
    }

    public String getApparentTemperature ()
    {
        return apparentTemperature;
    }

    public void setApparentTemperature (String apparentTemperature)
    {
        this.apparentTemperature = apparentTemperature;
    }

    public String getPrecipIntensity ()
    {
        return precipIntensity;
    }

    public void setPrecipIntensity (String precipIntensity)
    {
        this.precipIntensity = precipIntensity;
    }

    public String getTemperature ()
    {
        return temperature;
    }

    public void setTemperature (String temperature)
    {
        this.temperature = df.format(Double.parseDouble(temperature));
    }

    public String getDewPoint ()
    {
        return dewPoint;
    }

    public void setDewPoint (String dewPoint)
    {
        this.dewPoint = df.format(Double.parseDouble(dewPoint));
    }

    public String getOzone ()
    {
        return ozone;
    }

    public void setOzone (String ozone)
    {
        this.ozone = ozone;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
        if(null!=time){
        	TimeZone.setDefault(TimeZone.getTimeZone("EST5EDT"));
        	Date date = new java.util.Date(Long.parseLong(time)*1000);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            setDay(sdf.format(date));
            sdf = new SimpleDateFormat("dd-MM-yyyy");
    		setDate(sdf.format(date));
    		sdf = new SimpleDateFormat("HH:mm");
    		setCurrentTime(sdf.format(date));
        }
    }

    public String getWindSpeed ()
    {
        return windSpeed;
    }

    public void setWindSpeed (String windSpeed)
    {
        this.windSpeed = df.format(Double.parseDouble(windSpeed));
    }

    public String getHumidity ()
    {
        return humidity;
    }

    public void setHumidity (String humidity)
    {
        this.humidity = df.format(Double.parseDouble(humidity)*100d);
    }

    public String getWindBearing ()
    {
        return windBearing;
    }

    public void setWindBearing (String windBearing)
    {
        this.windBearing = windBearing;
    }

    public String getPrecipProbability ()
    {
        return precipProbability;
    }

    public void setPrecipProbability (String precipProbability)
    {
        this.precipProbability = precipProbability;
    }

    public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(String temperatureMin) {
		this.temperatureMin = df.format(Double.parseDouble(temperatureMin));
	}

	public String getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(String temperatureMax) {
		this.temperatureMax = df.format(Double.parseDouble(temperatureMax));
	}
	
	public String getSunriseTime() {
		return sunriseTime;
	}

	public void setSunriseTime(String sunriseTime) {
    	Date date = new java.util.Date(Long.parseLong(sunriseTime)*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		this.sunriseTime = sdf.format(date);
	}

	public String getSunsetTime() {
		return sunsetTime;
	}

	public void setSunsetTime(String sunsetTime) {
    	Date date = new java.util.Date(Long.parseLong(sunsetTime)*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		this.sunsetTime = sdf.format(date);
	}

	@Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", icon = "+icon+", pressure = "+pressure+", cloudCover = "+cloudCover+", apparentTemperature = "+apparentTemperature+", precipIntensity = "+precipIntensity+", temperature = "+temperature+", dewPoint = "+dewPoint+", ozone = "+ozone+", time = "+time+", windSpeed = "+windSpeed+", humidity = "+humidity+", windBearing = "+windBearing+", precipProbability = "+precipProbability+"]";
    }
}

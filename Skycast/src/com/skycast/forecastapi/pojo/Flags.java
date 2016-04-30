package com.skycast.forecastapi.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Flags
{
    private String[] isdStations;

    private String units;

    private String[] sources;

    public String[] getIsdStations ()
    {
        return isdStations;
    }

    public void setIsdStations (String[] isdStations)
    {
        this.isdStations = isdStations;
    }

    public String getUnits ()
    {
        return units;
    }

    public void setUnits (String units)
    {
        this.units = units;
    }

    public String[] getSources ()
    {
        return sources;
    }

    public void setSources (String[] sources)
    {
        this.sources = sources;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isd-stations = "+isdStations+", units = "+units+", sources = "+sources+"]";
    }
}



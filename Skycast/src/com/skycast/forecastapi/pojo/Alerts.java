package com.skycast.forecastapi.pojo;

public class Alerts {
	private String time;

    private String title;

    private String expires;

    private String description;

    private String uri;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getExpires ()
    {
        return expires;
    }

    public void setExpires (String expires)
    {
        this.expires = expires;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [time = "+time+", title = "+title+", expires = "+expires+", description = "+description+", uri = "+uri+"]";
    }
}

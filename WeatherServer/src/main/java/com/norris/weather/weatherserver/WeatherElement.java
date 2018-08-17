package com.norris.weather.weatherserver;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class WeatherElement implements Serializable {
    private int zipCode;
    private String windSpeed;
    private String windDirection;
    private long timeFetched;

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setTimeFetched(long timeFetched) {
        this.timeFetched = timeFetched;
    }

    public WeatherElement(int zipCode) {
        this.zipCode = zipCode;
    }

    public WeatherElement(int zipCode, String windSpeed, String windDirection, long timeFetched){
        this.zipCode = zipCode;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.timeFetched = timeFetched;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    @JsonIgnore
    public long getTimeFetched() {
        return timeFetched;
    }

}

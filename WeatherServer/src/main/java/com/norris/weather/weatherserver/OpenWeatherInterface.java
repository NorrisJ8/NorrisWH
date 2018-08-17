package com.norris.weather.weatherserver;

import org.json.*;

public interface OpenWeatherInterface {
    WeatherElement getWeather(int zipCode);
}

package com.norris.weather.weatherserver;

public interface Cache {
    WeatherElement get(int zipCode);
    void clear();
}


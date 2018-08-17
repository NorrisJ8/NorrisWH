package com.norris.weather.weatherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherserverApplication {
    static Cache _cache = new WeatherCacheDecorator(new CacheInstance());

    public static void main(String[] args) {

        SpringApplication.run(WeatherserverApplication.class, args);
    }


}


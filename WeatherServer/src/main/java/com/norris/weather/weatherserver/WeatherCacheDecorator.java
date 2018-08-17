package com.norris.weather.weatherserver;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class WeatherCacheDecorator extends CacheDecorator implements OpenWeatherInterface {
    final long EXPIRATION_TIME_IN_MS = 900000;
    private static final String API_KEY = "0e21f6b688fd46f285d25c0a4a20e5d4";
    private ConcurrentHashMap<Integer, WeatherElement> cache = new ConcurrentHashMap<>();

    public WeatherCacheDecorator(Cache cache) {
        super(cache);
    }

    public WeatherElement get(int zipCode) {
        WeatherElement res = cache.get(zipCode);
        if (res == null) {
            System.out.println(LocalDateTime.now() + " - No cached data. Fetching weather for " + zipCode + ".");
            res = getWeather(zipCode);
        }
        else if ((System.currentTimeMillis() - res.getTimeFetched()) > EXPIRATION_TIME_IN_MS) {
            System.out.println(LocalDateTime.now() + " - Cached data older than 15 minutes. Fetching current weather for " + zipCode + ".");
            res = getWeather(zipCode);
        }
        else {
            System.out.println(LocalDateTime.now() + " - Cached value found. Returning stored weather for " + zipCode + ".");
        }
        return res;
    }


    @Override
    public WeatherElement getWeather(int zipCode) {
        String uri = String.format("https://api.openweathermap.org/data/2.5/weather?zip=%s&APPID=%s", String.valueOf(zipCode), API_KEY);
        RestTemplate template = new RestTemplate();
        String result = template.getForObject(uri, String.class);
        WeatherElement element = buildFromString(zipCode, result);
        if (cache.get(zipCode) != null) {
            cache.remove(zipCode);
        }
        cache.put(zipCode, element);
        return element;
    }

    protected WeatherElement buildFromString(int zipCode, String input) {
        JSONObject jsonResponse = new JSONObject(input);
        String windSpeed =  jsonResponse.getJSONObject("wind").get("speed").toString();
        String windDirection = jsonResponse.getJSONObject("wind").get("deg").toString();
        return new WeatherElement(zipCode, windSpeed, windDirection, System.currentTimeMillis());
    }

    protected void put(WeatherElement element) {
        cache.putIfAbsent(element.getZipCode(), element);
    }

    protected int getCacheSize() {
        return cache.size();
    }

    @Override
    public void clear() {
        cache.clear();
    }
}

package com.norris.weather.weatherserver;

public abstract class CacheDecorator implements Cache{
    private Cache cache;

    public CacheDecorator(Cache cache) {
        this.cache = cache;
    }

    public WeatherElement get(int zipCode) {
        return cache.get(zipCode);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}

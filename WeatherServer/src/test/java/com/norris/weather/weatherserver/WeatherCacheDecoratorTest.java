package com.norris.weather.weatherserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherCacheDecoratorTest {
    private WeatherCacheDecorator cache = new WeatherCacheDecorator(new CacheInstance());

    @Test
    public void testAddRemove() {
        assertEquals(cache.getCacheSize(), 0);
        cache.put(new WeatherElement(89111, "2.1", "200", System.currentTimeMillis()));
        assertEquals(cache.getCacheSize(), 1);
        WeatherElement element = cache.get(89111);
        assertEquals(element.getWindDirection(), "200");
        assertEquals(element.getWindSpeed(), "2.1");
        String testResponse = "{\"coord\":{\"lon\":-122.41,\"lat\":47.22},\"weather\":[{\"id\":721,\"main\":\"Haze\"," +
        "\"description\":\"haze\",\"icon\":\"50n\"},{\"id\":711,\"main\":\"Smoke\",\"description\":\"smoke\",\"icon\":" +
        "\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":286.35,\"pressure\":1022,\"humidity\":82,\"temp_min\":284.15," +
        "\"temp_max\":289.15},\"visibility\":16093,\"wind\":{\"speed\":2.1,\"deg\":240},\"clouds\":{\"all\":1},\"dt\":" +
                "1534498500,\"sys\":{\"type\":1,\"id\":2946,\"message\":0.055,\"country\":\"US\",\"sunrise\":1534511383," +
        "\"sunset\":1534562189},\"id\":420037668,\"name\":\"Tacoma\",\"cod\":200}";
        element = cache.buildFromString(98404, testResponse);
        cache.put(element);
        assertEquals(cache.getCacheSize(), 2);
        element.setWindDirection("160");
        cache.put(element);
        assertEquals(cache.getCacheSize(), 2);
    }
}
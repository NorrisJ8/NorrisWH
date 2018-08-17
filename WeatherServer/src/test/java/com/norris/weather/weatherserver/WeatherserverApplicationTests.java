package com.norris.weather.weatherserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherserverApplicationTests {

    @Test
    public void weatherElementCreation() {
        WeatherElement element = new WeatherElement(89111, "2.1", "200", System.currentTimeMillis());
        assertEquals(element.getZipCode(), 89111);
        assertEquals(element.getWindSpeed(), "2.1");
        assertEquals(element.getWindDirection(), "200");
        assertTrue(element.getTimeFetched() <= System.currentTimeMillis());
    }
}

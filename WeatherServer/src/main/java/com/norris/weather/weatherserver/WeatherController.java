package com.norris.weather.weatherserver;


import org.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

import java.time.LocalDateTime;

import static com.norris.weather.weatherserver.WeatherserverApplication._cache;

@RestController
@Validated
public class WeatherController implements ErrorController {

    @RequestMapping(value = "/api/v1/wind/{zipCode}", method = RequestMethod.GET, produces = "application/json")
    public WeatherElement getWindResults(@Pattern(regexp="(clear)|(\\d{5})", message = "Zip code must be exactly five digits") @PathVariable String zipCode) {

        if (zipCode.trim().toLowerCase().equals("clear")) {
            clearCache();
            return null;
        }
        else {
            return _cache.get(Integer.parseInt(zipCode));
        }
    }

    private void clearCache() {
        System.out.println(LocalDateTime.now() + " - Purging weather cache");
        _cache.clear();
    }

    @RequestMapping(value = "/error", produces = "application/json")
    public String errorFound() {
        return "Error: Resource not found. URI should be in the format http://localhost:8080/api/v1/wind/{zip code} where {zip code} is exactly 5 digits.";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
package org.infinitec.test.weatherforecast.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.infinitec.test.weatherforecast.model.APIWeatherDetailDTO;
import org.infinitec.test.weatherforecast.model.WeatherForecastDTO;
import org.infinitec.test.weatherforecast.service.api.APIIntegratorService;
import org.infinitec.test.weatherforecast.service.api.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Sameera.Manoraathna
 */

@RestController
@RequestMapping(value = "/data")
@Api(value = "Weather forecast data API", description = "Display the weather forecast details for a given city")
public class WeatherForecastController
{
    @Autowired
    private APIIntegratorService APIIntegratorService;

    @Autowired
    private WeatherForecastService weatherForecastService;

    @ApiOperation(value = "Returns the temperature and pressure forecast for the next 3 days for a given city")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved the temperature and pressure forecast data"),
        @ApiResponse(code = 404, message = "Forecast data for the given city is not found"),
        @ApiResponse(code = 401, message = "You are not authorized to access the URL"),
        @ApiResponse(code = 403, message = "URL is Forbidden")
    })
    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    public WeatherForecastDTO getData(@Valid @PathVariable("city") final String cityName)
    {
        final APIWeatherDetailDTO apiForecastList = APIIntegratorService.getWeatherForecastAPIDetailsByCityName(cityName);
        return weatherForecastService.getWeatherForecastDetails(apiForecastList);
    }
}

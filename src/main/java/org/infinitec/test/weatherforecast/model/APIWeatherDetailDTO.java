package org.infinitec.test.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sameera.Manorathna
 */

public class APIWeatherDetailDTO
{
    @JsonProperty("message")
    private String message;

    @JsonProperty("list")
    private List<APIDailyWeatherForecastDTO> apiDailyWeatherForecastDTOList;

    @JsonIgnore
    private final LocalDateTime currentDate = LocalDateTime.now();

    public String getMessage()
    {
        return message;
    }

    public List<APIDailyWeatherForecastDTO> getApiDailyWeatherForecastDTOList()
    {
        return apiDailyWeatherForecastDTOList;
    }

    public LocalDateTime getCurrentDate()
    {
        return currentDate;
    }
}

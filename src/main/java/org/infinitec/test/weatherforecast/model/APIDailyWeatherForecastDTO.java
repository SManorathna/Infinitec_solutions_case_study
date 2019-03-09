package org.infinitec.test.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.infinitec.test.weatherforecast.util.UtilService;

import java.time.LocalDateTime;

/**
 * @author Sameera.Manorathna
 */

public class APIDailyWeatherForecastDTO
{
    @JsonProperty("dt_txt")
    private String date;

    @JsonProperty("main")
    private APIWeatherForecastAttrDTO apiWeatherForecastAttrDTO;

    public APIDailyWeatherForecastDTO()
    {
    }

    public APIDailyWeatherForecastDTO(final String date, final APIWeatherForecastAttrDTO apiWeatherForecastAttrDTO)
    {
        this.date = date;
        this.apiWeatherForecastAttrDTO = apiWeatherForecastAttrDTO;
    }

    public String getDate()
    {
        return date;
    }

    public APIWeatherForecastAttrDTO getApiWeatherForecastAttrDTO() {
        return apiWeatherForecastAttrDTO;
    }
}

package org.infinitec.test.weatherforecast.service.api;

import org.infinitec.test.weatherforecast.model.WeatherForecastDTO;
import org.infinitec.test.weatherforecast.model.APIWeatherDetailDTO;

/**
 * @author Sameera.Manorathna
 *
 * Crete the weather detail bject based on the API retrieved weather forecast details
 */

public interface WeatherForecastService
{
    /**
     * Retrieve weather forecast details based on API retrieved forecast details
     * @param APIWeatherDetailDTO APIWeatherDetailDTO
     * @return WeatherForecastDTO
     */
    WeatherForecastDTO getWeatherForecastDetails(final APIWeatherDetailDTO APIWeatherDetailDTO);
}

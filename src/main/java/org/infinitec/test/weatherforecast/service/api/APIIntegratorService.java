package org.infinitec.test.weatherforecast.service.api;

import org.infinitec.test.weatherforecast.model.APIWeatherDetailDTO;

/**
 * @author Sameera.Manorathna
 *
 * Retrieve and format details from the external API
 */

public interface APIIntegratorService
{
    /**
     * Return the weather detail mapped object when the city name is given
     * @param cityName cityName
     * @return APIWeatherDetailDTO
     */
    APIWeatherDetailDTO getWeatherForecastAPIDetailsByCityName(String cityName);
}

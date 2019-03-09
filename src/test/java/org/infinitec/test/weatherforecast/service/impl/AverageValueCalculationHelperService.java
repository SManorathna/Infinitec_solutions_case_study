package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.infinitec.test.weatherforecast.model.APIWeatherForecastAttrDTO;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sameera.Manorathna
 *
 * Helper service for handling commpon operations in test classes
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AverageValueCalculationHelperService
{
    public APIDailyWeatherForecastDTO getAPIWeatherForecastAttr(final String dateTime, final double minTemp, final double maxTemp, final double pressure)
    {
        return new APIDailyWeatherForecastDTO(dateTime, getAPIWeatherForecastAttrDTO(minTemp, maxTemp, pressure));
    }

    private APIWeatherForecastAttrDTO getAPIWeatherForecastAttrDTO(final double minTemp, final double maxTemp, final double pressure)
    {
        return new APIWeatherForecastAttrDTO(minTemp, maxTemp, pressure);
    }
}

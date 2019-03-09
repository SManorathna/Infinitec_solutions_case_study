package org.infinitec.test.weatherforecast.service.api;

import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.infinitec.test.weatherforecast.model.TemperatureDetailDTO;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Sameera.Manorathna
 *
 * Calculate average day time and night time temperature values for a given forecasting period
 */

public interface TemperatureCalcService
{
    TemperatureDetailDTO getAverageTemperatureValue(Supplier<Stream<APIDailyWeatherForecastDTO>> apiWeatherForecastData);
}

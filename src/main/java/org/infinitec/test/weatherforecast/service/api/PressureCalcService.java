package org.infinitec.test.weatherforecast.service.api;

import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Sameera.Manorathna
 *
 * Calculate average pressure values for a given forecasting period
 */

public interface PressureCalcService
{
    double getAveragePressureValue(Supplier<Stream<APIDailyWeatherForecastDTO>> apiWeatherForecastData);
}

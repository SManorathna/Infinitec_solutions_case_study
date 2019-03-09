package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.infinitec.test.weatherforecast.service.api.PressureCalcService;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Sameera.Manorathna
 *
 * implementation of {@link PressureCalcService}
 */

@Service
public class PressureCalcServiceImpl implements PressureCalcService
{
    @Override
    public double getAveragePressureValue(final Supplier<Stream<APIDailyWeatherForecastDTO>> apiWeatherForecastData)
    {
        return apiWeatherForecastData.get()
                .mapToDouble(e -> e.getApiWeatherForecastAttrDTO().getPressure())
                .average().orElse(0.0);
    }
}

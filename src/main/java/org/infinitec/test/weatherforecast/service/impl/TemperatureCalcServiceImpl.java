package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.constants.Constants;
import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.infinitec.test.weatherforecast.model.TemperatureDetailDTO;
import org.infinitec.test.weatherforecast.service.api.TemperatureCalcService;
import org.infinitec.test.weatherforecast.util.UtilService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sameera.Manorathna
 *
 * implementation of {@link TemperatureCalcService}
 */

@Service
public class TemperatureCalcServiceImpl implements TemperatureCalcService
{
    @Override
    public TemperatureDetailDTO getAverageTemperatureValue(final Supplier<Stream<APIDailyWeatherForecastDTO>> apiWeatherForecastData)
    {
        UtilService utilService = new UtilService();
        final Map<Boolean, Double> temperatureRangeMap = apiWeatherForecastData.get()
                .collect(Collectors.groupingBy(e -> isDayTimeNow(utilService.getLocalDateFromStringDate(e.getDate())),
                                               Collectors.averagingDouble(e -> getAverageTempWithinDay(e.getApiWeatherForecastAttrDTO().getMaxTemp(),
                                                                                                       e.getApiWeatherForecastAttrDTO().getMinTemp()))));

        final double celsiusConvertedDayTemp = utilService.getCelsiusConvertedTemperature(temperatureRangeMap.getOrDefault(true, 0.0));
        final double celsiusConvertedNightTemp = utilService.getCelsiusConvertedTemperature(temperatureRangeMap.getOrDefault(false, 0.0));

        return new TemperatureDetailDTO(utilService.getStringConvertedValue(celsiusConvertedDayTemp),
                                        utilService.getStringConvertedValue(celsiusConvertedNightTemp));
    }

    private boolean isDayTimeNow(final LocalDateTime date)
    {
        return isAfterOrEqualToDayTime(date) && isBeforeOrEqualToNightTime(date);
    }

    private boolean isAfterOrEqualToDayTime(final LocalDateTime date)
    {
        return date.toLocalTime().isAfter(LocalTime.of(Constants.DAY_TIME_STARTING_HOUR, Constants.DAY_TIME_STARTING_MINUTE))
                || date.toLocalTime().equals(LocalTime.of(Constants.DAY_TIME_STARTING_HOUR, Constants.DAY_TIME_STARTING_MINUTE));
    }

    private boolean isBeforeOrEqualToNightTime(final LocalDateTime date)
    {
        return date.toLocalTime().isBefore(LocalTime.of(Constants.NIGHT_TIME_STARTING_HOUR, Constants.NIGHT_TIME_STARTING_MINUTE))
                || date.toLocalTime().equals(LocalTime.of(Constants.NIGHT_TIME_STARTING_HOUR, Constants.NIGHT_TIME_STARTING_MINUTE));
    }

    private double getAverageTempWithinDay(final double minTemp, final double maxTemp)
    {
        return (minTemp + maxTemp)/2;
    }
}

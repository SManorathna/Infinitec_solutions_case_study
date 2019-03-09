package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.constants.Constants;
import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.infinitec.test.weatherforecast.model.APIWeatherDetailDTO;
import org.infinitec.test.weatherforecast.model.TemperatureDetailDTO;
import org.infinitec.test.weatherforecast.model.WeatherForecastDTO;
import org.infinitec.test.weatherforecast.service.api.PressureCalcService;
import org.infinitec.test.weatherforecast.service.api.TemperatureCalcService;
import org.infinitec.test.weatherforecast.service.api.WeatherForecastService;
import org.infinitec.test.weatherforecast.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Sameera.Manorathna
 *
 * implementation of {@link WeatherForecastService}
 */

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService
{
    @Autowired
    private TemperatureCalcService temperatureCalcService;

    @Autowired
    private PressureCalcService pressureCalcService;

    @Override
    public WeatherForecastDTO getWeatherForecastDetails(final APIWeatherDetailDTO APIWeatherDetailDTO)
    {
        UtilService utilService = new UtilService();
        final LocalDateTime currentDate = APIWeatherDetailDTO.getCurrentDate();
        final Supplier<Stream<APIDailyWeatherForecastDTO>> expectingForecastDayFilteredList =
                () -> APIWeatherDetailDTO.getApiDailyWeatherForecastDTOList()
                        .stream()
                        .filter(e -> isDateWithinExpectingForecastDays(utilService.getLocalDateFromStringDate(e.getDate()), currentDate));

        final double averagePressure = pressureCalcService.getAveragePressureValue(expectingForecastDayFilteredList);
        final TemperatureDetailDTO averageTemperature = temperatureCalcService.getAverageTemperatureValue(expectingForecastDayFilteredList);

        WeatherForecastDTO weatherForecast = new WeatherForecastDTO();
        weatherForecast.setCurrentDateTime(utilService.getStringDateFromLocalDate(currentDate));
        weatherForecast.setNoOfForecastingDays(Constants.NO_OF_EXPECTED_FORECAST_DAYS);
        weatherForecast.setAveragePressure(utilService.getStringConvertedValue(averagePressure));
        weatherForecast.setTemperatureDetails(averageTemperature);

        return weatherForecast;
    }

    private boolean isDateWithinExpectingForecastDays(final LocalDateTime date, final LocalDateTime currentDate)
    {
        return date.isAfter(currentDate) && date.isBefore(getForecastEndDate(currentDate));
    }

    private LocalDateTime getForecastEndDate(final LocalDateTime currentDate)
    {
        return currentDate.plusDays(Constants.NO_OF_EXPECTED_FORECAST_DAYS).withHour(0).withMinute(0).withSecond(0);
    }
}

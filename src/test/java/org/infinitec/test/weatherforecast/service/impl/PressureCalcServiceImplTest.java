package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Sameera.Manorathna
 *
 * Test of {@link PressureCalcServiceImpl}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PressureCalcServiceImplTest
{
    private PressureCalcServiceImpl averagePressureCalcService;

    private AverageValueCalculationHelperService helperService;

    private static final double epsilon = 1;

    @Before
    public void setUp()
    {
        averagePressureCalcService = new PressureCalcServiceImpl();
        helperService = new AverageValueCalculationHelperService();
    }

    @Test
    public void test_getAveragePressureValue_for_multiple_weatherforecast_values()
    {
        final double[] pressureList = new double[]{1020.5, 1042.36, 1200.63, 1322.56, 1256.3, 1563.3, 1432.6, 1265.6, 1526.5, 1632.02, 1823.02, 1263, 2253.2};

        List<APIDailyWeatherForecastDTO> weatherForecastList =
                Arrays.asList(helperService.getAPIWeatherForecastAttr("2018-02-02 01:20:00", 820.3, 882.36, pressureList[0]),
                        helperService.getAPIWeatherForecastAttr("2018-02-03 05:35:00", 986, 852, pressureList[1]),
                        helperService.getAPIWeatherForecastAttr("2018:02:04 12, 25:00", 955, 874, pressureList[2]),
                        helperService.getAPIWeatherForecastAttr("2018:02:05 08, 12:00", 955, 874, pressureList[3]),
                        helperService.getAPIWeatherForecastAttr("2018:02:05 06, 36:00", 955, 874, pressureList[4]),
                        helperService.getAPIWeatherForecastAttr("2018:02:05 19, 52:00", 955, 874, pressureList[5]),
                        helperService.getAPIWeatherForecastAttr("2018:02:05 09, 8:00", 955, 874, pressureList[6]),
                        helperService.getAPIWeatherForecastAttr("2018:02:06 22, 41:00", 955, 874, pressureList[7]),
                        helperService.getAPIWeatherForecastAttr("2018:02:06 23, 20:00", 955, 874, pressureList[8]),
                        helperService.getAPIWeatherForecastAttr("2018:02:06 14, 2:00", 955, 874, pressureList[9]),
                        helperService.getAPIWeatherForecastAttr("2018:02:07 03, 6:00", 955, 874, pressureList[10]),
                        helperService.getAPIWeatherForecastAttr("2018:02:07 08, 56:00", 955, 874, pressureList[11]),
                        helperService.getAPIWeatherForecastAttr("2018:02:07 08, 56:00", 955, 874, pressureList[12]));

        final Supplier<Stream<APIDailyWeatherForecastDTO>> supplier = () -> weatherForecastList.stream();
        final double result = averagePressureCalcService.getAveragePressureValue(supplier);
        final double expected = Arrays.stream(pressureList).average().getAsDouble();

        Assert.assertEquals(result, expected, epsilon);
    }

    @Test
    public void test_getAveragePressureValue_return_zero_for_empty_list()
    {
        List<APIDailyWeatherForecastDTO> weatherForecastList = Collections.emptyList();

        final Supplier<Stream<APIDailyWeatherForecastDTO>> supplier = () -> weatherForecastList.stream();
        final double result = averagePressureCalcService.getAveragePressureValue(supplier);

        Assert.assertEquals(result, 0.0, epsilon);
    }
}

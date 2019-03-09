package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.model.APIDailyWeatherForecastDTO;
import org.infinitec.test.weatherforecast.model.TemperatureDetailDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
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
public class TemperatureCalcServiceImplTest
{
    private TemperatureCalcServiceImpl averageTemperatureCalcService;

    private AverageValueCalculationHelperService helperService;

    private static final double epsilon = 1;

    private static final double kelvinCelsiusConstant = 273.0;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        helperService = new AverageValueCalculationHelperService();
        averageTemperatureCalcService = new TemperatureCalcServiceImpl();
    }
    
    @Test
    public void test_getAverageTemperatureValue_for_multiple_weatherforecast_values()
    {
        final double[] minTempValue = new double[]{812.2, 842.5, 823.25, 822, 813.56, 866.3, 800.36, 810.3, 866.25, 900.23, 900.22, 533.6};
        final double[] maxTempValue = new double[]{833.97, 866.32, 844.12, 846.2, 877.2, 899, 844.96, 863.56, 899.2, 915, 942.36, 633.2};

        List<APIDailyWeatherForecastDTO> weatherForecastList =
                Arrays.asList(helperService.getAPIWeatherForecastAttr("2018-02-02 01:20:00", minTempValue[0], maxTempValue[0], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-03 05:35:00", minTempValue[1], maxTempValue[1], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-04 12:25:00", minTempValue[2], maxTempValue[2], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-05 08:12:00", minTempValue[3], maxTempValue[3], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-05 06:36:00", minTempValue[4], maxTempValue[4], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-05 19:52:00", minTempValue[5], maxTempValue[5], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-05 09:08:00", minTempValue[6], maxTempValue[6], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-06 22:41:00", minTempValue[7], maxTempValue[7], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-06 23:20:00", minTempValue[8], maxTempValue[8], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-06 14:02:00", minTempValue[9], maxTempValue[9], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-07 03:06:00", minTempValue[10], maxTempValue[10], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-07 08:56:00", minTempValue[11], maxTempValue[11], 1026.36));

        final Supplier<Stream<APIDailyWeatherForecastDTO>> supplier = () -> weatherForecastList.stream();
        final TemperatureDetailDTO result = averageTemperatureCalcService.getAverageTemperatureValue(supplier);

        final int[] dayIndexes = new int[]{2, 3, 4, 6, 9, 11};
        final int[] nightIndexes = new int[]{0, 1, 5, 7, 8, 10};

        final double dayCount = getCount(dayIndexes, minTempValue, maxTempValue);
        final double nightCount = getCount(nightIndexes, minTempValue, maxTempValue);

        final double averageDayTemp = Double.valueOf(result.getAverageDayTimeTemperature());
        final double averageNightTemp = Double.valueOf(result.getAverageNightTimeTemperature());

        final double expectedDayTemp = dayCount/dayIndexes.length;
        final double expectedNightTemp = nightCount/nightIndexes.length;

        Assert.assertEquals(averageDayTemp, getCelsiusValue(expectedDayTemp), epsilon);
        Assert.assertEquals(averageNightTemp, getCelsiusValue(expectedNightTemp), epsilon);
    }

    @Test
    public void test_getAverageTemperatureValue_for_day_time_night_time_magin_cases()
    {
        final double[] minTempValue = new double[]{812.2, 842.5, 823.25, 822};
        final double[] maxTempValue = new double[]{833.97, 866.32, 844.12, 846.2};

        List<APIDailyWeatherForecastDTO> weatherForecastList =
                Arrays.asList(helperService.getAPIWeatherForecastAttr("2018-02-02 05:59:00", minTempValue[0], maxTempValue[0], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-03 06:00:00", minTempValue[1], maxTempValue[1], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-04 17:59:00", minTempValue[2], maxTempValue[2], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-05 18:00:00", minTempValue[3], maxTempValue[3], 1026.36));

        final Supplier<Stream<APIDailyWeatherForecastDTO>> supplier = () -> weatherForecastList.stream();
        final TemperatureDetailDTO result = averageTemperatureCalcService.getAverageTemperatureValue(supplier);

        final int[] dayIndexes = new int[]{1, 2};
        final int[] nightIndexes = new int[]{0, 3};

        final double dayCount = getCount(dayIndexes, minTempValue, maxTempValue);
        final double nightCount = getCount(nightIndexes, minTempValue, maxTempValue);

        final double averageDayTemp = Double.valueOf(result.getAverageDayTimeTemperature());
        final double averageNightTemp = Double.valueOf(result.getAverageNightTimeTemperature());

        final double expectedDayTemp = dayCount/dayIndexes.length;
        final double expectedNightTemp = nightCount/nightIndexes.length;

        Assert.assertEquals(averageDayTemp, getCelsiusValue(expectedDayTemp), epsilon);
        Assert.assertEquals(averageNightTemp, getCelsiusValue(expectedNightTemp), epsilon);
    }

    @Test
    public void test_getAverageTemperatureValue_for_empty_value_set_for_day_time()
    {
        final double[] minTempValue = new double[]{812.2, 842.5, 823.25, 822};
        final double[] maxTempValue = new double[]{833.97, 866.32, 844.12, 846.2};

        List<APIDailyWeatherForecastDTO> weatherForecastList =
                Arrays.asList(helperService.getAPIWeatherForecastAttr("2018-02-02 05:59:00", minTempValue[0], maxTempValue[0], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-03 04:00:00", minTempValue[1], maxTempValue[1], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-04 19:59:00", minTempValue[2], maxTempValue[2], 1026.36),
                        helperService.getAPIWeatherForecastAttr("2018-02-05 18:00:00", minTempValue[3], maxTempValue[3], 1026.36));

        final Supplier<Stream<APIDailyWeatherForecastDTO>> supplier = () -> weatherForecastList.stream();
        final TemperatureDetailDTO result = averageTemperatureCalcService.getAverageTemperatureValue(supplier);

        final int[] nightIndexes = new int[]{0, 1, 2, 3};
        final double nightCount = getCount(nightIndexes, minTempValue, maxTempValue);

        final double averageDayTemp = Double.valueOf(result.getAverageDayTimeTemperature());
        final double averageNightTemp = Double.valueOf(result.getAverageNightTimeTemperature());

        final double expectedNightTemp = nightCount/nightIndexes.length;

        Assert.assertEquals(averageDayTemp, getCelsiusValue(0.0), epsilon);
        Assert.assertEquals(averageNightTemp, getCelsiusValue(expectedNightTemp), epsilon);
    }

    private double getCount(int[] indexArray, double[] minTempValue, double[] maxTempValue)
    {
        double count = 0;
        for(int i = 0; i < indexArray.length; i++)
        {
            count += (minTempValue[indexArray[i]] + maxTempValue[indexArray[i]])/2;
        }

        return count;
    }

    private double getCelsiusValue(final double kelvinValue)
    {
        return kelvinValue - kelvinCelsiusConstant;
    }
}

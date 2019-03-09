package org.infinitec.test.weatherforecast.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * @author Sameera.Manorathna
 *
 * Test of {@link UtilService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilServiceTest
{
    private UtilService utilService;
    @Before
    public void setUp()
    {
        utilService = new UtilService();
    }

    @Test
    public void test_getLocalDateFromStringDate_given_a_valid_date_time()
    {
        final LocalDateTime result = utilService.getLocalDateFromStringDate("2016-02-05 12:55:23");

        Assert.assertEquals(result.getYear(), 2016);
        Assert.assertEquals(result.getMonthValue(), 2);
        Assert.assertEquals(result.getDayOfMonth(), 5);
        Assert.assertEquals(result.getHour(), 12);
        Assert.assertEquals(result.getMinute(), 55);
        Assert.assertEquals(result.getSecond(), 23);
    }

    @Test(expected=DateTimeParseException.class)
    public void test_getLocalDateFromStringDate_throw_exception_given_invalid_time()
    {
        final LocalDateTime result = utilService.getLocalDateFromStringDate("2016-02-05");
    }

    @Test(expected=DateTimeParseException.class)
    public void test_getLocalDateFromStringDate_throw_exception_given_invalid_date()
    {
        final LocalDateTime result = utilService.getLocalDateFromStringDate("2016-2-5 12:55:23");
    }

    @Test
    public void test_getStringDateFromLocalDate_for_given_a_valid_date_time()
    {
        final String result = utilService.getStringDateFromLocalDate(LocalDateTime.of(2018, 2, 2, 1, 20));
        Assert.assertEquals(result, "2018-02-02 01:20:00");
    }

    @Test(expected=DateTimeException.class)
    public void test_getStringDateFromLocalDate_for_given_invalid_date_time()
    {
        utilService.getStringDateFromLocalDate(LocalDateTime.of(2018, 0, 2, 1, 20));
    }

    @Test
    public void test_getStringConvertedValue_for_given_valid_double()
    {
        final String result = utilService.getStringConvertedValue(12.256369);
        Assert.assertEquals(result, "12.26");
    }

    @Test
    public void test_getStringConvertedValue_for_given_valid_integer()
    {
        final String result = utilService.getStringConvertedValue(13);
        Assert.assertEquals(result, "13.00");
    }

    @Test
    public void test_getStringConvertedValue_for_given_negative_value()
    {
        final String result = utilService.getStringConvertedValue(-15.36874);
        Assert.assertEquals(result, "0.0");
    }
}

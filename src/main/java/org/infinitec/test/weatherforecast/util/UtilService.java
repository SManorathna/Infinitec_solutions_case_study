package org.infinitec.test.weatherforecast.util;

import org.infinitec.test.weatherforecast.constants.Constants;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Sameera.Manorathna
 *
 * Util service
 */

@Service
public class UtilService
{
    /**
     * Convert string date time to LocalDateTime
     * @param dateTime
     * @return
     */
    public LocalDateTime getLocalDateFromStringDate(final String dateTime)
    {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Convert LocalDateTime to string date time
     * @param dateTime
     * @return
     */
    public String getStringDateFromLocalDate(final LocalDateTime dateTime)
    {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Convert rounded double value to a string value
     * @param value
     * @return
     */
    public String getStringConvertedValue(final double value)
    {
        final DecimalFormat dec = new DecimalFormat("#.00");
        return dec.format(value);
    }

    /**
     * Convert Kelvin temperature to Celsius temperature
     *
     * @param kelvinTemperature
     * @return
     */
    public double getCelsiusConvertedTemperature(double kelvinTemperature)
    {
        return kelvinTemperature - Constants.KELVIN_CELSIUS_CONSTANT;
    }
}

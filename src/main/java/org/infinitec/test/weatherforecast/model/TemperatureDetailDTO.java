package org.infinitec.test.weatherforecast.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sameera.Manorathna
 */

public class TemperatureDetailDTO
{
    @ApiModelProperty(notes = "Average temperature of the day time (6.00 AM to 6.00 PM)")
    private String averageDayTimeTemperature;

    @ApiModelProperty(notes = "Average temperature of night time (6.00 PM to 6.00 AM)")
    private String averageNightTimeTemperature;

    public TemperatureDetailDTO(final String averageDayTimeTemperature, final String averageNightTimeTemperature)
    {
        this.averageDayTimeTemperature = averageDayTimeTemperature;
        this.averageNightTimeTemperature = averageNightTimeTemperature;
    }

    public String getAverageDayTimeTemperature()
    {
        return averageDayTimeTemperature;
    }

    public String getAverageNightTimeTemperature()
    {
        return averageNightTimeTemperature;
    }
}

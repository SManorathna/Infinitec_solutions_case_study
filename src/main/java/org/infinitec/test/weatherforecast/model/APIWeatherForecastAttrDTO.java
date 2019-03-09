package org.infinitec.test.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sameera.Manorathna
 */

public class APIWeatherForecastAttrDTO
{
    @JsonProperty("temp_min")
    private double minTemp;

    @JsonProperty("temp_max")
    private double maxTemp;

    @JsonProperty("pressure")
    private double pressure;

    public APIWeatherForecastAttrDTO()
    {
    }

    public APIWeatherForecastAttrDTO(double minTemp, double maxTemp, double pressure)
    {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
    }

    public double getPressure()
    {
        return pressure;
    }

    public double getMinTemp()
    {
        return minTemp;
    }

    public double getMaxTemp()
    {
        return maxTemp;
    }
}

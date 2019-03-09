package org.infinitec.test.weatherforecast.model;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sameera.Manorathna
 */

@XmlRootElement
public class WeatherForecastDTO
{
    @ApiModelProperty(notes = "Current time")
    private String currentDateTime;

    @ApiModelProperty(notes = "Number of future forecasting days")
    private int noOfForecastingDays;

    @ApiModelProperty(notes = "Average pressure of the forecasting days")
    private String averagePressure;

    @ApiModelProperty(notes = "Temperature detail area")
    private TemperatureDetailDTO temperatureDetails;

    public String getCurrentDateTime()
    {
        return currentDateTime;
    }

    public String getAveragePressure()
    {
        return averagePressure;
    }

    public int getNoOfForecastingDays()
    {
        return noOfForecastingDays;
    }

    public TemperatureDetailDTO getTemperatureDetails()
    {
        return temperatureDetails;
    }

    public void setCurrentDateTime(String currentDateTime)
    {
        this.currentDateTime = currentDateTime;
    }

    public void setNoOfForecastingDays(int noOfForecastingDays) {
        this.noOfForecastingDays = noOfForecastingDays;
    }

    public void setAveragePressure(String averagePressure)
    {
        this.averagePressure = averagePressure;
    }

    public void setTemperatureDetails(TemperatureDetailDTO temperatureDetails)
    {
        this.temperatureDetails = temperatureDetails;
    }
}

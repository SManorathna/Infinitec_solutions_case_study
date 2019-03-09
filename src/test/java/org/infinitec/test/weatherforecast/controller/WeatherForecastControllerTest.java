package org.infinitec.test.weatherforecast.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.infinitec.test.weatherforecast.model.APIWeatherDetailDTO;
import org.infinitec.test.weatherforecast.model.TemperatureDetailDTO;
import org.infinitec.test.weatherforecast.model.WeatherForecastDTO;
import org.infinitec.test.weatherforecast.service.api.APIIntegratorService;
import org.infinitec.test.weatherforecast.service.api.WeatherForecastService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Sameera.Manorathna
 *
 * Test of {@link WeatherForecastController}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastControllerTest
{
    @InjectMocks
    private WeatherForecastController controller;

    @Mock
    private APIIntegratorService APIIntegratorService;

    @Mock
    private WeatherForecastService weatherForecastService;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test_controller_end_point() throws Exception
    {
        WeatherForecastDTO weatherForecast = new WeatherForecastDTO();
        weatherForecast.setAveragePressure("1056.65");
        weatherForecast.setNoOfForecastingDays(3);
        weatherForecast.setCurrentDateTime(LocalDateTime.now().toString());
        weatherForecast.setTemperatureDetails(new TemperatureDetailDTO("812.23", "922.56"));

        when(APIIntegratorService.getWeatherForecastAPIDetailsByCityName(anyString())).thenReturn(mock(APIWeatherDetailDTO.class));
        when(weatherForecastService.getWeatherForecastDetails(any(APIWeatherDetailDTO.class))).thenReturn(weatherForecast);

        final String uri = "/data/London";
        RequestBuilder builder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(builder).andReturn();
        String expectedJson = mapToJson(weatherForecast);
        String outputJson = result.getResponse().getContentAsString();

        Assert.assertEquals(outputJson, expectedJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

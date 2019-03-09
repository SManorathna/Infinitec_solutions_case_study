package org.infinitec.test.weatherforecast.service.impl;

import org.infinitec.test.weatherforecast.constants.Constants;
import org.infinitec.test.weatherforecast.exceptionhandler.APIResponseErrorHandler;
import org.infinitec.test.weatherforecast.exceptionhandler.DataNotFoundException;
import org.infinitec.test.weatherforecast.model.APIWeatherDetailDTO;
import org.infinitec.test.weatherforecast.service.api.APIIntegratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

/**
 * @author Sameera.Manorathna
 *
 * implementation of {@link APIIntegratorService}
 */

@Service
public class APIIntegratorServiceImpl implements APIIntegratorService
{
    @Value("${openweathermap.endpoint}")
    private String endPoint;

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    @Override
    public APIWeatherDetailDTO getWeatherForecastAPIDetailsByCityName(final String cityName)
    {
        final HttpEntity<String> entity = new HttpEntity<>(getHttpHeaders());
        final String uriString = getParameterizedUriString(cityName);
        final ResponseEntity<APIWeatherDetailDTO> response = getRestTemplate()
                .exchange(uriString, HttpMethod.GET, entity, APIWeatherDetailDTO.class);

        if(response.getStatusCode() == HttpStatus.OK)
        {
            return response.getBody();
        }
        else
        {
            throw new DataNotFoundException(response.getBody().getMessage());
        }
    }

    private HttpHeaders getHttpHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("APIKey", apiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    private String getParameterizedUriString(final String cityName)
    {
        return UriComponentsBuilder.fromUriString(endPoint)
                .queryParam(Constants.API_KEY_TEXT, apiKey)
                .queryParam(Constants.CITY_NAME_QUERY_PARAM_TEXT, cityName).toUriString();
    }

    private RestTemplate getRestTemplate()
    {
        RestTemplateBuilder template = new RestTemplateBuilder();
        return template.errorHandler(new APIResponseErrorHandler()).build();
    }
}

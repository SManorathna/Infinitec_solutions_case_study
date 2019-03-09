package org.infinitec.test.weatherforecast.exceptionhandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infinitec.test.weatherforecast.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author Sameera.Manoraathna
 *
 * Validate API response
 */

public class APIResponseErrorHandler implements ResponseErrorHandler
{
    private static final Log logger = LogFactory.getLog(APIResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException
    {
        return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException
    {
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)
        {
            logger.error(response.getStatusCode() + " " + Constants.SERVER_ERROR);
        }
        else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
        {
            logger.error("Status code: " + response.getStatusCode());
            logger.error("Response: " + response.getStatusText());
            logger.error("Body: " + response.getBody());
        }
    }
}

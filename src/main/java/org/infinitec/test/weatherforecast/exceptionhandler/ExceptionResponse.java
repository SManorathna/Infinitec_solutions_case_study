package org.infinitec.test.weatherforecast.exceptionhandler;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * @author Sameera.Manoraathna
 *
 * Error response object
 */

@XmlRootElement
public class ExceptionResponse
{
    private String timestamp;
    private int errorCode;
    private String errorMessage;

    public ExceptionResponse(final String timestamp,
                             final int errorCode,
                             final String errorMessage)
    {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}

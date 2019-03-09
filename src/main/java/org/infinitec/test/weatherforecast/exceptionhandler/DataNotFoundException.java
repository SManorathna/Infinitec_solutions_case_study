package org.infinitec.test.weatherforecast.exceptionhandler;

/**
 * @author Sameera.Manoraathna
 *
 * Throws when results for input data are not found
 */

public class DataNotFoundException extends RuntimeException
{
    public DataNotFoundException(final String message)
    {
        super(message);
    }
}

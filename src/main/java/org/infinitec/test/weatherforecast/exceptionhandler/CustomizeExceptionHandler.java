package org.infinitec.test.weatherforecast.exceptionhandler;

import org.infinitec.test.weatherforecast.constants.Constants;
import org.infinitec.test.weatherforecast.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Sameera.Manoraathna
 *
 * Enable exception handling for all the controllers
 */

@RestControllerAdvice("org.infinitec.test.weatherforecast.controller")
@RestController
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler
{
    @Autowired
    private UtilService utilService;

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(Exception ex, WebRequest req)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(utilService.getStringDateFromLocalDate(LocalDateTime.now()),
                                                                    HttpStatus.NOT_FOUND.value(),
                                                                    ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}

package io.spring.workshop.tradingservice.controller.handler;

import io.spring.workshop.tradingservice.exception.TickerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TickerNotFoundExceptionHandler {

    @ExceptionHandler(TickerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handle(TickerNotFoundException ex) {
    }
}

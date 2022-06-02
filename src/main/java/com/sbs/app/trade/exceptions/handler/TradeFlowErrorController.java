package com.sbs.app.trade.exceptions.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.sbs.app.trade.exceptions.InvalidTradeException;
import com.sbs.app.trade.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class TradeFlowErrorController {
	@ExceptionHandler(InvalidTradeException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage handleNotFoundException(InvalidTradeException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return message;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
				ex.getMessage(), request.getDescription(false));
		return message;
	}
}

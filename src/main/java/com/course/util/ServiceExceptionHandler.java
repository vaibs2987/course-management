package com.course.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	ApiResponse<String> handleControllerException(HttpServletRequest req, Throwable ex) {

		Exception errorResponse = new Exception(ex);

		Throwable throwable = errorResponse.getCause();
		String msg = "Some exception occured";
		String errorMsg = "Api fails";
		RuntimeException runtimeException = new RuntimeException(throwable);
		if (runtimeException != null) {
			msg = runtimeException.getMessage();
			errorMsg = runtimeException.getMessage();
		}

		ApiResponse<String> apiResponse = new ApiResponse<String>(msg);
		apiResponse.setMessage(errorMsg);

		apiResponse.setStatus(ResponseCode.EXCEPTION_OCCURRED);
		LOGGER.error("Exception occured  :" + errorResponse.getMessage());
		return apiResponse;
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Map<String, Object> responseBody = new HashMap<>();
		LOGGER.error("The URL you have reached is not in service at this time : " + ex.getRequestURL());
		responseBody.put("resource", null);
		responseBody.put("status", ResponseCode.REQUEST_NOT_FOUND);
		responseBody.put("message", "The URL you have reached is not in service at this time.");
		return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
	}

}
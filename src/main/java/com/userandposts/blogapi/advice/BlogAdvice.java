package com.userandposts.blogapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.userandposts.blogapi.exception.BlogException;

@ControllerAdvice
public class BlogAdvice {
	
	@ResponseBody
	@ExceptionHandler(BlogException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String blogExceptionHandler(BlogException ex) {
		return ex.getMessage();
	}
}

package com.userandposts.blogapi.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.userandposts.blogapi.exception.BlogException;

public class BlogAdviceTest {

	
	@Test
	public void testblogExceptionHandler() {
		BlogException blogException = new BlogException("exception");
		
		String message = new BlogAdvice().blogExceptionHandler(blogException);
		assertEquals("Exception in user and posts : exception", message);
	}
}

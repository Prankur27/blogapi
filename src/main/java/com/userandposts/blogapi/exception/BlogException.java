package com.userandposts.blogapi.exception;

public class BlogException extends Exception{
	
	public BlogException(String message) {
		super("Exception in user and posts : "+message);
	}

}

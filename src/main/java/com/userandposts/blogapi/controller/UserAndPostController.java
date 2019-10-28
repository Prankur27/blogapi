package com.userandposts.blogapi.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userandposts.blogapi.exception.BlogException;
import com.userandposts.blogapi.model.Comment;
import com.userandposts.blogapi.model.Post;
import com.userandposts.blogapi.model.UserDetails;

@RestController
public class UserAndPostController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final static String USER_URL = "https://jsonplaceholder.typicode.com/users";
	private final static String POST_URL = "https://jsonplaceholder.typicode.com/posts";
	private final static String COMMENT_URL = "https://jsonplaceholder.typicode.com/comments?postId=";
	
	private static final Logger logger = LogManager.getLogger(UserAndPostController.class);
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/usersandposts", produces="application/json")
	public @ResponseBody List<UserDetails> getUserAndPosts() throws BlogException {
		List<UserDetails> userList = null;
		try {
			UserDetails[] users = restTemplate.getForObject(USER_URL, UserDetails[].class);
			userList = Arrays.asList(users);
			if(null != userList && !userList.isEmpty()) {
		       
		        Post[] posts = restTemplate.getForObject(POST_URL, Post[].class);
		        List<Post> postList = Arrays.asList(posts);
		        
		        mapPostInUser(userList, postList);
		        
		    }else {
		    	logger.error("Exception in user and post api, NO USER PRESENT");
		    	throw new BlogException("Error in retreiving user information");
		    }
		}catch(Exception ex) {
			logger.error("Exception in user and post api: "+ex.getMessage());
			throw new BlogException("Error in retreiving user information "+ex.getMessage());
		}
	    
		return userList;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/posts", produces="application/json")
	public @ResponseBody List<Comment> getComments(@RequestParam("postId") String postId) throws BlogException {
		List<Comment> commentList = null;
		try {
			Comment[] comments = restTemplate.getForObject(COMMENT_URL+postId, Comment[].class);
			commentList = Arrays.asList(comments);
		    if(null == commentList || commentList.isEmpty()) {
		        logger.error("Exception in comments api, No Comments for post "+postId);
		    	throw new BlogException("Error in retreiving comments");
		    }
		}catch(Exception ex){
			logger.error("Exception in comments api, exception: "+ex.getMessage());
			throw new BlogException("Error in retreiving comments "+ ex.getMessage());
		}
	    
	    return commentList;
	}
	
	public void mapPostInUser(List<UserDetails> userDetails, List<Post> posts) {
		userDetails.stream().forEach((user) ->{
			posts.stream().forEach((post) ->{
				if(user.getId() == post.getUserId()) {
					user.getPost().add(post);
				}
			});
		});
	}

}

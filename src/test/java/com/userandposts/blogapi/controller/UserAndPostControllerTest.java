package com.userandposts.blogapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.userandposts.blogapi.exception.BlogException;
import com.userandposts.blogapi.model.Comment;
import com.userandposts.blogapi.model.Post;
import com.userandposts.blogapi.model.UserDetails;

@RunWith(MockitoJUnitRunner.class)
public class UserAndPostControllerTest {
	@Mock
	private RestTemplate restTemplateMock;
	
	@InjectMocks
	private UserAndPostController userPostController;

	private final static String USER_URL = "https://jsonplaceholder.typicode.com/users";
	private final static String POST_URL = "https://jsonplaceholder.typicode.com/posts";
	private final static String COMMENT_URL = "https://jsonplaceholder.typicode.com/comments?postId=";
	
	@Test
	public void testGetUserAndPosts_WithOnePostForUser() throws BlogException {
		UserDetails[] users = new UserDetails[1]; 
		UserDetails userDetails = new UserDetails();
		userDetails.setId(123);
		userDetails.setName("test user");
		users[0] = userDetails;
		
		Post[] posts = new Post[1];
		Post post = new Post();
		post.setUserId(123);
		post.setTitle("My Post");
		posts[0] = post;
		
	
		Mockito.when(restTemplateMock.getForObject(USER_URL, UserDetails[].class)).thenReturn(users);
		Mockito.when(restTemplateMock.getForObject(POST_URL, Post[].class)).thenReturn(posts);
		
		List<UserDetails> responseList = userPostController.getUserAndPosts();
		assertEquals(Integer.valueOf(123), responseList.get(0).getPost().get(0).getUserId());

		
	}
	
	@Test
	public void testGetUserAndPosts_WithNoPostForUsersReturned() throws BlogException {
		UserDetails[] users = new UserDetails[1]; 
		UserDetails userDetails = new UserDetails();
		userDetails.setId(123);
		userDetails.setName("test user");
		users[0] = userDetails;
		
		Post[] posts = new Post[1];
		Post post = new Post();
		post.setUserId(1234);
		post.setTitle("My Post");
		posts[0] = post;
		
	
		Mockito.when(restTemplateMock.getForObject(USER_URL, UserDetails[].class)).thenReturn(users);
		Mockito.when(restTemplateMock.getForObject(POST_URL, Post[].class)).thenReturn(posts);
		
		List<UserDetails> responseList = userPostController.getUserAndPosts();
		assertTrue(responseList.get(0).getPost().isEmpty());
		
	}
	
	@Test(expected=BlogException.class)
	public void testGetUserAndPosts_WithEmptyUserList() throws BlogException {
		UserDetails[] users1 = new UserDetails[1];
		Mockito.when(restTemplateMock.getForObject(USER_URL, UserDetails[].class)).thenReturn(users1);
		List<UserDetails> responseUserDetails = userPostController.getUserAndPosts();
		
	}
	
	@Test(expected=BlogException.class)
	public void testGetUserAndPosts_NullUser() throws BlogException {
		UserDetails[] users1 = new UserDetails[1];
		Mockito.when(restTemplateMock.getForObject(USER_URL, UserDetails[].class)).thenReturn(null);
		List<UserDetails> responseUserDetails = userPostController.getUserAndPosts();
			
	}
	
	@Test
	public void testComments() throws BlogException {
		Comment[] comments = new Comment[1];
		Comment comment = new Comment();
		comment.setId(1234);
		comment.setPostId(10);
		comment.setBody("This is comment");
		comments[0] = comment;
		
		Mockito.when(restTemplateMock.getForObject(COMMENT_URL+10, Comment[].class)).thenReturn(comments);
		
		List<Comment> commentResponse = userPostController.getComments("10");
		assertEquals(Integer.valueOf(1234), commentResponse.get(0).getId());
		assertEquals("This is comment", commentResponse.get(0).getBody());
		
	}
	
	@Test(expected=BlogException.class)
	public void testComments_NullComment() throws BlogException {
		Comment[] comments = new Comment[1];
		
		Mockito.when(restTemplateMock.getForObject(COMMENT_URL+10, Comment[].class)).thenReturn(null);
		
		List<Comment> commentResponse = userPostController.getComments("10");
	}
	
	
	

}









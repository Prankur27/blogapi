package com.userandposts.blogapi.model;

import java.util.ArrayList;
import java.util.List;

public class UserDetails {
	private String website;
	private Company company;
	private Integer id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private List<Post> post = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	
	
	@Override
	public String toString() {
		return this.website +" : "+this.id+" : "+this.address.getStreet()+ this.post.get(0).getTitle();
	}
	
	
}

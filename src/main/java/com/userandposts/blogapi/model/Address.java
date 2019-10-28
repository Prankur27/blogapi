package com.userandposts.blogapi.model;

public class Address {
	
	private String street;
	private String suite;
	private String zipcode;
	private GeoLocation geo;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public GeoLocation getGeo() {
		return geo;
	}
	public void setGeo(GeoLocation geo) {
		this.geo = geo;
	}
	
	

}

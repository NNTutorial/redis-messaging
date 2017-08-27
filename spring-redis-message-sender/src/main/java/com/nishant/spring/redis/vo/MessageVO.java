package com.nishant.spring.redis.vo;

public class MessageVO {

	private String routekey;
	private String name;
	private String location;
	private String country;

	public String getRoutekey() {
		return routekey;
	}
	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}


}

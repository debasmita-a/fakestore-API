package com.qa.fakestore.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private String email;
	private String username;
	private String password;
	private Name name;
	private Address address;
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Name{
		private String firstname;
		private String lastname;
	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Address{
		private String city;
		private String street;
		private int number;
		private String zipcode;
		private Geolocation geolocation;
		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class Geolocation{
			private String lat;
			@JsonProperty("long")
			private String longi;
		}
	}
	private String phone;
	
}

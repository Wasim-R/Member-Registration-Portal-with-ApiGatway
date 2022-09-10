package com.cognizant.user.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

	private String addressLine;
	private String country;
	private String state;
	private String city;
	private String pinCode;
}

package com.cognizant.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")
public class User {

	@Id
	private String id;

	private String name;

	private String dateOfBirth;

	private Address address;

	private String panCardNumber;

	private Long contactDetail;

	private String email;

	private String password;

}

package com.cognizant.user.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "dependent")
public class Dependent {

	private String id;

	private String name;

	private String dateOfBirth;

	private String userId;
}

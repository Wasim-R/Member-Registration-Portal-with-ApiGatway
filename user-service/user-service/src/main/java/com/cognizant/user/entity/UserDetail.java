package com.cognizant.user.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {

	private User user;

	private List<Dependent> dependents;
	
	private List<String> idsToDetele;
}

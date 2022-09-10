package com.cognizant.user.controller;

import java.util.Objects;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.cognizant.user.entity.Dependent;
import com.cognizant.user.entity.Login;
import com.cognizant.user.entity.MemberDetail;
import com.cognizant.user.entity.User;
import com.cognizant.user.entity.UserDetail;
import com.cognizant.user.exception.DataMissingException;
import com.cognizant.user.exception.ResourceNotFoundException;
import com.cognizant.user.exception.UserNotFoundException;
import com.cognizant.user.mock.UserMockData;
import com.cognizant.user.service.UserService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserTestController {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;

	UserMockData userMockData;
	
	@BeforeEach
	public void setUp() throws Exception {
		userMockData = new UserMockData();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	void testRegisterUser() {
		User userData = userMockData.getUserMockData().get(0);
		
		if(userData.getName()== null || userData.getEmail() == null) {
			throw new DataMissingException("Mandatory data is missing");
		}
			User value = new User();
			Mockito.when(userService.register(userData)).thenReturn(value);
			
			ResponseEntity<?> responseEntity = userController.saveUser(userData);
			Assert.assertNotNull(responseEntity);
		
	}
	
	@Test
	void test1RegisterUser() {
		User userData = userMockData.getUserMockData().get(0);
		
		userData.setName(null);
		
		try {
			if(userData.getName()== null || userData.getEmail() == null) {
				throw new DataMissingException("Mandatory data is missing");
			}
			
			User value = new User();
			Mockito.when(userService.register(userData)).thenReturn(value);
			
			ResponseEntity<?> responseEntity = userController.saveUser(userData);
			Assert.assertNotNull(responseEntity);
		}catch(Exception e) {
			
		}
	}
	
	@Test
	void testUpdateUser() {
		String id = "R-190";
		UserDetail userDetailData = userMockData.getUserDetailMockData();
		Optional<User> existingUser = Optional.of(new User());
		
		Mockito.when(userService.getUserById(id)).thenReturn(existingUser);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("Member not found with the id : " + id);
		}

		if (userDetailData.getUser().getName() == null || userDetailData.getUser().getEmail() == null) {
			throw new DataMissingException("Mandatory data is missing");
		}
		
		UserDetail value = new UserDetail();
		
		Mockito.when(userService.save(userDetailData)).thenReturn(value);
		
		ResponseEntity<?> responseEntity = userController.updateUser(id, userDetailData);
		Assert.assertNotNull(responseEntity);
	}
	
	@Test
	void test1UpdateUser() {
		try {
			String id = "R-195";
			UserDetail userDetailData = userMockData.getUserDetailMockData();
			Optional<User> existingUser = Optional.of(new User());
			
			Mockito.when(userService.getUserById(id)).thenReturn(existingUser);

			if (existingUser.isEmpty()) {
				throw new ResourceNotFoundException("Member not found with the id : " + id);
			}

			if (userDetailData.getUser().getName() == null || userDetailData.getUser().getEmail() == null) {
				throw new DataMissingException("Mandatory data is missing");
			}
			
			UserDetail value = new UserDetail();
			
			Mockito.when(userService.save(userDetailData)).thenReturn(value);
			
			ResponseEntity<?> responseEntity = userController.updateUser(id, userDetailData);
			Assert.assertNotNull(responseEntity);
		} catch(Exception e) {
			
		}
	}
	
	@Test
	void test2UpdateUser() {
		try {
			String id = "R-190";
			UserDetail userDetailData = userMockData.getUserDetailMockData();
			
			Optional<User> existingUser = Optional.of(new User());
						
			Mockito.when(userService.getUserById(id)).thenReturn(existingUser);
			
			userDetailData.getUser().setEmail(null);

			if (userDetailData.getUser().getName() == null || userDetailData.getUser().getEmail() == null) {
				throw new DataMissingException("Mandatory data is missing");
			}
			
			UserDetail value = new UserDetail();
			
			Mockito.when(userService.save(userDetailData)).thenReturn(value);
			
			ResponseEntity<?> responseEntity = userController.updateUser(id, userDetailData);
			Assert.assertNotNull(responseEntity);
		} catch(Exception e) {
			
		}
	}
	
	@Test
	void testLoginUser() {
		
		Login loginUser = userMockData.getLoginMockData();
		
		UserDetail userDetailMockData = userMockData.getUserDetailMockData();
		
//		UserDetail userDetail = new UserDetail();
		
		Mockito.when(userService.verifyUserDetail(loginUser.getUsername(), loginUser.getPassword()))
			.thenReturn(userDetailMockData);

		try {
			if (Objects.isNull(userDetailMockData)) {
				throw new UserNotFoundException("Invalid Credentials");
			}
			
			ResponseEntity<?> responseEntity = userController.loginUser(loginUser);
			Assert.assertNotNull(responseEntity);
		} catch (ResourceNotFoundException e) {
			ResponseEntity<?> responseEntity = userController.loginUser(loginUser);
			Assert.assertNotNull(responseEntity);
		}
	}
	
	@Test
	void test1LoginUser() {
		
		Login loginUser = userMockData.getLoginMockData2();
		
		UserDetail userDetailMockData = userMockData.getUserDetailMockData();
		
		Mockito.when(userService.verifyUserDetail(loginUser.getUsername(), loginUser.getPassword()))
			.thenReturn(userDetailMockData);

		try {
			if (Objects.isNull(userDetailMockData.getUser())) {
				throw new UserNotFoundException("Invalid Credentials");
			}
			
			ResponseEntity<?> responseEntity = userController.loginUser(loginUser);
			Assert.assertNotNull(responseEntity);
		} catch (ResourceNotFoundException e) {
			ResponseEntity<?> responseEntity = userController.loginUser(loginUser);
			Assert.assertNotNull(responseEntity);
		}
	}
	
	@Test
	void testGetUserById() {
		String id= "R-190";
//		Optional<User> user = Optional.ofNullable(userMockData.getUserMockData().get(0));
		
		Optional<User> user = Optional.ofNullable(new User());
		
		Mockito.when(userService.getUserById(id))
		.thenReturn(user);

		if (user.isEmpty()) {
			ResponseEntity<?> responseEntity = userController.getUserById(id);
			Assert.assertNull(responseEntity);
		}
		
		ResponseEntity<?> responseEntity = userController.getUserById(id);
		Assert.assertNotNull(responseEntity);

	}
	
	@Test
	void test1GetUserById() {
		String id= "R-195";
		//		Optional<User> user = Optional.ofNullable(userMockData.getUserMockData().get(0));

		Optional<User> user = Optional.ofNullable(new User());
		
		Mockito.when(userService.getUserById(Mockito.anyString()))
		.thenReturn(user);

		if (user.isEmpty()) {
			ResponseEntity<?> responseEntity = userController.getUserById(id);
			Assert.assertNotNull(responseEntity);
		}
		
	}

	@Test
	void testCheckUserClaimDetail() {
		String id="R-190";
		MemberDetail memberDetail = new MemberDetail();

		if (id.startsWith("R")) {
			
			Optional<User> user = Optional.ofNullable(userMockData.getUserMockData().get(0));
			
			Mockito.when(userService.getUserById(id))
			.thenReturn(user);
			
			if (user.isPresent()) {
				memberDetail.setMemberId(user.get().getId());
				memberDetail.setName(user.get().getName());
				memberDetail.setDateOfBirth(user.get().getDateOfBirth());
			}
			
			MemberDetail responseEntity = userController.checkUserClaimDetail(id);
			Assert.assertNotNull(responseEntity);
		}
	}
	
	@Test
	void test1CheckUserClaimDetail() {
		String id="D-111";
		MemberDetail memberDetail = new MemberDetail();

		if (id.startsWith("D")) {
			
			Optional<Dependent> dependent = Optional.ofNullable(userMockData.getDependentMockData());
			
			Mockito.when(userService.getDependentById(id))
			.thenReturn(dependent);
			
			if (dependent.isPresent()) {
				memberDetail.setMemberId(dependent.get().getId());
				memberDetail.setName(dependent.get().getName());
				memberDetail.setDateOfBirth(dependent.get().getDateOfBirth());
			}
			
			MemberDetail responseEntity = userController.checkUserClaimDetail(id);
			Assert.assertNotNull(responseEntity);
		}
	}
}

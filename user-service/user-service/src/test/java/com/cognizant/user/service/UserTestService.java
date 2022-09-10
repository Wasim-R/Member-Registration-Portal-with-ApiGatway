package com.cognizant.user.service;

import java.util.ArrayList;
import java.util.List;
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

import com.cognizant.user.entity.Dependent;
import com.cognizant.user.entity.User;
import com.cognizant.user.entity.UserDetail;
import com.cognizant.user.exception.UserNotFoundException;
import com.cognizant.user.mock.UserMockData;
import com.cognizant.user.repository.DependentRepository;
import com.cognizant.user.repository.UserDao;
import com.cognizant.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserTestService {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Mock
	private DependentRepository dependentRepository;

	@Mock
	private UserDao userDao;

	UserMockData userMockData;

	@BeforeEach
	public void setUp() throws Exception {
		userMockData = new UserMockData();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	void testGetUserById() {
		String id = "R-190";

		Optional<User> user = Optional.ofNullable(new User());

		Mockito.when(userRepository.findById(id)).thenReturn(user);

		Optional<User> userData = userService.getUserById(id);

		Assert.assertNotNull(userData.get());
	}
	
	@Test
	void testGetDependentById() {
		String id = "D-111";

		Optional<Dependent> dependent = Optional.ofNullable(new Dependent());

		Mockito.when(dependentRepository.findById(id)).thenReturn(dependent);

		Optional<Dependent> dependentData = userService.getDependentById(id);

		Assert.assertNotNull(dependentData.get());
	}
	
	@Test
	void testSave() {
		UserDetail userDetailMockData = userMockData.getUserDetailMockData();
		
		User user = new User();
		
		Dependent dependent = new Dependent();
		
		Mockito.when(userRepository.save(userDetailMockData.getUser())).thenReturn(user);
		
		for(Dependent dependentValue: userDetailMockData.getDependents()) {
			Mockito.when(dependentRepository.save(dependentValue)).thenReturn(dependent);
		}
		
		UserDetail userDetail = userService.save(userDetailMockData);
		
		Assert.assertNotNull(userDetail);
	}
	
	@Test
	void test1Save() {
		UserDetail userDetailMockData = userMockData.getNewUserDetail();
		
		User user = new User();
		
		Dependent dependent = new Dependent();
				
		for(Dependent dependentValue: userDetailMockData.getDependents()) {
			if (dependent.getId() == null) {
				dependent.setId("D-" + Math.round(Math.random() * 1000));
				dependent.setUserId(user.getId());
				Mockito.when(dependentRepository.save(dependentValue)).thenReturn(dependent);
			}
		}
		
		Mockito.when(userRepository.save(userDetailMockData.getUser())).thenReturn(user);
		
		UserDetail userDetail = userService.save(userDetailMockData);
		
		Assert.assertNotNull(userDetail);
	}
	
	@Test
	void testRegister() {
		User user = userMockData.getNewUserMockData();
		user.setId("R-" + Math.round(Math.random() * 1000));
		User returnedUser = new User();
		
		Mockito.when(userRepository.save(user)).thenReturn(returnedUser);

		User userValue = userService.register(user);
		Assert.assertNotNull(userValue);
	}
	
	@Test
	void testVerifyUserDetail() {
		User userDataMockData = userMockData.getUserMockData().get(0);
		User user = new User();
		
		Mockito.when(userDao.validateUserDetail(userDataMockData.getEmail(), userDataMockData.getPassword()))
		.thenReturn(user);

		UserDetail userDetail = new UserDetail();
		List<Dependent> dependents = new ArrayList<>();
		
		Mockito.when(userDao.validateUserDetail(userDataMockData.getEmail(), userDataMockData.getPassword()))
		.thenReturn(user);
		
		Mockito.when(dependentRepository.findByUserId(user.getId()))
		.thenReturn(dependents);

		userDetail.setUser(user);
		if (!dependents.isEmpty()) {
			userDetail.setDependents(dependents);
		}
		
		UserDetail userDetailValue = userService.verifyUserDetail(userDataMockData.getEmail(), userDataMockData.getPassword());
		Assert.assertNotNull(userDetailValue);
	}
	
	@Test
	void test1VerifyUserDetail() {
		User userDataMockData = userMockData.getUserMockData().get(0);
		User user = new User();
		
		Mockito.when(userDao.validateUserDetail(userDataMockData.getEmail(), userDataMockData.getPassword()))
		.thenReturn(user);

		UserDetail userDetail = new UserDetail();
		List<Dependent> dependents = new ArrayList<>();
		
		Mockito.when(userDao.validateUserDetail(userDataMockData.getEmail(), userDataMockData.getPassword()))
		.thenReturn(user);
		
		Mockito.when(dependentRepository.findByUserId(user.getId()))
		.thenReturn(dependents);
		
		dependents.add(userMockData.getDependentMockData());
		userDetail.setUser(user);
		if (!dependents.isEmpty()) {
			userDetail.setDependents(dependents);
		}
		
		UserDetail userDetailValue = userService.verifyUserDetail(userDataMockData.getEmail(), userDataMockData.getPassword());
		Assert.assertNotNull(userDetailValue);
	}
	
	@Test
	void test2VerifyUserDetail() {
		User userDataMockData = userMockData.getUserMockData().get(0);
		User user = new User();
		
		Mockito.when(userDao.validateUserDetail("haritharaj1811@gmail.com", userDataMockData.getPassword()))
		.thenReturn(user);
		
		User userValue = null;
		
		try {
			if (Objects.isNull(userValue)) {
				Assert.assertNull(userValue);
				throw new UserNotFoundException("Invalid Credentials");
			}
		}catch(Exception e) {
			
		}
	}
	
	@Test
	void testFindAllUser() {
		List<User> users = new ArrayList<>();
		Mockito.when(userRepository.findAll()).thenReturn(users);
		
		List<User> usersValue = userService.findAllUser();
		Assert.assertNotNull(usersValue);
	}
}

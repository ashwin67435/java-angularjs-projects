package com.flightapp1.service;

import java.util.List;

import com.flightapp1.dao.UserDAO;
import com.flightapp1.dao.UserDAOImpl;
import com.flightapp1.model.User;
import com.flightapp1.util.JsonUtil;

public class UserServiceImpl implements UserService{
	public String findAll() {
		UserDAO userDAO = new UserDAOImpl();
		
		List<User> users = userDAO.findAll();
		
		String usersList = JsonUtil.convertJavaToJson(users);
		
		return usersList;
	}

	public User isUserExits(String userName) {
		UserDAO userDAO = new UserDAOImpl();
		
		List<User> users = userDAO.findAll();
		
		for(User user:users) {
			if(user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}
	
	
	public boolean userLogin(String userName,String password) {
		User user = isUserExits(userName);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean createNewUser(String userName,String password,String gender,String email,String dob) {
		User isUser = isUserExits(userName);
		
		if(isUser!=null) {
			return false;
		}else {
			
			UserDAO userDAO = new UserDAOImpl();
			User user = new User();
			user.setUserName(userName);
			user.setDob(dob);
			user.setPassword(password);
			user.setEmail(email);
			user.setGender(gender);
			user.setUserId(userName.hashCode());
			
			userDAO.addUser(user);
			return true;
		}
	}
	
	public void updateUser(String data) {
		UserDAO userDAO = new UserDAOImpl();
		
		userDAO.updateUser(data);
	}
	
	
	public boolean isAdminUser(String userName) {
		UserDAO userDAO = new UserDAOImpl();
		
		return userDAO.isAdminUser(userName);
	}
	
}

package com.flightapp1.service;

import com.flightapp1.model.User;

public interface UserService {
	public String findAll();
	public User isUserExits(String userName);
	public boolean userLogin(String userName,String password);
	public boolean createNewUser(String userName,String password,String gender,String email,String dob);
	public void updateUser(String data);
	public boolean isAdminUser(String userName);
}

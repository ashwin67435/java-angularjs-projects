package com.flightapp1.dao;

import java.util.List;

import com.flightapp1.model.User;

public interface UserDAO {
	public List<User> findAll();
	public void addUser(User user);
	public void updateUser(String data);
	public boolean isAdminUser(String userName);
//	User findById();
//	List<User> findByName();
//	boolean addUser(User user);
//	boolean updateUser(User user);
//	boolean deleteUser(User user);
}

package com.flightapp1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.UserService;
import com.flightapp1.service.UserServiceImpl;



//@SuppressWarnings("serial")
@WebServlet("/userdetails")
public class UserServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		UserService userService = new UserServiceImpl();
		
		String UsersList = userService.findAll();
		
		res.getWriter().write(UsersList);
	}
}

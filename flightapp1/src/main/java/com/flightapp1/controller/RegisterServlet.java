package com.flightapp1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.UserService;
import com.flightapp1.service.UserServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		
		String userName = req.getParameter("uName");
		String dob = req.getParameter("dob");
		String password = req.getParameter("uPass");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		
		UserService userService = new UserServiceImpl();
		if(userService.createNewUser(userName, password, gender, email, dob)) {
			res.sendRedirect("login.html");
		}else {
			res.getWriter().write("user already exits");
		}
		
		
	}
}
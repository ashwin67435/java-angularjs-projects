package com.flightapp1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flightapp1.service.UserService;
import com.flightapp1.service.UserServiceImpl;

@WebServlet("/login")
public class loginServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		UserService userService = new UserServiceImpl();
		
		
		String uName = req.getParameter("uName");
		String UPass = req.getParameter("uPass");
	
		if(userService.userLogin(uName, UPass)) {
			HttpSession session = req.getSession();
			session.setAttribute("userName", uName);
			if(userService.isAdminUser(uName)) {
				session.setAttribute("isadmin", "true");
				res.sendRedirect("/flightapp1/admin");
			}else {
				session.setAttribute("isadmin", "false");
				res.sendRedirect("/flightapp1/users.jsp");
			}
				
		}else {
			res.sendRedirect("/flightapp1/login.html");
		}
		
	}
}

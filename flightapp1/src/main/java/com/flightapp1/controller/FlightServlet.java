package com.flightapp1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.FlightService;
import com.flightapp1.service.FlightServiceImpl;

@WebServlet("/flightdetails")
public class FlightServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		
		FlightService flightService = new FlightServiceImpl();
		
		String flightList = flightService.getFlights();
		
		res.getWriter().write(flightList);
	}
}

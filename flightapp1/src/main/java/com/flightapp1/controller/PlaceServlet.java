package com.flightapp1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.FlightService;
import com.flightapp1.service.FlightServiceImpl;

@WebServlet("/places")
public class PlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightService flightService = new FlightServiceImpl();
		
		String places = flightService.getPlaces();
		
		response.getWriter().write(places);
	}

}

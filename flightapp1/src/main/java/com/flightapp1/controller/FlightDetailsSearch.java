package com.flightapp1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.FlightService;
import com.flightapp1.service.FlightServiceImpl;

@WebServlet("/flightdetailssearch")
public class FlightDetailsSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightService flightService = new FlightServiceImpl();
		
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		String departureTime = request.getParameter("departureTime");
		String returnTime = request.getParameter("returnTime");
		int travellerCount = Integer.valueOf(request.getParameter("travellersCount"));
		
		
		if(!returnTime.equals("")) {
			String flightStrReturn = flightService.getFlights(destination,source ,departureTime.substring(0,10));
			System.out.println(flightStrReturn);
		}
		

		
		String flightStrTo = flightService.getFlights(source, destination,departureTime);
		response.setContentType("application/json");
		
		response.getWriter().write(flightStrTo);
	}

}

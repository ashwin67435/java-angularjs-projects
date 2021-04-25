package com.flightapp1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.TicketService;
import com.flightapp1.service.TicketServiceImpl;

@WebServlet("/bookedtickets")
public class BookedTicketServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		TicketService ticketService = new TicketServiceImpl();
		
		String ticketList = ticketService.getBookedTickets();
		
		res.getWriter().write(ticketList);
	}
	
}

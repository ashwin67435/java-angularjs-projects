package com.flightapp1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.TicketService;
import com.flightapp1.service.TicketServiceImpl;

@WebServlet("/cancelticket")
public class CancelTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingId = request.getParameter("bookingId");
		
		TicketService ticketService = new TicketServiceImpl();
		
		System.out.println(bookingId);
		ticketService.cancelTicket(bookingId);
		
		response.getWriter().write("cancelled");
		
	}

}

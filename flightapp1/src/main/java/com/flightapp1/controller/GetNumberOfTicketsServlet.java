package com.flightapp1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.TicketService;
import com.flightapp1.service.TicketServiceImpl;


@WebServlet("/getnooftickets")
public class GetNumberOfTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketService ticketService = new TicketServiceImpl();
		
		response.getWriter().write(ticketService.getNumberOfTickets().toString());
	}

}

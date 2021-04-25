package com.flightapp1.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightapp1.service.TicketService;
import com.flightapp1.service.TicketServiceImpl;
import com.flightapp1.testing.Testing;


@WebServlet("/newticket")
public class NewTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketService ticketService = new TicketServiceImpl();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		
		try {
			String line;
			while((line = reader.readLine())!=null) {
				sb.append(line).append('\n');
			}
		}finally {
			reader.close();
		}
		
		ticketService.addTicket(sb.toString());
	}

}

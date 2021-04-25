package com.flightapp1.service;

import java.util.List;

import com.flightapp1.dao.BookTicketDAO;
import com.flightapp1.dao.BookTicketImpl;
import com.flightapp1.model.BookedTicket;
import com.flightapp1.util.JsonUtil;

public class TicketServiceImpl implements TicketService {
	public String getBookedTickets() {
		BookTicketDAO bookedTicketDAO = new BookTicketImpl();
		
		List<BookedTicket> tickets = bookedTicketDAO.getBookedTickets();
		
		String bookedTickets = JsonUtil.convertJavaToJson(tickets);
		
		return bookedTickets;
		
	}
	
	
	public void addTicket(String data) {
		BookTicketDAO bookedTicketDAO = new BookTicketImpl();
		bookedTicketDAO.addTicket(data);
	}
	
	public Long getNumberOfTickets() {
		BookTicketDAO bookedTicketDAO = new BookTicketImpl();
		return bookedTicketDAO.getNumberOfTickets();
	}
	
	public String getTicketHistory(String userName) {
		BookTicketDAO bookedTicketDAO = new BookTicketImpl();
		
		String history = JsonUtil.convertJavaToJson(bookedTicketDAO.getUserHistory(userName));
		return history;
	}
	
	
	public void cancelTicket(String bookingId) {
		BookTicketDAO bookedTicketDAO = new BookTicketImpl();
		bookedTicketDAO.cancelTicket(bookingId);
	}
	
}

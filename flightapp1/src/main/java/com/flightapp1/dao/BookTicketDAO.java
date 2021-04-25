package com.flightapp1.dao;

import java.util.List;

import com.flightapp1.model.BookedTicket;

public interface BookTicketDAO {
	public List<BookedTicket> getBookedTickets();
	public Long getNumberOfTickets();
	public void addTicket(String data);
	public List<BookedTicket> getUserHistory(String userName);
	public void cancelTicket(String bookingId);
}

package com.flightapp1.service;

public interface TicketService {
	public String getBookedTickets();
	public void addTicket(String data);
	public Long getNumberOfTickets();
	public String getTicketHistory(String userName);
	public void cancelTicket(String bookingId);
}

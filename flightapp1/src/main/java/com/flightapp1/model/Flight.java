package com.flightapp1.model;

import java.io.Serializable;

public class Flight implements Serializable{
	
	private String flightName;
	private long seatsBooked;
	private long flightNumber;
	private long capacity;
	private double price;
	private String source;
	private String destination;
	private String departureTime;
	private boolean isMealAvailable;
	private long numberOfStops;
	private long duration;
	private long ticketsBooked;
	
	
	
	
	public long getTicketsBooked() {
		return ticketsBooked;
	}
	public void setTicketsBooked(long ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}
	public long getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(long seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	
	public long getNumberOfStops() {
		return numberOfStops;
	}
	public void setNumberOfStops(long numberOfStops) {
		this.numberOfStops = numberOfStops;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isMealAvailable() {
		return isMealAvailable;
	}
	public void setMealAvailable(boolean isMealAvailable) {
		this.isMealAvailable = isMealAvailable;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public long getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(long flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
}

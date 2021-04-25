package com.flightapp1.service;

import java.util.List;

import com.flightapp1.model.Flight;

public interface FlightService {
	public String getFlights();
	public Flight isFlightExists(Long flightNumber);
	public boolean createNewFlight(String flightName,long flightNumber,double price,String source,String destination,String departureTime,boolean isMealAvailable,long numberOfStops,long duration);
	public void updateFlight(String data);
	public String getPlaces();
	
	public String getFlights(String source,String destination,String departureTime);
}

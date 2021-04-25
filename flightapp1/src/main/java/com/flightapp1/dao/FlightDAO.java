package com.flightapp1.dao;

import java.util.List;

import com.flightapp1.model.Flight;

public interface FlightDAO {
	public List<Flight> getFlights();
	public void addFlight(Flight flight);
	public void updateFlight(String data);
	public List<String> getPlaces();
	public List<Flight> getFlights(String source,String destination,String departureTime);
 }

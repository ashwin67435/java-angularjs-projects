package com.flightapp1.service;


import java.util.List;

import com.flightapp1.dao.FlightDAO;
import com.flightapp1.dao.FlightDAOImpl;
import com.flightapp1.model.Flight;
import com.flightapp1.util.JsonUtil;

public class FlightServiceImpl implements FlightService{
	public String getFlights() {
		FlightDAO flightDAO = new FlightDAOImpl();
		
		List<Flight> flights = flightDAO.getFlights();
		
		String flightsList = JsonUtil.convertJavaToJson(flights);
		
		return flightsList;
	}
	
	public Flight isFlightExists(Long flightNumber) {
		FlightDAO flightDAO = new FlightDAOImpl();
		
		List<Flight> flights = flightDAO.getFlights();
		
		for(Flight flight:flights) {
			if(flight.getFlightNumber() == flightNumber) {
				return flight;
			}
		}
		return null;
	}
	
	public boolean createNewFlight(String flightName,long flightNumber,double price,String source,String destination,String departureTime,boolean isMealAvailable,long numberOfStops,long duration) {
		Flight isFlight = isFlightExists(flightNumber);
		
		if(isFlight!=null) {
			return false;
		}else {
			FlightDAO flightDAO = new FlightDAOImpl();
			
			Flight flight = new Flight();
			flight.setFlightName(flightName);
			flight.setDepartureTime(departureTime);
			flight.setDestination(destination);
			flight.setSource(source);
			flight.setDuration(duration);
			flight.setPrice(price);
			flight.setFlightNumber(flightNumber);
			flight.setMealAvailable(isMealAvailable);
			flight.setNumberOfStops(numberOfStops);
			
			flightDAO.addFlight(flight);
			return true;
		}
	}
	
	public void updateFlight(String data) {
		FlightDAO flightDAO = new FlightDAOImpl();
		flightDAO.updateFlight(data);
	}

	public String getPlaces() {
		FlightDAO flightDAO = new FlightDAOImpl();
		
		List<String> places = flightDAO.getPlaces();
		
		String placesList = JsonUtil.convertJavaToJson(places);
		
		return placesList;
	}
	
	public String getFlights(String source,String destination,String departureTime){
		FlightDAO flightDAO = new FlightDAOImpl();
		
		List<Flight> flights = flightDAO.getFlights(source, destination,departureTime);
		
		String flightString = JsonUtil.convertJavaToJson(flights);
		
		return flightString;
	}
	
}

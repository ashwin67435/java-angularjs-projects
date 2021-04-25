package com.flightapp1.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp1.model.Flight;

public class FlightDAOImpl implements FlightDAO{
	
	public List<Flight> getFlights(){
		List<Flight> flights = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		
		try(FileReader reader = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
			Object obj = parser.parse(reader);
			
			JSONObject jsonObj = (JSONObject) obj;
			
			JSONArray flightsArr = (JSONArray)jsonObj.get("flights");
			
			Iterator<?> it = flightsArr.iterator();
			
			
			while(it.hasNext()) {
				JSONObject flightObj = (JSONObject)it.next();
				Flight flight = new Flight();
				flight.setFlightNumber((long)flightObj.get("flightNumber"));
				flight.setFlightName((String)flightObj.get("flightName"));
				flight.setSource((String)flightObj.get("source"));
				flight.setDestination((String)flightObj.get("destination"));
				flight.setMealAvailable(flightObj.get("mealAvailable").toString().charAt(0)=='t'?true:false);
				flight.setDepartureTime((String)flightObj.get("departureTime").toString().substring(0,16));
				flight.setPrice((double)flightObj.get("price"));
				flight.setNumberOfStops((long)flightObj.get("numberOfStops"));
				flight.setDuration((long)flightObj.get("duration"));
				flight.setSeatsBooked((long)flightObj.get("seatsBooked"));
				flight.setCapacity((long)flightObj.get("capacity"));
				flight.setTicketsBooked((long)flightObj.get("ticketsBooked"));
				flights.add(flight);
			}
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
		}
		return flights;
	}
	
	
	public void addFlight(Flight flight) {
		JSONObject jsonObj = new JSONObject();
		JSONParser parser = new JSONParser();
		
		jsonObj.put("flightName",flight.getFlightName());
		jsonObj.put("flightNumber",flight.getFlightNumber());
		jsonObj.put("source",flight.getSource());
		jsonObj.put("destination",flight.getDestination());
		jsonObj.put("price",flight.getPrice());
		jsonObj.put("departureTime",flight.getDepartureTime());
		jsonObj.put("duration",flight.getDuration());
		jsonObj.put("numberOfStops",flight.getNumberOfStops());
		jsonObj.put("isMealAvailable",flight.isMealAvailable());
		jsonObj.put("seatsBooked", flight.getSeatsBooked());
		jsonObj.put("capacity", flight.getCapacity());
		jsonObj.put("ticketsBooked",flight.getTicketsBooked());
		
		
		try(FileReader reader = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
			Object obj = parser.parse(reader);
			
			JSONObject fullJsonObj = (JSONObject)obj;
			
			JSONArray flightsArr = (JSONArray)fullJsonObj.get("flights");
			
			flightsArr.add(jsonObj);
			
			FileWriter writer = new FileWriter("/home/local/ZOHOCORP/ashwin-pt3940/database.json",false);
			
			writer.write(fullJsonObj.toJSONString());
			writer.flush();
			
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFlight(String data) {
		
		ObjectMapper objectMapper = new ObjectMapper();
	    
	    
		   JSONParser parser = new JSONParser();
		    
		    try(FileReader read = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
				
		    	JSONArray flights = objectMapper.readValue(data.toString(), JSONArray.class);
		    	
		    	Object obj = parser.parse(read);
				
				JSONObject fullJsonObj = (JSONObject)obj;
				
				JSONArray flightsArr = (JSONArray)fullJsonObj.get("flights");
				
				flightsArr.clear();
				
				flightsArr.addAll(flights);
				
				FileWriter writer = new FileWriter("/home/local/ZOHOCORP/ashwin-pt3940/database.json",false);
				
				writer.write(fullJsonObj.toJSONString());
				writer.flush();
			
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			}
	
	}
	
	public List<String> getPlaces(){
		
		List<String> places = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		
		 try(FileReader read = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
				
		    	Object obj = parser.parse(read);
				
				JSONObject fullJsonObj = (JSONObject)obj;
				
				JSONArray flightPlaces = (JSONArray)fullJsonObj.get("places");
				
				Iterator<?> it = flightPlaces.iterator();
				
				while(it.hasNext()) {
					places.add(it.next().toString());
				}
				
		 }catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			}
		return places;
	}
	
	public List<Flight> getFlights(String source,String destination,String departureTime){
		
		List<Flight> flightsForRoute = new ArrayList<>();
		List<Flight> flights = getFlights();
		
		for(Flight flight: flights) {
			if(flight.getSource().equals(source) && flight.getDestination().equals(destination) && flight.getDepartureTime().substring(0,10).equals(departureTime)) {
				flightsForRoute.add(flight);
			}
		}
		return flightsForRoute;
	}
	
	
}

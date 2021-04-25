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
import com.flightapp1.model.BookedTicket;
import com.flightapp1.model.Passenger;

public class BookTicketImpl implements BookTicketDAO {
	public List<BookedTicket> getBookedTickets(){
		
		List<BookedTicket> tickets = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		
		try(FileReader reader = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
			Object obj = parser.parse(reader);
			
			JSONObject jsonObj = (JSONObject) obj;
			
			JSONArray ticketsArr = (JSONArray) jsonObj.get("bookedTickets");
			
			Iterator<?> it = ticketsArr.iterator();
		
			
			while(it.hasNext()) {
				JSONObject ticketObj = (JSONObject)it.next();
				BookedTicket ticket = new BookedTicket();
				ticket.setBookingId((String)ticketObj.get("bookingId"));
				ticket.setFlightNumber((long)ticketObj.get("flightNumber"));
				ticket.setFlightName((String)ticketObj.get("flightName"));
				ticket.setUserName((String)ticketObj.get("user"));
				ticket.setSource((String)ticketObj.get("source"));
				ticket.setCategory((String)ticketObj.get("class"));
				ticket.setFare(Double.valueOf((String)ticketObj.get("fare")));
				ticket.setDepartureTime((String)ticketObj.get("departure"));
				ticket.setDestination((String)ticketObj.get("destination"));
				ticket.setCanceled(ticketObj.get("isCancel").toString().charAt(0)=='t'?true:false);
				
				JSONArray passengersObj = (JSONArray)ticketObj.get("passengers");
				
				List<Passenger> passengers = new ArrayList<>();
				
				Iterator<?> passIt = passengersObj.iterator();
				
				while(passIt.hasNext()) {
					JSONObject passObj = (JSONObject)passIt.next();
					Passenger pass = new Passenger();
					pass.setName((String)passObj.get("name"));
					pass.setAge(Long.valueOf((String)passObj.get("age")));
					passengers.add(pass);
				}
				ticket.setPassenger(passengers);
				
				tickets.add(ticket);
				
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
	}
		return tickets;
	}
	
	public void addTicket(String data) {

		   JSONParser parser = new JSONParser();
		    
		    try(FileReader read = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
				
		    	Object obj = parser.parse(read);
		    	
		    	Object objticket = parser.parse(data.toString());
		    	
		    	JSONObject fullObj = (JSONObject)obj;
		    	
		    	JSONObject ticketObj = (JSONObject)objticket;
		    	
		    	
		    	JSONArray ticketArr = (JSONArray)fullObj.get("bookedTickets");
		    	
		    	JSONArray passengers = (JSONArray)ticketObj.get("passengers");
		    	
		    	int seatsCount = passengers.size();
		    	
				Long numberOfTickets = (Long)fullObj.get("numberOfTicketsBooked");
				
				numberOfTickets += 1;
				
				JSONArray flights = (JSONArray)fullObj.get("flights");
				
				String FlightNumber = ticketObj.get("flightNumber").toString();
				
				Iterator<?> it = flights.iterator();
				
				while(it.hasNext()) {
					JSONObject flightObj = (JSONObject)it.next();
					if(flightObj.get("flightNumber").toString().equals(FlightNumber)) {
						Long seats = (long) flightObj.get("seatsBooked");
						Long capacity = (long)flightObj.get("capacity");
						Long ticketsBooked = (long)flightObj.get("ticketsBooked"); 
						
						if(ticketsBooked % 5==0) {
							double fare = (double)flightObj.get("price");
							//surge price 5%
							fare += fare*0.05;
							
							flightObj.put("price", fare);
						}
						
						flightObj.put("ticketsBooked",ticketsBooked+1);
						flightObj.put("capacity", capacity-seatsCount);
						flightObj.put("seatsBooked", seats+seatsCount);
						break;
					}
				}
				
				fullObj.put("numberOfTicketsBooked", numberOfTickets);
		    	
		    	ticketArr.add(ticketObj);
		    	
		    	FileWriter writer = new FileWriter("/home/local/ZOHOCORP/ashwin-pt3940/database.json",false);
				
				writer.write(fullObj.toJSONString());
				writer.flush();
		    	
		    	
		    }catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			}
	}
	
	public Long getNumberOfTickets() {
		
		 JSONParser parser = new JSONParser();
		    
		    try(FileReader read = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
				
		    	Object obj = parser.parse(read);
		    	
		    	JSONObject fullObj = (JSONObject)obj;
		    	
		    	Long numberOfTickets = (Long)fullObj.get("numberOfTicketsBooked");
		    	
		    	return numberOfTickets;
	
		    	
		    }catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			}
		    
		    return -1l;
		
	}
	
	public List<BookedTicket> getUserHistory(String userName){
		List<BookedTicket> ticketHistory = new ArrayList<>();
		
		List<BookedTicket> tickets = getBookedTickets();
		
		for(BookedTicket ticket : tickets) {
			if(ticket.getUserName().equals(userName)) {
				ticketHistory.add(ticket);
			}
		}
		return ticketHistory;
		
	}
	
	
	public void cancelTicket(String bookingId) {
	
		 JSONParser parser = new JSONParser();
		    
		    try(FileReader read = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
				
		    	Object obj = parser.parse(read);
		    	
		    	JSONObject fullObj = (JSONObject)obj;
		    	
		    	JSONArray ticketArr = (JSONArray)fullObj.get("bookedTickets");
		    	
		    	Iterator<?> it = ticketArr.iterator();
		    
		    	
		    	while(it.hasNext()) {
		    		JSONObject currObj = (JSONObject)it.next();
		    	
		    		if(currObj.get("bookingId").toString().equals(bookingId)) {
		    			
		    			Long flightNumber = (long)currObj.get("flightNumber");
		    			System.out.println(flightNumber);
		    			JSONArray passengers = (JSONArray)currObj.get("passengers");
		    			int passengerCount = passengers.size();
		    			
		    			
		    			JSONArray flights = (JSONArray)fullObj.get("flights");
		    			
		    			Iterator<?> itf = flights.iterator();
						
						while(itf.hasNext()) {
							JSONObject flightObj = (JSONObject)itf.next();
							if(flightObj.get("flightNumber").toString().equals(flightNumber.toString())) {
								Long seats = (long) flightObj.get("seatsBooked");
								Long capacity = (long)flightObj.get("capacity");
								Long ticketsBooked = (long)flightObj.get("ticketsBooked"); 
								Long numberOfTickets = (Long)fullObj.get("numberOfTicketsBooked");
								
								System.out.println(seats);
								System.out.println(capacity);
								System.out.println(ticketsBooked);
								seats -= passengerCount;
								ticketsBooked -= 1;
								capacity += passengerCount;
								numberOfTickets -= 1;
								
								flightObj.put("ticketsBooked",ticketsBooked);
								flightObj.put("capacity", capacity);
								flightObj.put("seatsBooked", seats);
								fullObj.put("numberOfTicketsBooked", numberOfTickets);
								break;
							}
						}
		    			
		    			currObj.put("isCancel", true);
		    			break;
		    		}
		    	}
		    	
		    	FileWriter writer = new FileWriter("/home/local/ZOHOCORP/ashwin-pt3940/database.json",false);
				
				writer.write(fullObj.toJSONString());
				writer.flush();
		    	
		    	
		    }catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			}
	
		
	}
	
}

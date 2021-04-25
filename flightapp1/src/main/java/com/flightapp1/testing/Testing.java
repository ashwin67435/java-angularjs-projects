package com.flightapp1.testing;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp1.model.BookedTicket;
import com.flightapp1.model.Passenger;
import com.flightapp1.model.User;
import com.flightapp1.util.JsonUtil;

public class Testing {
	
	public static void main(String[] args) {
		String s1 = "124.34";
		System.out.println(Double.valueOf(s1));
	}
		
}

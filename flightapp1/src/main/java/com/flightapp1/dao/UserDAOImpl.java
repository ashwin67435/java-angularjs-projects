package com.flightapp1.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp1.model.User;

public class UserDAOImpl implements UserDAO{
	
	public List<User> findAll(){
		List<User> usersList = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		
		try(FileReader reader = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
			Object obj = parser.parse(reader);
			
			JSONObject jsonObj = (JSONObject)obj;
			
			JSONArray usersArr = (JSONArray)jsonObj.get("users");

			Iterator<?> it = usersArr.iterator();
			
			while(it.hasNext()) {
				JSONObject userObj = (JSONObject)it.next();
				User user = new User();
				user.setUserId((Long.valueOf(userObj.get("userId").toString())));
				user.setUserName((String)userObj.get("userName"));
				user.setGender((String)userObj.get("gender"));
				user.setPassword((String)userObj.get("password"));
				user.setDob((String)userObj.get("dob").toString().substring(0, 10));
				user.setEmail((String)userObj.get("email"));
				usersList.add(user);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public void addUser(User user) {
		JSONObject jsonObj = new JSONObject();
		JSONParser parser = new JSONParser();
		
		jsonObj.put("userId", user.getUserId());
		jsonObj.put("userName",user.getUserName());
		jsonObj.put("gender", user.getGender());
		jsonObj.put("password",user.getPassword());
		jsonObj.put("dob", user.getDob());
		jsonObj.put("email", user.getEmail());
		
		try(FileReader reader = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
			Object obj = parser.parse(reader);
			
			JSONObject fullJsonObj = (JSONObject)obj;
			
			JSONArray usersArr = (JSONArray)fullJsonObj.get("users");
			
			usersArr.add(jsonObj);
			
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
	
	
	public void updateUser(String data) {
		
		ObjectMapper objectMapper = new ObjectMapper();
	    
	    
		   JSONParser parser = new JSONParser();
		    
		    try(FileReader read = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
				
		    	JSONArray users = objectMapper.readValue(data.toString(), JSONArray.class);
		    	
		    	Object obj = parser.parse(read);
				
				JSONObject fullJsonObj = (JSONObject)obj;
				
				JSONArray usersArr = (JSONArray)fullJsonObj.get("users");
				
				usersArr.clear();
				
				usersArr.addAll(users);
				
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
	
	public boolean isAdminUser(String userName) {
		

		JSONParser parser = new JSONParser();
		
		try(FileReader reader = new FileReader("/home/local/ZOHOCORP/ashwin-pt3940/database.json")){
			Object obj = parser.parse(reader);
			
			JSONObject jsonObj = (JSONObject)obj;
			
			JSONArray adminArr = (JSONArray)jsonObj.get("admins");
			
			Iterator<?> it = adminArr.iterator();
			
			while(it.hasNext()) {
				
				if(it.next().equals(userName)) {
					return true;
				}
				
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


package com.entlogics.restaurantsystem.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.restaurantsystem.dao.IRestaurantDAO;
import com.entlogics.restaurantsystem.dao.RestaurantDAO;
import com.entlogics.restaurantsystem.entity.Restaurant;
import com.google.gson.Gson;

//this servlet gets info of a specific restaurant, deletes a restaurants and updates a restaurant
@WebServlet("/restaurants/*")
public class GetRestaurantInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetRestaurantInfoController() {
		super();
	}

	// This method gets the info of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Reference to hold restaurant ID
		String restaurantId = null;

		// Getting parameter from the URL(The ID of the Restaurant)
		String uri = request.getRequestURI();
		if (uri != null) {
			
			//Split the URI into different parts, parts being separated by "/" and store them in an array
			String[] parts = uri.split("/");
			
			//Store parts as a list and Get index of part of the URI called "restaurants"
			int indexOfRestaurants = Arrays.asList(parts).indexOf("restaurants");
			
			//If part of the URL called "restaurants" exists, Then
			if (indexOfRestaurants != -1) {
				
				//Get the next index after "restaurants" Which will be the ID of the restaurant in the Path Parameter
				restaurantId = parts[indexOfRestaurants + 1];
				System.out.println("restaurantID in Path is: " + restaurantId);

			}

		}

		// Create restaurantDAO object to call restaurantDAO's getASpecificRestaurant method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		Restaurant specificRestaurant = restaurantDAO.getASpecificRestaurant(restaurantId);


		
		//convert the JSON object to String, Set Content type
				String json = new Gson().toJson(specificRestaurant);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	// This method is used to edit a restaurant
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Reference to hold restaurant ID
		String restaurantId = null;

		// Getting parameter from the URL(The ID of the Restaurant)
		String uri = request.getRequestURI();
		if (uri != null) {

			// Split the URI into different parts, parts being separated by "/" and store them in an array
			String[] parts = uri.split("/");

			// Store parts as a list and Get index of part of the URI called "restaurants"
			int indexOfRestaurants = Arrays.asList(parts).indexOf("restaurants");

			// If part of the URL called "restaurants" exists, Then
			if (indexOfRestaurants != -1) {

				// Get the next index after "restaurants" Which will be the ID of the restaurant in the Path Parameter
				restaurantId = parts[indexOfRestaurants + 1];
				System.out.println("restaurantID in Path is: " + restaurantId);

			}

		}

		// Get JSON data from request in a string
		String jsonData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		// Test if data is correct
		System.out.println("json data = " + jsonData);

		// Change the JSON string from json to restaurant class and save it into a restaurant object
		Restaurant restaurantData = new Gson().fromJson(jsonData, Restaurant.class);

		// Create restaurantDAO object
		IRestaurantDAO restaurantDAO = new RestaurantDAO();

		// Call RestaurantDao's createRestaurant method
		Boolean result = restaurantDAO.editRestaurant(restaurantData, restaurantId);
		
		// use boolean result to display a message in the log file
		System.out.println("Successfuly Edited a restaurant: " + result);

		// check boolean result value and send appropriate status code to client
		if (result == true) {

			response.setStatus(201);
		} else {

			// status code 400: bad request from client
			response.sendError(400);
		}

	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// Reference to hold restaurant ID
		String restaurantId = null;
		
//		//parsing restaurantId from string to integer
//		int restaurantId = Integer.parseInt(restaurantIdString);
		//returns 500 server error

		// Getting parameter from the URL(The ID of the Restaurant)
		String uri = request.getRequestURI();
		if (uri != null) {

			// Split the URI into different parts, parts being separated by "/" and store them in an array
			String[] parts = uri.split("/");

			// Store parts as a list and Get index of part of the URI called "restaurants"
			int indexOfRestaurants = Arrays.asList(parts).indexOf("restaurants");

			// If part of the URL called "restaurants" exists, Then
			if (indexOfRestaurants != -1) {

				// Get the next index after "restaurants" Which will be the ID of the restaurant in the Path Parameter
				restaurantId = parts[indexOfRestaurants + 1];
				System.out.println("restaurantID in Path is: " + restaurantId);

			}

		}

		// Create restaurantDAO object
		IRestaurantDAO restaurantDAO = new RestaurantDAO();

		// Call RestaurantDao's deleteRestaurant method
		Boolean result = restaurantDAO.deleteRestaurant(restaurantId);
		
		// use boolean result to display a message in the log file
		System.out.println("Successfuly deleted a restaurant: " + result);

		// check boolean result value and send appropriate status code to client
		if (result == true) {
			
			//status code 204: no content
			response.setStatus(204);
		} else {

			// status code 400: bad request from client
			response.sendError(400);
		}

	}
	
}

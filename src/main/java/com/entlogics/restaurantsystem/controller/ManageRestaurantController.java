package com.entlogics.restaurantsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

// this servlet gets the list of restaurants, Adds a new restaurant
@WebServlet("/restaurants")
public class ManageRestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageRestaurantController() {
		super();

	}

	// This method gets the list of restaurants
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Create restaurantDAO object to call restaurantDAO's getRestaurants method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();

		// Call RestaurantDAO getRestaurants method
		List<Restaurant> restaurantList = restaurantDAO.getRestaurants();

		// storing the size of the restaurant list in a string
		String size = String.valueOf(restaurantList.size());
		System.out.println("Controller List of restaurant " + restaurantList);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(restaurantList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	//this method is used to add a new restaurant
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get JSON data from request in a string
		String jsonData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		// Test if data is correct
		System.out.println("json data = " + jsonData);

		// Change the JSON string from json to restaurant class and save it into a restaurant object
		Restaurant restaurantData = new Gson().fromJson(jsonData, Restaurant.class);

		// Create restaurantDAO object to call restaurantDAO's getRestaurants method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();

		// Call RestaurantDao's createRestaurant method
		Boolean result = restaurantDAO.createRestaurant(restaurantData);

		// use boolean result to display a message in the log file
		System.out.println("Successfuly Created a new restaurant: " + result);

		// check boolean result value and send appropriate status code to client
		if (result == true) {

			response.setStatus(201);
		} else {

			// status code 400: bad request from client
			response.sendError(400);
		}

	}

}

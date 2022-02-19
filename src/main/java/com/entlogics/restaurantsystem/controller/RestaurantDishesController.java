package com.entlogics.restaurantsystem.controller;

import java.io.IOException;
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
import com.entlogics.restaurantsystem.entity.RestaurantDish;
import com.entlogics.restaurantsystem.entity.Waiter;
import com.google.gson.Gson;

//this servlet returns list of a dishes of a specific restaurant
@WebServlet("/restaurants/dishes/*")
public class RestaurantDishesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RestaurantDishesController() {
		super();
	}

	// returns list of a dishes of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting parameter from the URL(The ID of the restaurant)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int restaurant_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In RestaurantDishesController servlet, The ID of the restaurant is: " + restaurant_id);

		// Create restaurantDAO object to call restaurantDAO's getRestaurantDishes
		// method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		List<RestaurantDish> RestaurantDishes = restaurantDAO.getRestaurantDishes(restaurant_id);

		// print info of dishes to log
		System.out.println("GetRestaurantWaiterController List of customers " + RestaurantDishes);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(RestaurantDishes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	//this method adds a dish to a restaurant
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Getting parameter from the URL(The ID of the restaurant)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int restaurant_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In RestaurantDishesController servlet, The ID of the restaurant is: " + restaurant_id);
		
		// Get JSON data from request in a string
		String jsonData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		// Test if data is correct
		System.out.println("json data = " + jsonData);

		// Change the JSON string from json to restaurant class and save it into a
		// RestaurantDish object
		RestaurantDish dishData = new Gson().fromJson(jsonData, RestaurantDish.class);

		// Create restaurantDAO object to call restaurantDAO's getRestaurants method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();

		// Call RestaurantDao's linkDishRestaurant method
		Boolean result = restaurantDAO.linkDishRestaurant(dishData, restaurant_id);

		// use boolean result to display a message in the log file
		System.out.println("Successfuly linked a dish to the restaurant: " + result);

		// check boolean result value and send appropriate status code to client
		if (result == true) {

			response.setStatus(201);
		} else {

			// status code 400: bad request from client
			response.sendError(400);
		}
		
	}

}

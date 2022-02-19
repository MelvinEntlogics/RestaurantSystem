package com.entlogics.restaurantsystem.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.restaurantsystem.dao.IRestaurantDAO;
import com.entlogics.restaurantsystem.dao.RestaurantDAO;
import com.entlogics.restaurantsystem.entity.Customer;
import com.entlogics.restaurantsystem.entity.Restaurant;
import com.entlogics.restaurantsystem.entity.RestaurantCustomer;
import com.entlogics.restaurantsystem.entity.RestaurantDish;
import com.google.gson.Gson;

//this servlet returns all customers of a specific restaurant
@WebServlet("/restaurant/customers/*")
public class RestaurantCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RestaurantCustomerController() {
        super();
        
    }

	//this method returns a list of customers of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Getting parameter from the URL(The ID of the Restaurant)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int restaurant_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In restaurantCustomer servlet, The ID of the restaurant is: " + restaurant_id);

		// Create restaurantDAO object to call restaurantDAO's getRestaurantCustomers method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		List<Customer> RestaurantCustomerList = restaurantDAO.getRestaurantCustomer(restaurant_id);

		// print list of customers to log
		System.out.println("RestaurantCustomerController List of customers " + RestaurantCustomerList);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(RestaurantCustomerList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Getting parameter from the URL(The ID of the restaurant)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int restaurant_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In RestaurantCustomerController servlet, The ID of the restaurant is: " + restaurant_id);

		// Get JSON data from request in a string
		String jsonData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		// Test if data is correct
		System.out.println("json data = " + jsonData);

		// Change the JSON string from json to RestaurantCustomer class and save it into a
		// RestaurantDish object
		RestaurantCustomer customerData = new Gson().fromJson(jsonData, RestaurantCustomer.class);

		// Create restaurantDAO object to call restaurantDAO's linkCustomerRestaurant method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();

		// Call RestaurantDao's linkCustomerRestaurant method
		Boolean result = restaurantDAO.linkCustomerRestaurant(customerData, restaurant_id);

		// use boolean result to display a message in the log file
		System.out.println("Successfuly linked a customer to the restaurant: " + result);

		// check boolean result value and send appropriate status code to client
		if (result == true) {

			response.setStatus(201);
		} else {

			// status code 400: bad request from client
			response.sendError(400);
		}
	}

}

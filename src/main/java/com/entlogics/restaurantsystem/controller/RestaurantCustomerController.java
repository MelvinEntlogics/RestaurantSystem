package com.entlogics.restaurantsystem.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//this servlet returns all customers of a specific restaurant
@WebServlet("/restaurants/*/customers")
public class RestaurantCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RestaurantCustomerController() {
        super();
        
    }

	//this method returns a list of customers of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
				System.out.println("RestaurantCustomerController restaurantID in Path is: " + restaurantId);

			}

		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

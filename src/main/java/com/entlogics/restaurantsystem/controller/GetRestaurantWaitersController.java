package com.entlogics.restaurantsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.restaurantsystem.dao.IRestaurantDAO;
import com.entlogics.restaurantsystem.dao.RestaurantDAO;
import com.entlogics.restaurantsystem.entity.Customer;
import com.entlogics.restaurantsystem.entity.Waiter;
import com.google.gson.Gson;

//this servlet returns all waiters of a specific restaurant
@WebServlet("/restaurants/waiters/*")
public class GetRestaurantWaitersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetRestaurantWaitersController() {
        super();
    }

	//this method returns a list of waiters of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Getting parameter from the URL(The ID of the Restaurant)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int restaurant_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In restaurantCustomer servlet, The ID of the restaurant is: " + restaurant_id);

		// Create restaurantDAO object to call restaurantDAO's getRestaurantWaiters
		// method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		List<Waiter> RestaurantWaiterList = restaurantDAO.getRestaurantWaiters(restaurant_id);

		// print list of waiters to log
		System.out.println("GetRestaurantWaiterController List of customers " + RestaurantWaiterList);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(RestaurantWaiterList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

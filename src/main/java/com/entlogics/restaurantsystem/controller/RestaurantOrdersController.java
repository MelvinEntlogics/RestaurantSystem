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
import com.entlogics.restaurantsystem.entity.Order;
import com.entlogics.restaurantsystem.entity.RestaurantDish;
import com.google.gson.Gson;

//this servlet returns list of orders of a specific restaurant
@WebServlet("/restaurants/orders/*")
public class RestaurantOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RestaurantOrdersController() {
		super();
	}

	// returns list of orders of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting parameter from the URL(The ID of the restaurant)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int restaurant_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In RestaurantDishesController servlet, The ID of the restaurant is: " + restaurant_id);

		// Create restaurantDAO object to call restaurantDAO's getRestaurantOrders
		// method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		List<Order> RestaurantOrders = restaurantDAO.getRestaurantOrders(restaurant_id);

		// print info of orders to log
		System.out.println("GetRestaurantWaiterController List of customers " + RestaurantOrders);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(RestaurantOrders);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

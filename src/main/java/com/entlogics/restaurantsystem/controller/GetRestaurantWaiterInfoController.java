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
import com.entlogics.restaurantsystem.entity.Waiter;
import com.google.gson.Gson;

//this servlet returns info of a waiter of a specific restaurant
@WebServlet("/restaurants/waiter/*")
public class GetRestaurantWaiterInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetRestaurantWaiterInfoController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Getting parameter from the URL(The ID of the Waiter)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int waiter_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In GetRestaurantWaiterInfo servlet, The ID of the waiter is: " + waiter_id);

		// Create restaurantDAO object to call restaurantDAO's getRestaurantWaiterInfo
		// method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		Waiter RestaurantWaiterInfo = restaurantDAO.getRestaurantWaitersInfo(waiter_id);

		// print info of waiter to log
		System.out.println("GetRestaurantWaiterController List of customers " + RestaurantWaiterInfo);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(RestaurantWaiterInfo);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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
import com.google.gson.Gson;

//this servlet returns info of a customer of a specific restaurant
@WebServlet("/restaurant/customer/*")
public class GetRestaurantCustomerInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetRestaurantCustomerInfoController() {
        super();
    }

	//this method returns info of a customer of a specific restaurant
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting parameter from the URL(The ID of the Customer)
		String id = request.getPathInfo().replace("/", "");

		// changing id from string to int
		int customer_id = Integer.parseInt(id);

		// Printing the ID to the logs
		System.out.println("In getRestaurantCustomerInfo servlet, The ID of the restaurant is: " + customer_id);

		// Create restaurantDAO object to call restaurantDAO's getRestaurantCustomerInfo method
		IRestaurantDAO restaurantDAO = new RestaurantDAO();
		Customer RestaurantCustomerInfo = restaurantDAO.getRestaurantCustomerInfo(customer_id);

		// print list of customers to log
		System.out.println("getRestaurantCustomerInfoController List of customers " + RestaurantCustomerInfo);

		// convert the JSON object to String, Set Content type
		String json = new Gson().toJson(RestaurantCustomerInfo);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
		
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.entlogics.restaurantsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.entlogics.restaurantsystem.entity.Customer;
import com.entlogics.restaurantsystem.entity.Order;
import com.entlogics.restaurantsystem.entity.Restaurant;
import com.entlogics.restaurantsystem.entity.RestaurantCustomer;
import com.entlogics.restaurantsystem.entity.RestaurantDish;
import com.entlogics.restaurantsystem.entity.Waiter;

public interface IRestaurantDAO {
	// Add 13 Method declarations

	// Creating a list of restaurants
	List<Restaurant> getRestaurants();
	
	//Getting a Specific Restaurant
	Restaurant getASpecificRestaurant(String restaurantId);
	
	//post a restaurant
	Boolean createRestaurant(Restaurant restaurantData);

	//edit a restaurant
	Boolean editRestaurant(Restaurant restaurantData, String restaurantId);
	
	//delete a restaurant
	Boolean deleteRestaurant(String restaurantId);

	//list all customers of a restaurant
	List<Customer> getRestaurantCustomer(int restaurant_id);

	//getting info of a specific customer of a restaurant
	Customer getRestaurantCustomerInfo(int restaurant_id);

	//list all waiters of a restaurant
	List<Waiter> getRestaurantWaiters(int restaurant_id);
	
	//list info of a waiter from a restaurant
	Waiter getRestaurantWaitersInfo(int waiter_id);

	//list dishes in a restaurant
	List<RestaurantDish> getRestaurantDishes(int restaurant_id);
	
	//list orders in a restaurant
	List<Order> getRestaurantOrders(int restaurant_id);

	//Links a dish to a restaurant
	Boolean linkDishRestaurant(RestaurantDish dishData, int restaurantId);
	
	//Links a customer to a restaurant
	Boolean linkCustomerRestaurant(RestaurantCustomer customerData, int restaurant_id);

}

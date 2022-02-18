package com.entlogics.restaurantsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.entlogics.restaurantsystem.entity.Restaurant;

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

}

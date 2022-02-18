package com.entlogics.restaurantsystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entlogics.restaurantsystem.controller.GetRestaurantInfoController;
import com.entlogics.restaurantsystem.entity.Customer;
import com.entlogics.restaurantsystem.entity.Dish;
import com.entlogics.restaurantsystem.entity.Restaurant;
import com.entlogics.restaurantsystem.entity.Waiter;
import com.entlogics.restaurantsystem.util.DBConnectionUtil;

public class RestaurantDAO implements IRestaurantDAO {
	
	//method to list all restaurants
	@Override
	public List<Restaurant> getRestaurants() {

		// List initialization with restaurant as Generic Type to add restaurants to the list
		List<Restaurant> listOfrestaurants = new ArrayList<>();

		// sql query string to get the list of restaurants from the database
		String sql = "SELECT restaurant_id, restaurant_name, restaurant_address, restaurant_type, restaurant_rating, restaurant_email, restaurant_phone \r\n"
				+ "FROM restaurant";

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			Statement statement = connection.createStatement();

			
			//result set object to store the result of the executed query giving list of restaurants
			ResultSet resultSet = statement.executeQuery(sql);

			// looping over until resultSet object has data
			while (resultSet.next()) {

				// Creating restaurant object to be added to the list of restaurants
				Restaurant restaurant = new Restaurant();
				
				//getting attributes of restaurant from result set and setting them to our restaurant object	
				restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
				restaurant.setRestaurantName(resultSet.getString("restaurant_name"));
				restaurant.setRestaurantAddress(resultSet.getString("restaurant_address"));
				restaurant.setRestaurantEmail(resultSet.getString("restaurant_email"));
				restaurant.setRestaurantPhone(resultSet.getString("restaurant_phone"));
				restaurant.setRestaurantType(resultSet.getString("restaurant_type"));
				restaurant.setRestaurantRating(resultSet.getInt("restaurant_rating"));

				listOfrestaurants.add(restaurant);

			}
		} catch (IOException | SQLException e) {
			
			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		System.out.println("Inside restaurantDAO, Size of the list is: " + listOfrestaurants.size());

		// Get the list of restaurants from the database
		return listOfrestaurants;

	}
	
	//method to create a restaurant
	@Override
	public Boolean createRestaurant(Restaurant restaurantData) {

		boolean flag = false;

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO restaurant(restaurant_id, restaurant_name, restaurant_address, restaurant_type, restaurant_rating, restaurant_email, restaurant_phone) VALUES(?, ?, ?, ?, ?, ?, ?)");

			//getting information from restaurantData to set prepared statement
			preparedStatement.setInt(1, restaurantData.getRestaurantId());
			preparedStatement.setString(2, restaurantData.getRestaurantName());
			preparedStatement.setString(3, restaurantData.getRestaurantAddress());
			preparedStatement.setString(4, restaurantData.getRestaurantType());
			preparedStatement.setInt(5, restaurantData.getRestaurantRating());
			preparedStatement.setString(6, restaurantData.getRestaurantEmail());
			preparedStatement.setString(7, restaurantData.getRestaurantPhone());
			
			preparedStatement.executeUpdate();
			flag = true;
		} catch (Exception e) {
			
			System.out.println("Inside catch block of restaurant DAO Create restaurant");
			e.printStackTrace();

		}
		return flag;

	}

	// Returns info about a Specific Restaurant
	@Override
	public Restaurant getASpecificRestaurant(String restaurantId) {

		// New restaurant object
		Restaurant restaurant = new Restaurant();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT restaurant.restaurant_id, restaurant_name, restaurant_address, restaurant_type, restaurant_Rating, restaurant_email, restaurant_phone\r\n"
							+ "FROM restaurant\r\n" + "WHERE restaurant_id =?");
			preparedStatement.setString(1, restaurantId);

			// result set object to store the result of the executed query giving info of a Specific restaurant
			ResultSet restaurantResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (restaurantResultSet.next()) {

				//getting attributes of restaurant from result set and setting them to our restaurant object
				restaurant.setRestaurantId(restaurantResultSet.getInt("restaurant_id"));
				restaurant.setRestaurantName(restaurantResultSet.getString("restaurant_name"));
				restaurant.setRestaurantAddress(restaurantResultSet.getString("restaurant_address"));
				restaurant.setRestaurantEmail(restaurantResultSet.getString("restaurant_email"));
				restaurant.setRestaurantPhone(restaurantResultSet.getString("restaurant_phone"));
				restaurant.setRestaurantType(restaurantResultSet.getString("restaurant_type"));
				restaurant.setRestaurantRating(restaurantResultSet.getInt("restaurant_rating"));

			}

			// Declaring lists to be added inside restaurant object
			List<Customer> listCustomers = new ArrayList<>();
			List<Waiter> listWaiters = new ArrayList<>();
			List<Dish> listDishes = new ArrayList<>();

			// Prepared statement for customerInfo
			PreparedStatement preparedStatementCustomer = connection.prepareStatement(
					"SELECT restaurant_customer.customer_id, customer_name\r\n" + "FROM restaurant_customer\r\n"
							+ "JOIN customer\r\n" + "ON customer.customer_id = restaurant_customer.customer_id\r\n"
							+ "Where restaurant_id = ?");

			preparedStatementCustomer.setString(1, restaurantId);

			// result set object to store the result of the executed query giving info of customers in a Specific restaurant
			ResultSet customerResultSet = preparedStatementCustomer.executeQuery();

			// looping over until resultSet object has data
			while (customerResultSet.next()) {

				// Creating a new customer object and adding properties from the database
				Customer customer = new Customer();
				customer.setCustomerId(customerResultSet.getInt("customer_id"));
				customer.setCustomerName(customerResultSet.getString("customer_name"));
				listCustomers.add(customer);
			}

			// Prepared statement for waiter
			PreparedStatement preparedStatementWaiter = connection
					.prepareStatement("SELECT waiter_id, name\r\n" + "FROM waiter\r\n" + "WHERE restaurant_id = ?");

			preparedStatementWaiter.setString(1, restaurantId);

			// result set object to store the result of the executed query giving info of the waiters in a Specific restaurant
			ResultSet waiterResultSet = preparedStatementWaiter.executeQuery();

			// looping over until resultSet object has data
			while (waiterResultSet.next()) {

				// Creating a new waiter object and adding properties from the database
				Waiter waiter = new Waiter();
				waiter.setWaiterId(waiterResultSet.getInt("waiter_id"));
				waiter.setWaiterName(waiterResultSet.getString("name"));
				listWaiters.add(waiter);
			}

			// Prepared statement for dish
			PreparedStatement preparedStatementDish = connection.prepareStatement(
					"select dish.dish_id, dish_name\r\n" + "FROM dish\r\n" + "JOIN restaurant_dish \r\n"
							+ "ON dish.dish_id = restaurant_dish.dish_id\r\n" + "WHERE restaurant_id = ?");

			preparedStatementDish.setString(1, restaurantId);

			// result set object to store the result of the executed query giving info of the dishes in a Specific restaurant
			ResultSet dishResultSet = preparedStatementDish.executeQuery();

			// looping over until resultSet object has data
			while (dishResultSet.next()) {
				
				// Creating a new dish object and adding properties from the database
				Dish dish = new Dish();
				dish.setDishId(dishResultSet.getInt("dish_id"));
				dish.setDishName(dishResultSet.getString("dish_name"));
				listDishes.add(dish);
			}

			// Setting the "List of customers" attribute in the restaurant object
			restaurant.setRestaurantCustomers(listCustomers);
			

			// Setting the "List of waiters" attribute in the restaurant object
			restaurant.setRestaurantWaiters(listWaiters);
			

			// Setting the "List of dishes" attribute in the restaurant object
			restaurant.setRestaurantDishes(listDishes);

		

		} catch (IOException | SQLException e) {
			
			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}


		// return the specific restaurant from database
		return restaurant;

	}
	
	//method to edit a restaurant
	@Override
	public Boolean editRestaurant(Restaurant restaurantData, String restaurantID) {
		
		//to indicate the status post execution of edit restaurant method
		boolean flag = false;
		
		//storing existing information of restaurant by calling getASpecificRestaurant method
		Restaurant existingRestaurant = getASpecificRestaurant(restaurantID);
		
		//if attributes are provided by the client in json request body of PUT, Then use those attributes else if not provided, use existing attribute information from existingRestaurant
		String restaurantName = restaurantData.getRestaurantName() != null ? restaurantData.getRestaurantName() : existingRestaurant.getRestaurantName();
		
		String restaurantAddress = restaurantData.getRestaurantAddress() != null ? restaurantData.getRestaurantAddress() : existingRestaurant.getRestaurantAddress();
		
		String restaurantType = restaurantData.getRestaurantType() != null ? restaurantData.getRestaurantType() : existingRestaurant.getRestaurantType();
		
		int restaurantRating = restaurantData.getRestaurantRating() != 0 ? restaurantData.getRestaurantRating() : existingRestaurant.getRestaurantRating();
		
		String restaurantEmail = restaurantData.getRestaurantEmail() != null ? restaurantData.getRestaurantEmail() : existingRestaurant.getRestaurantEmail();
		
		String restaurantPhone = restaurantData.getRestaurantPhone() != null ? restaurantData.getRestaurantPhone() : existingRestaurant.getRestaurantPhone();
		
		
		
		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE restaurant SET restaurant_name=?, restaurant_address=?, restaurant_type=?, restaurant_rating=?, restaurant_email=?, restaurant_phone=? WHERE restaurant_id = ?");

			// getting information from restaurantData to set prepared statement			
			preparedStatement.setString(1, restaurantName);
			preparedStatement.setString(2, restaurantAddress);
			preparedStatement.setString(3, restaurantType);
			preparedStatement.setInt(4, restaurantRating);
			preparedStatement.setString(5, restaurantEmail);
			preparedStatement.setString(6, restaurantPhone);
			preparedStatement.setString(7, restaurantID);

			preparedStatement.executeUpdate();
			flag = true;
		} catch (IOException|SQLException e) {

			System.out.println("Inside catch block of restaurant DAO edit restaurant");
			e.printStackTrace();

		}		
		
		return flag;		
		
	}
	
	//method to delete a restaurant
	@Override
	public Boolean deleteRestaurant(String restaurantId) {
		
		//to indicate the status post execution of delete restaurant method
		boolean flag = false;
		

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"DELETE FROM restaurant WHERE restaurant_id= ?");

			// getting information from restaurantData to set prepared statement			
			preparedStatement.setString(1, restaurantId);
			

			preparedStatement.executeUpdate();
			flag = true;
		} catch (IOException|SQLException e) {

			System.out.println("Inside catch block of restaurant DAO delete restaurant");
			e.printStackTrace();

		}		
		
		return flag;
	}

}

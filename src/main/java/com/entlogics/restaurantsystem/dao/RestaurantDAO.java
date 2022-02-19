package com.entlogics.restaurantsystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.entlogics.restaurantsystem.controller.GetRestaurantInfoController;
import com.entlogics.restaurantsystem.entity.Customer;
import com.entlogics.restaurantsystem.entity.Dish;
import com.entlogics.restaurantsystem.entity.Order;
import com.entlogics.restaurantsystem.entity.OrderItems;
import com.entlogics.restaurantsystem.entity.Restaurant;
import com.entlogics.restaurantsystem.entity.RestaurantCustomer;
import com.entlogics.restaurantsystem.entity.RestaurantDish;
import com.entlogics.restaurantsystem.entity.Waiter;
import com.entlogics.restaurantsystem.util.DBConnectionUtil;

public class RestaurantDAO implements IRestaurantDAO {

	// method to list all restaurants
	@Override
	public List<Restaurant> getRestaurants() {

		// List initialization with restaurant as Generic Type to add restaurants to the
		// list
		List<Restaurant> listOfrestaurants = new ArrayList<>();

		// sql query string to get the list of restaurants from the database
		String sql = "SELECT restaurant_id, restaurant_name, restaurant_address, restaurant_type, restaurant_rating, restaurant_email, restaurant_phone \r\n"
				+ "FROM restaurant";

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			Statement statement = connection.createStatement();

			// result set object to store the result of the executed query giving list of
			// restaurants
			ResultSet resultSet = statement.executeQuery(sql);

			// looping over until resultSet object has data
			while (resultSet.next()) {

				// Creating restaurant object to be added to the list of restaurants
				Restaurant restaurant = new Restaurant();

				// getting attributes of restaurant from result set and setting them to our
				// restaurant object
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

	// method to create a restaurant
	@Override
	public Boolean createRestaurant(Restaurant restaurantData) {

		boolean flag = false;

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO restaurant(restaurant_id, restaurant_name, restaurant_address, restaurant_type, restaurant_rating, restaurant_email, restaurant_phone) VALUES(?, ?, ?, ?, ?, ?, ?)");

			// getting information from restaurantData to set prepared statement
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

			// result set object to store the result of the executed query giving info of a
			// Specific restaurant
			ResultSet restaurantResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (restaurantResultSet.next()) {

				// getting attributes of restaurant from result set and setting them to our
				// restaurant object
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

			// result set object to store the result of the executed query giving info of
			// customers in a Specific restaurant
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

			// result set object to store the result of the executed query giving info of
			// the waiters in a Specific restaurant
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

			// result set object to store the result of the executed query giving info of
			// the dishes in a Specific restaurant
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

	// method to edit a restaurant
	@Override
	public Boolean editRestaurant(Restaurant restaurantData, String restaurantID) {

		// to indicate the status post execution of edit restaurant method
		boolean flag = false;

		// storing existing information of restaurant by calling getASpecificRestaurant
		// method
		Restaurant existingRestaurant = getASpecificRestaurant(restaurantID);

		// if attributes are provided by the client in json request body of PUT, Then
		// use those attributes else if not provided, use existing attribute information
		// from existingRestaurant
		String restaurantName = restaurantData.getRestaurantName() != null ? restaurantData.getRestaurantName()
				: existingRestaurant.getRestaurantName();

		String restaurantAddress = restaurantData.getRestaurantAddress() != null ? restaurantData.getRestaurantAddress()
				: existingRestaurant.getRestaurantAddress();

		String restaurantType = restaurantData.getRestaurantType() != null ? restaurantData.getRestaurantType()
				: existingRestaurant.getRestaurantType();

		int restaurantRating = restaurantData.getRestaurantRating() != 0 ? restaurantData.getRestaurantRating()
				: existingRestaurant.getRestaurantRating();

		String restaurantEmail = restaurantData.getRestaurantEmail() != null ? restaurantData.getRestaurantEmail()
				: existingRestaurant.getRestaurantEmail();

		String restaurantPhone = restaurantData.getRestaurantPhone() != null ? restaurantData.getRestaurantPhone()
				: existingRestaurant.getRestaurantPhone();

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
		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO edit restaurant");
			e.printStackTrace();

		}

		return flag;

	}

	// method to delete a restaurant
	@Override
	public Boolean deleteRestaurant(String restaurantId) {

		// to indicate the status post execution of delete restaurant method
		boolean flag = false;

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM restaurant WHERE restaurant_id= ?");

			// getting information from restaurantData to set prepared statement
			preparedStatement.setString(1, restaurantId);

			preparedStatement.executeUpdate();
			flag = true;
		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO delete restaurant");
			e.printStackTrace();

		}

		return flag;
	}

	//method to get customers of a restaurant
	@Override
	public List<Customer> getRestaurantCustomer(int restaurant_id) {

		// declare list to hold the list of customers
		List<Customer> listOfcustomers = new ArrayList<>();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT customer.customer_id, customer_name, customer_address, customer_phone, customer_email, customer_status, customer_spent\r\n"
							+ "FROM customer\r\n" + "JOIN restaurant_customer\r\n"
							+ "ON customer.customer_id = restaurant_customer.customer_id\r\n"
							+ "WHERE restaurant_id = ?");
			preparedStatement.setInt(1, restaurant_id);

			// result set object to store the result of the executed query giving list of
			// customers of a restaurant
			ResultSet customerResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (customerResultSet.next()) {

				// New customer object
				Customer customer = new Customer();

				// getting attributes of customer from result set and setting them to our
				// customer object
				customer.setCustomerId(customerResultSet.getInt("customer_id"));
				customer.setCustomerName(customerResultSet.getString("customer_name"));
				customer.setCustomerAddress(customerResultSet.getString("customer_address"));
				customer.setCustomerEmail(customerResultSet.getString("customer_email"));
				customer.setCustomerPhone(customerResultSet.getString("customer_phone"));
				customer.setCustomerSpent(customerResultSet.getInt("customer_spent"));
				customer.setCustomerStatus(customerResultSet.getString("customer_status"));

				// Prepared statement for customerOrders
				PreparedStatement preparedStatementCustomer = connection
						.prepareStatement("select order_id from orders\r\n" + "WHERE customer_id = ?");

				preparedStatementCustomer.setInt(1, customerResultSet.getInt("customer_id"));

				// result set object to store the result of the executed query giving info of
				// orders of a customer
				ResultSet customerInfoResultSet = preparedStatementCustomer.executeQuery();

				// Declaring Order set (to avoid duplicates) to be added inside customer object
				Set<Order> listOfOrders = new LinkedHashSet<>();

				// looping over until resultSet object has data
				while (customerInfoResultSet.next()) {

					// Creating a new order object and adding properties from the database
					Order orders = new Order();
					orders.setOrderId(customerInfoResultSet.getInt("order_id"));

					// Prepared statement for orderItems
					PreparedStatement preparedStatementItems = connection
							.prepareStatement("SELECT item_id from order_items\r\n" + "WHERE order_id = ?");

					preparedStatementItems.setInt(1, customerInfoResultSet.getInt("order_id"));

					// result set object to store the result of the executed query giving info of
					// items in an order
					ResultSet itemInfoResultSet = preparedStatementItems.executeQuery();

					// Declaring dish list to be added inside order object
					List<Dish> listOfItems = new ArrayList<Dish>();

					// looping over until resultSet object has data
					while (itemInfoResultSet.next()) {

						// Creating a new dish object and adding properties from the database
						Dish dishes = new Dish();
						dishes.setDishId(itemInfoResultSet.getInt("item_id"));
						listOfItems.add(dishes);

						orders.setOrderItems(listOfItems);

						listOfOrders.add(orders);
					}

				}

				// set the list of orders to customer object
				customer.setCustomerOrders(listOfOrders);

				// add customer to list of customers
				listOfcustomers.add(customer);
			}

		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		// return the list of customers in a restaurant
		return listOfcustomers;

	}
	//method to get info of a specific customer in a restaurant
	@Override
	public Customer getRestaurantCustomerInfo(int customer_id) {

		// declare customer object to hold info of customer
		Customer customer = new Customer();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT customer.customer_id, customer_name, customer_address, customer_phone, customer_email, customer_status, customer_spent\r\n"
							+ "FROM customer\r\n" + "JOIN restaurant_customer\r\n"
							+ "ON customer.customer_id = restaurant_customer.customer_id\r\n"
							+ "WHERE restaurant_id = 1 AND customer.customer_id = ?");
			preparedStatement.setInt(1, customer_id);

			// result set object to store the result of the executed query giving info of a
			// customer of a restaurant
			ResultSet customerResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (customerResultSet.next()) {

				// getting attributes of customer from result set and setting them to our
				// customer object
				customer.setCustomerId(customerResultSet.getInt("customer_id"));
				customer.setCustomerName(customerResultSet.getString("customer_name"));
				customer.setCustomerAddress(customerResultSet.getString("customer_address"));
				customer.setCustomerEmail(customerResultSet.getString("customer_email"));
				customer.setCustomerPhone(customerResultSet.getString("customer_phone"));
				customer.setCustomerSpent(customerResultSet.getInt("customer_spent"));
				customer.setCustomerStatus(customerResultSet.getString("customer_status"));

				// Prepared statement for customerOrders
				PreparedStatement preparedStatementCustomer = connection
						.prepareStatement("select order_id from orders\r\n" + "WHERE customer_id = ?");

				preparedStatementCustomer.setInt(1, customerResultSet.getInt("customer_id"));

				// result set object to store the result of the executed query giving info of
				// orders of a customer
				ResultSet customerInfoResultSet = preparedStatementCustomer.executeQuery();

				// Declaring Order set (to avoid duplicates) to be added inside customer object
				Set<Order> listOfOrders = new LinkedHashSet<>();

				// looping over until resultSet object has data
				while (customerInfoResultSet.next()) {

					// Creating a new order object and adding properties from the database
					Order orders = new Order();
					orders.setOrderId(customerInfoResultSet.getInt("order_id"));

					// Prepared statement for orderItems
					PreparedStatement preparedStatementItems = connection
							.prepareStatement("SELECT item_id from order_items\r\n" + "WHERE order_id = ?");

					preparedStatementItems.setInt(1, customerInfoResultSet.getInt("order_id"));

					// result set object to store the result of the executed query giving info of
					// items in an order
					ResultSet itemInfoResultSet = preparedStatementItems.executeQuery();

					// Declaring dish list to be added inside order object
					List<Dish> listOfItems = new ArrayList<Dish>();

					// looping over until resultSet object has data
					while (itemInfoResultSet.next()) {

						// Creating a new dish object and adding properties from the database
						Dish dishes = new Dish();
						dishes.setDishId(itemInfoResultSet.getInt("item_id"));
						listOfItems.add(dishes);

						orders.setOrderItems(listOfItems);

						listOfOrders.add(orders);
					}

				}

				// set the list of orders to customer object
				customer.setCustomerOrders(listOfOrders);
			}

		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		// return the list of customers in a restaurant
		return customer;

	}

	// method to list all waiters
	@Override
	public List<Waiter> getRestaurantWaiters(int restaurant_id) {

		// declare a list of waiters to which waiters will be added later
		List<Waiter> listOfWaiters = new ArrayList<>();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select waiter_id, name, waiter_salary from waiter\r\n" + "WHERE restaurant_id = ?");
			preparedStatement.setInt(1, restaurant_id);

			// result set object to store the result of the executed query giving a list of
			// waiters of a restaurant
			ResultSet waiterResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (waiterResultSet.next()) {
				// create waiter object
				Waiter waiter = new Waiter();

				// getting attributes of waiter from result set and setting them to our waiter
				// object
				waiter.setWaiterId(waiterResultSet.getInt("waiter_id"));
				waiter.setWaiterName(waiterResultSet.getString("name"));
				waiter.setWaiterSalary(waiterResultSet.getInt("waiter_salary"));

				listOfWaiters.add(waiter);
			}
		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		// return the list of waiters in a restaurant
		return listOfWaiters;

	}
	//method to get info of a specific waiter
	@Override
	public Waiter getRestaurantWaitersInfo(int waiter_id) {

		// declare a waiter object to which waiters info will be added later
		Waiter waiterInfo = new Waiter();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection
					.prepareStatement("select waiter.waiter_id, name, waiter_salary\r\n" + "from waiter\r\n"
							+ "WHERE waiter.restaurant_id = 1 AND waiter.waiter_id =?");
			preparedStatement.setInt(1, waiter_id);

			// result set object to store the result of the executed query giving info of
			// waiter in a restaurant
			ResultSet waiterResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (waiterResultSet.next()) {
				// create waiter object
				Waiter waiter = new Waiter();

				// getting attributes of waiter from result set and setting them to our waiter
				// object
				waiter.setWaiterId(waiterResultSet.getInt("waiter_id"));
				waiter.setWaiterName(waiterResultSet.getString("name"));
				waiter.setWaiterSalary(waiterResultSet.getInt("waiter_salary"));

				// To create a prepared statement object
				PreparedStatement ordersPreparedStatement = connection
						.prepareStatement("Select order_id \r\n" + "from orders\r\n" + "WHERE waiter_id = ?");
				ordersPreparedStatement.setInt(1, waiter_id);

				// result set object to store the result of the executed query giving info of
				// waiter in a restaurant
				ResultSet ordersResultSet = ordersPreparedStatement.executeQuery();

				// Declaring Order set (to avoid duplicates) to be added inside waiter object
				Set<Order> listOfOrders = new LinkedHashSet<>();
				// looping over until resultSet object has data
				while (ordersResultSet.next()) {
					// create waiter object
					Order order = new Order();

					// getting attributes of order from result set and setting them to our order
					// object
					order.setOrderId(ordersResultSet.getInt("order_id"));
					listOfOrders.add(order);

				}
				// set waiter orders
				waiter.setWaiterOrders(listOfOrders);

				waiterInfo = waiter;
			}
		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		// return the list of waiters in a restaurant
		return waiterInfo;

	}
	
	//method to list dishes of a restaurant
	@Override
	public List<RestaurantDish> getRestaurantDishes(int restaurant_id) {

		// declare a list of RestaurantDish to which dishes will be added later
		List<RestaurantDish> listOfDishes = new ArrayList<>();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection
					.prepareStatement("select restaurant_dish.dish_id, price, dish_name \r\n"
							+ "from restaurant_dish\r\n" + "JOIN dish\r\n"
							+ "ON dish.dish_id = restaurant_dish.dish_id\r\n" + "Where restaurant_id = ?");
			preparedStatement.setInt(1, restaurant_id);

			// result set object to store the result of the executed query giving a list of
			// dishes of a restaurant
			ResultSet dishResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (dishResultSet.next()) {
				// create waiter object
				RestaurantDish dish = new RestaurantDish();

				// getting attributes of dish from result set and setting them to our dish
				// object
				dish.setDishID(dishResultSet.getInt("dish_id"));
				dish.setDishPrice(dishResultSet.getInt("price"));
				dish.setDishName(dishResultSet.getString("dish_name"));

				listOfDishes.add(dish);
			}
		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		// return the list of dishes in a restaurant
		return listOfDishes;

	}
	
	//method to get orders in a restaurant
	@Override
	public List<Order> getRestaurantOrders(int restaurant_id) {

		// declare a list of order to which orders will be added later
		List<Order> listOfOrders = new ArrayList<>();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a prepared statement object
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT order_id, customer_id, date, order_amount, type, waiter_id\r\n"
							+ "from orders\r\n" + "WHERE restaurant_id = ?");
			preparedStatement.setInt(1, restaurant_id);

			// result set object to store the result of the executed query giving a list of
			// orders of a restaurant
			ResultSet orderResultSet = preparedStatement.executeQuery();

			// looping over until resultSet object has data
			while (orderResultSet.next()) {
				// create waiter object
				Order orders = new Order();

				// getting attributes of orders from result set and setting them to our order
				// object
				orders.setOrderId(orderResultSet.getInt("order_id"));
				orders.setOrderDate(orderResultSet.getDate("date"));
				orders.setCustomerId(orderResultSet.getInt("customer_id"));
				orders.setOrderAmount(orderResultSet.getInt("order_amount"));
				orders.setType(orderResultSet.getString("type"));
				orders.setWaiterId(orderResultSet.getInt("waiter_id"));

				// To create a prepared statement object
				PreparedStatement itemsPreparedStatement = connection.prepareStatement(
						"select item_id, quantity\r\n" + "FROM order_items\r\n" + "WHERE order_id = ?");
				itemsPreparedStatement.setInt(1, orderResultSet.getInt("order_id"));

				// result set object to store the result of the executed query giving a list of
				// orders of a restaurant
				ResultSet itemsResultSet = itemsPreparedStatement.executeQuery();

				// Declaring OrderItems list to be added inside orders object
				List<OrderItems> listOrderItems = new ArrayList<OrderItems>();
				// looping over until resultSet object has data
				while (itemsResultSet.next()) {
					// create waiter object
					OrderItems orderItems = new OrderItems();

					// getting attributes of items from result set and setting them to our
					// orderItems
					// object
					orderItems.setItemId(itemsResultSet.getInt("item_id"));
					orderItems.setItemQuantity(itemsResultSet.getInt("quantity"));

					listOrderItems.add(orderItems);

				}
				orders.setOrderItem(listOrderItems);
				listOfOrders.add(orders);

			}
		} catch (IOException | SQLException e) {

			System.out.println("Inside catch block of restaurant DAO");
			e.printStackTrace();
		}

		// return the list of orders in a restaurant
		return listOfOrders;

	}

	//method to link dishes to a restaurant
	@Override
	public Boolean linkDishRestaurant(RestaurantDish dishtData, int restaurantId) {

		boolean flag = false;

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object (For dish table, due to foreign key constraint)
			PreparedStatement prePreparedStatement = connection
					.prepareStatement("INSERT INTO dish(dish_id, dish_name) VALUES (?, ?)");

			// getting information from dish Data to set prepared statement
			prePreparedStatement.setInt(1, dishtData.getDishID());
			prePreparedStatement.setString(2, dishtData.getDishName());

			prePreparedStatement.executeUpdate();

			// To create a statement object
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO restaurant_dish(dish_id, price, restaurant_id) VALUES(?, ?, ?)");

			// getting information from dishData to set prepared statement
			preparedStatement.setInt(1, dishtData.getDishID());
			preparedStatement.setInt(2, dishtData.getDishPrice());
			preparedStatement.setInt(3, restaurantId);

			preparedStatement.executeUpdate();
			flag = true;
		} catch (Exception e) {

			System.out.println("Inside catch block of restaurant DAO's linkDishRestaurant");
			e.printStackTrace();

		}
		return flag;

	}

	//method to link customers to a restaurant
	@Override
	public Boolean linkCustomerRestaurant(RestaurantCustomer customerData, int restaurant_id) {

		boolean flag = false;

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object (For customer table, due to foreign key constraint)
			PreparedStatement prePreparedStatement = connection
					.prepareStatement("INSERT INTO customer(customer_id, customer_name, customer_address, customer_phone, customer_email) VALUES (?,?,?,?,?)");

			// getting information from customer Data to set prepared statement
			prePreparedStatement.setInt(1, customerData.getCustomerId());
			prePreparedStatement.setString(2, customerData.getCustomerName());
			prePreparedStatement.setString(3, customerData.getCustomerAddress());
			prePreparedStatement.setString(4, customerData.getCustomerPhone());
			prePreparedStatement.setString(5, customerData.getCustomerEmail());

			prePreparedStatement.executeUpdate();

			// To create a statement object
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO restaurant_customer(restaurant_id, customer_id, customer_status, customer_spent) VALUES (?,?,?,?)");

			// getting information from customerData to set prepared statement
			preparedStatement.setInt(1, restaurant_id);
			preparedStatement.setInt(2, customerData.getCustomerId());
			preparedStatement.setString(3, customerData.getCustomerStatus());
			preparedStatement.setInt(4, customerData.getCustomerSpent());
			

			preparedStatement.executeUpdate();
			flag = true;
		} catch (Exception e) {

			System.out.println("Inside catch block of restaurant DAO's linkCustomerRestaurant");
			e.printStackTrace();

		}
		return flag;

	}
}

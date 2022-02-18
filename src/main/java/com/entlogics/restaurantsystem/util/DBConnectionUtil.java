package com.entlogics.restaurantsystem.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DBConnectionUtil {

	// Define database properties

	private static Properties properties;

	private static FileInputStream fileInput;

	private static String URL;

	private static String DRIVER;

	private static String USERNAME;

	private static String PASSWORD;

	private static Connection connection;

	// Create Method to return database connection

	public static Connection openConnection() throws IOException {

		properties = new Properties();

		// reading the properties file
//		fileInput = new FileInputStream("db.properties");
		
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("db.properties");

		// loading the file
		properties.load(in);

		// url of the database
		URL = properties.getProperty("url");
		System.out.println("url is: "+URL);

		// MySQL Driver
		DRIVER = properties.getProperty("driver");
		System.out.println("driver is "+ DRIVER);

		// Name of the database user
		USERNAME = properties.getProperty("username");
		System.out.println("username is " +USERNAME);

		// password of the database user
		PASSWORD = properties.getProperty("password");
		System.out.println("password is "+PASSWORD);

		try {

			// Loading the driver
			Class.forName(DRIVER);

			// get the connection
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}

		// return the connection
		
		return connection;
	}

}

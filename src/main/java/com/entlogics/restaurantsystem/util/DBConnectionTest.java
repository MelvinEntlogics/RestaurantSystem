package com.entlogics.restaurantsystem.util;

import java.sql.Connection;

public class DBConnectionTest {

	// Call openConnection method

	public static void main(String[] args) {
		try {
			Connection connection = DBConnectionUtil.openConnection();
			System.out.println("connection object is " + connection);
		} catch (Exception e) {
			System.out.println("Inside catch block of main method");
			e.printStackTrace();

		}
	}

}

package com.rays.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class DataValidator {

	public static boolean signUpValidation(HttpServletRequest request) {

		boolean isValid = true;

		System.out.println("firstName: " + request.getParameter("firstName"));

		if (request.getParameter("firstName") == "") {
			request.setAttribute("firstName", "firstName is required");
			System.out.println("firstName is required");
			isValid = false;
		}
		if (request.getParameter("lastName") == "") {
			request.setAttribute("lastName", "lastName is required");
			System.out.println("lastName is required");
			isValid = false;
		}
		if (request.getParameter("login") == "") {
			request.setAttribute("login", "login is required");
			System.out.println("login is required");
			isValid = false;
		} else if (!request.getParameter("login").endsWith("@gmail.com")) {
			request.setAttribute("login", "invalid login format");
			isValid = false;
		}
		if (request.getParameter("password") == "") {
			request.setAttribute("password", "password is required");
			System.out.println("password is required");
			isValid = false;
		} else if (!(request.getParameter("password").length() >= 8
				|| request.getParameter("password").length() <= 12)) {
			request.setAttribute("password", "Your password must be between 8 and 12 characters long.");
			System.out.println("password is required");
			isValid = false;
		}
		if (request.getParameter("dob") == "") {
			request.setAttribute("dob", "dob is required");
			System.out.println("dob is required");
			isValid = false;
		} else if (!(request.getParameter("dob") == "")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				Date dob = sdf.parse(request.getParameter("dob"));
				Date now = new Date();
				int age = now.getYear() - dob.getYear();
				if (!(age >= 18 && age <= 60)) {
					request.setAttribute("dob", "you are not eligible for this web site");
					isValid = false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isValid;

	}

	
    //  New validation for Payment form
	
//	public static boolean paymentValidation(HttpServletRequest request) {
//
//	    boolean isValid = true;
//
//	    System.out.println("Name: " + request.getParameter("Name"));
//
//	    if (request.getParameter("Name") == "") {
//	        request.setAttribute("Name", "Name is required");
//	        System.out.println("Name is required");
//	        isValid = false;
//	    }
//
//	    if (request.getParameter("Account") == "") {
//	        request.setAttribute("Account", "Account number is required");
//	        System.out.println("Account number is required");
//	        isValid = false;
//	    } else if (!request.getParameter("Account").matches("\\d{9,18}")) {
//	        request.setAttribute("Account", "Enter valid account number (9–18 digits)");
//	        System.out.println("Invalid account number");
//	        isValid = false;
//	    }
//
//	    if (request.getParameter("Upi_id") == "") {
//	        request.setAttribute("Upi_id", "UPI ID is required");
//	        System.out.println("UPI ID is required");
//	        isValid = false;
//	    } else if (!request.getParameter("Upi_id").matches("^[\\w.-]+@[a-zA-Z]+$")) {
//	        request.setAttribute("Upi_id", "Invalid UPI ID format (e.g., name@bank)");
//	        System.out.println("Invalid UPI ID format");
//	        isValid = false;
//	    }
//
//	    if (request.getParameter("Total_amount") == "") {
//	        request.setAttribute("Total_amount", "Total amount is required");
//	        System.out.println("Total amount is required");
//	        isValid = false;
//	    } else if (!request.getParameter("Total_amount").matches("\\d+(\\.\\d{1,2})?")) {
//	        request.setAttribute("Total_amount", "Enter valid amount");
//	        System.out.println("Invalid total amount");
//	        isValid = false;
//	    }
//
//	    if (request.getParameter("dob") == "") {
//	        request.setAttribute("dob", "Date of Birth is required");
//	        System.out.println("Date of Birth is required");
//	        isValid = false;
//	    }
//
//	    return isValid;
//	}
//

}
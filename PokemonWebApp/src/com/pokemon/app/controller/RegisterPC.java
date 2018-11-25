package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.pokemon.app.service.CheckService;
import com.pokemon.database.Database;

public class RegisterPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CheckService checkService = new CheckService();

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");

		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username != "" && password != "") {
				if (!checkService.isCheckUsername(username)) {
					PreparedStatement preparedStatement = Database.getConnection()
							.prepareStatement("insert into users (username,password) values (?,?)");
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					int i = preparedStatement.executeUpdate();
					if (i == 1) {
						json.put("msg", "You Are Successfully Registered! You may now Login");
						json.put("status", "success");
					} else {
						json.put("error", "Internal Server Error! Please Try Again After Sometime");
						json.put("status", "fail");
					}
				} else {
					json.put("status", "fail");
					json.put("error", "Your Username Already Registered! Please Enter Different Username");
				}
			} else {
				json.put("status", "fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}

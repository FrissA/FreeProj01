package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pokemon.app.dto.UserMapDto;
import com.pokemon.app.service.UserMapping;
import com.pokemon.database.Database;

public class LoginPC extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserMapping mapping = UserMapping.getInstance();

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		try {
			Statement statement = Database.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from users where username = '" + username + "' AND password = '" + password + "'");
			if (resultSet.next()) {
				session.setAttribute("id", resultSet.getInt("id"));
				session.setAttribute("username", resultSet.getString("username"));
				session.setAttribute("gameId", 0);
				UserMapDto userMapDto = new UserMapDto();
				userMapDto.setUsername(resultSet.getString("username"));
				mapping.getMap().put(resultSet.getInt("id"), userMapDto);
				json.put("status", "success");
				json.put("msg", "Your Are Successfully Login");
			} else {
				json.put("status", "fail");
				json.put("error", "Wrong Username And Password! Please Try Again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}

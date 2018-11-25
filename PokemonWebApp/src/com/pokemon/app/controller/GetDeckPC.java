package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pokemon.database.Database;

public class GetDeckPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		try {
			if (session.getAttribute("id") != null && session.getAttribute("id") != "") {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				int sessionId = Integer.parseInt(session.getAttribute("id").toString());
				Statement statement = Database.getConnection().createStatement();
				ResultSet resultSet = statement
						.executeQuery("select * from decks where status = 0 AND user_id = " + sessionId);
				while (resultSet.next()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", String.valueOf(resultSet.getInt("id")));
					map.put("cards", resultSet.getString("cards"));
					list.add(map);
				}
				if (!list.isEmpty()) {
					json.put("deck", list);
					json.put("status", "success");
				} else {
					json.put("status", "fail");
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
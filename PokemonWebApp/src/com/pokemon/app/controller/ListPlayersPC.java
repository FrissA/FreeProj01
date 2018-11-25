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

import com.pokemon.app.dto.UserMapDto;
import com.pokemon.app.service.UserMapping;
import com.pokemon.database.Database;

public class ListPlayersPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		try {
			if (session.getAttribute("id") != null && session.getAttribute("id") != "") {
				int sessionId = Integer.parseInt(session.getAttribute("id").toString());
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				Map<Integer, UserMapDto> loginUserMap = UserMapping.getInstance().getMap();
				for (int id : loginUserMap.keySet()) {
					Map<String, String> map = new HashMap<String, String>();
					UserMapDto userMapDto = loginUserMap.get(id);
					map.put("playerId", id + "");
					map.put("username", userMapDto.getUsername());

					Statement challengeStatement = Database.getConnection().createStatement();
					ResultSet challengeResultSet = challengeStatement.executeQuery(
							"select * from challenge where challengee = " + id + " and challenger= " + sessionId);
					if (challengeResultSet.next()) {
						if (challengeResultSet.getInt("status") == 0) {
							map.put("button", "Requested");
						} else if (challengeResultSet.getInt("status") == 3) {
							map.put("button", "Accepted");
						} else if (challengeResultSet.getInt("status") == 1) {
							map.put("button", "Challenge");
						}
					} else {
						map.put("button", "Challenge");
					}
					list.add(map);
				}
				if (!list.isEmpty()) {
					json.put("players", list);
					json.put("status", "success");
				} else {
					json.put("status", "fail");
					json.put("error", "No Challenges Available");
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
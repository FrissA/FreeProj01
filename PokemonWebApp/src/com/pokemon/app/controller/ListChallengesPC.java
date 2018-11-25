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

public class ListChallengesPC extends HttpServlet {
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
				Statement challengeStatement = Database.getConnection().createStatement();
				ResultSet challengeResultSet = challengeStatement
						.executeQuery("select * from challenge where status = 0 AND challengee = " + sessionId);
				while (challengeResultSet.next()) {
					Map<String, String> map = new HashMap<String, String>();
					Statement getchallengerStatement = Database.getConnection().createStatement();
					ResultSet getChallengerResultSet = getchallengerStatement.executeQuery(
							"select username from users where id = " + challengeResultSet.getInt("challenger"));
					while (getChallengerResultSet.next()) {
						map.put("challengerId", challengeResultSet.getInt("challenger") + "");
						map.put("username", getChallengerResultSet.getString("username"));
					}
					list.add(map);
				}
				if (!list.isEmpty()) {
					json.put("challenges", list);
					json.put("status", "success");
				} else {
					json.put("status", "fail");
					json.put("error", "No Challenges Available");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}

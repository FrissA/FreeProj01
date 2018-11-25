package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

public class Logout extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("username") != null || session.getAttribute("username") != "") {
				int userId = Integer.parseInt(session.getAttribute("id").toString());
				session.invalidate();
				json.put("status", "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				json.put("status", "fail");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		out.write(json.toString());
	}
}

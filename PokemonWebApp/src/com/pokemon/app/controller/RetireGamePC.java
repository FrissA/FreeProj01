package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pokemon.database.Database;

public class RetireGamePC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");

		/* Board Status wating = 0 and playing = 1 retire = 2 */
		try {
			HttpSession session = request.getSession();
			int sessionId = Integer.parseInt(session.getAttribute("id").toString());
			int gameId = Integer.parseInt(session.getAttribute("gameId").toString());
			PreparedStatement preparedStatement = Database.getConnection()
					.prepareStatement("update challenge set status = 2 where id = ?");
			preparedStatement.setInt(1, gameId);
			int i = preparedStatement.executeUpdate();
			if (i == 1) {
				session.setAttribute("gameId", 0);
				json.append("msg", "Retire From Game");
			} else {
				json.append("error", "Internal Server Error! Please Try Again After Sometime");
			}
		} catch (Exception e) {
		}
	}
}

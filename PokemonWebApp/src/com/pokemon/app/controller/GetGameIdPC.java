package com.pokemon.app.controller;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pokemon.database.Database;

public class GetGameIdPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		try {
			if (session.getAttribute("gameId") != null && session.getAttribute("gameId") != "") {
				int sessionId = Integer.parseInt(session.getAttribute("id").toString());

				Statement statementForGetBoard = Database.getConnection().createStatement();
				ResultSet resultSetForGetBoard = statementForGetBoard
						.executeQuery("select * from board where status = 0 AND player2 = " + sessionId);
				if (resultSetForGetBoard.next()) {
					int gameId = resultSetForGetBoard.getInt("id");
					int player1 = resultSetForGetBoard.getInt("player1");
					session.setAttribute("gameId", gameId);
					json.put("msg", "Found Game Id");
					json.put("player1", player1);
					PreparedStatement preparedStatement = Database.getConnection()
							.prepareStatement("update board set status = ? where id= ?");
					preparedStatement.setInt(1, 1);
					preparedStatement.setInt(2, gameId);
					int i = preparedStatement.executeUpdate();
					if (i == 1) {
						json.append("msg", "Both Players To the Bench");
					} else {
						json.append("error", "Internal Server Error! Please Try Again After Sometime");
					}
					/*
					 * Map<Integer, UserMapDto> loginUserMap = UserMapping.getInstance().getMap();
					 * UserMapDto userMapDto = loginUserMap.get(sessionId);
					 */
					Statement statementForGetUsername = Database.getConnection().createStatement();
					ResultSet resultSetForGetUsername = statementForGetUsername
							.executeQuery("select username from users where id = " + player1);
					if (resultSetForGetUsername.next()) {
						json.put("player1Username", resultSetForGetUsername.getString("username"));
					}
				}
			} else {
				json.put("error", "No Game Id Available");
			}
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		out.write(json.toString());
	}
}

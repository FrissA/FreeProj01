package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pokemon.database.Database;

public class AddCardToHandPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");

		/* Deck Status active = 0 and Inactive = 1 */
		try {
			HttpSession session = request.getSession();
			int userId = Integer.parseInt(session.getAttribute("id").toString());
			int gameId = Integer.parseInt(session.getAttribute("gameId").toString());
			int cardPosition = Integer.parseInt(request.getParameter("cardPosition"));

			Statement statement = Database.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from in_play_players where player_id=" + userId + " AND game_id = " + gameId);
			if (resultSet.next()) {
				String[] bench = resultSet.getString("bench").split(",");

				if (bench.length < 7) {
					String benchString = "";
					for (String string : bench) {
						if (!string.trim().equals("")) {
							benchString += string + ",";
						}
					}
					benchString += String.valueOf(cardPosition);
					int handSize = benchString.split(",").length;
					PreparedStatement preparedStatement = Database.getConnection().prepareStatement(
							"update in_play_players set bench = ?, hand_size = ? where game_id= ? AND player_id = ?");
					preparedStatement.setString(1, benchString);
					preparedStatement.setInt(2, handSize);
					preparedStatement.setInt(3, gameId);
					preparedStatement.setInt(4, userId);
					int i = preparedStatement.executeUpdate();
					if (i == 1) {
						json.append("msg", "Card add to Bench");
					} else {
						json.append("error", "Internal Server Error! Please Try Again After Sometime");
					}
				} else {
					json.append("error", "Only 7 Cards added to hand");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}

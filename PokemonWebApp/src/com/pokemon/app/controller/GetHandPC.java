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

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.json.JSONObject;

import com.pokemon.database.Database;

public class GetHandPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		int sessionId = Integer.parseInt(session.getAttribute("id").toString());
		String benchString = request.getParameter("bench");
		int playerId = Integer.parseInt(request.getParameter("playerId"));
		if (benchString != null && !benchString.trim().equals("")) {
			String[] bench = benchString.split(",");
			ObjectMapper mapper = new ObjectMapper();
			ArrayNode handNode = mapper.createArrayNode();
			JsonNode drawableCardNode = null;
			try {
				Statement checkHandStatement = Database.getConnection().createStatement();
				ResultSet checkHandResultSet = checkHandStatement
						.executeQuery("SELECT * FROM decks where status = 0 AND user_id =" + sessionId);
				if (checkHandResultSet.next()) {
					ArrayNode cardsNode = (ArrayNode) mapper.readTree(checkHandResultSet.getString("cards"));
					for (String card : bench) {
						if (!card.trim().equals("")) {
							int cardPosition = Integer.parseInt(card);
							drawableCardNode = cardsNode.get(cardPosition);
							handNode.add(drawableCardNode);
						}
					}
					json.put("bench", handNode);

					if (playerId != sessionId) {
						json.put("player2", "player2");
					}

				} else {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.write(json.toString());
	}
}
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

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.json.JSONObject;

import com.pokemon.app.service.DrawCardsPC;
import com.pokemon.database.Database;

public class UpdateDeckAndHandPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DrawCardsPC drawCards = new DrawCardsPC();

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		int sessionId = Integer.parseInt(session.getAttribute("id").toString());
		int cardPosition = Integer.parseInt(request.getParameter("cardPosition"));

		JsonNode nodeForAddDecks = drawCards.drawCardsFromHand(sessionId, cardPosition);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode node = null;
		try {
			Statement checkDeckStatement = Database.getConnection().createStatement();
			ResultSet checkDeckResultSet = checkDeckStatement
					.executeQuery("select * from decks where status = 0 AND user_id =" + sessionId);
			if (checkDeckResultSet.next()) {
				node = (ArrayNode) mapper.readTree(checkDeckResultSet.getString("cards"));
				node.add(nodeForAddDecks);
				PreparedStatement updateDecks = Database.getConnection()
						.prepareStatement("update decks set cards = ? where id=?");
				updateDecks.setString(1, node.toString());
				updateDecks.setInt(2, checkDeckResultSet.getInt("id"));
				updateDecks.execute();

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

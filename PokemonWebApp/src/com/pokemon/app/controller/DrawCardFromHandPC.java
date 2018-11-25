package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.JSONException;
import org.json.JSONObject;

import com.pokemon.app.service.DrawCardsPC;

public class DrawCardFromHandPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("id").toString());
		int cardPosition = Integer.parseInt(request.getParameter("cardPosition"));
		DrawCardsPC drawCards = new DrawCardsPC();
		JsonNode node = drawCards.drawCardsFromHand(userId, cardPosition);
		try {
			json.put("card", node);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}
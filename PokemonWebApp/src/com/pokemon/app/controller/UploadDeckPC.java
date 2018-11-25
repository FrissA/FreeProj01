package com.pokemon.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pokemon.database.Database;

public class UploadDeckPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("id") != null && session.getAttribute("id") != "") {
				int sessionId = Integer.parseInt(session.getAttribute("id").toString());
				String[] uploadDeck = request.getParameter("deck").toString().split("\n");
				List<String> cardsList = new ArrayList<String>();
				for (String string : uploadDeck) {
					String[] card = string.split(" ");
					cardsList.add("{" + "\"t\":\"" + card[0] + "\",\"n\":" + card[1] + "}");
				}

				if (uploadDeck.length != 0) {
					if (cardsList.size() == 40) {
						Statement checkDeckIdStatement = Database.getConnection().createStatement();
						ResultSet resultSet = checkDeckIdStatement
								.executeQuery("select * from decks where user_id = " + sessionId);
						if (resultSet.next()) {
							json.put("status", "fail");
							json.put("error", "Already Deck Available");
						} else {
							PreparedStatement preparedStatement = Database.getConnection()
									.prepareStatement("insert into decks (user_id,cards) values (?,?)");
							preparedStatement.setInt(1, sessionId);
							preparedStatement.setString(2, cardsList.toString());
							int i = preparedStatement.executeUpdate();
							if (i == 1) {
								json.put("status", "success");
								json.put("msg", "Decks Successfully Uploaded");
							} else {
								json.put("status", "fail");
								json.put("error", "Internal Server Error! Please Try Again After Sometime");
							}
						}
					} else {
						json.put("status", "fail");
						json.put("error", "Uploaded Deck Exist must be 40 Cards");
					}
				} else {
					json.put("status", "fail");
					json.put("error", "Please Upload Deck");
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
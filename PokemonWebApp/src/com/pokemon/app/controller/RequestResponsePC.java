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

public class RequestResponsePC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");

		try {
			HttpSession session = request.getSession();
			int sessionId = Integer.parseInt(session.getAttribute("id").toString());
			int challengerId = Integer.parseInt(request.getParameter("challengerId"));
			String requestResponse = request.getParameter("requestResponse");

			Statement checkDecksStatement = Database.getConnection().createStatement();
			ResultSet checkDecksResultSet = checkDecksStatement
					.executeQuery("select * from decks where status = 0 AND user_id = " + sessionId);
			if (checkDecksResultSet.next()) {
				PreparedStatement preparedStatement = null;
				if (requestResponse.equals("accept") || requestResponse.equals("refuse")) {
					if (requestResponse.equals("accept")) {
						preparedStatement = Database.getConnection().prepareStatement(
								"UPDATE challenge SET status = 3 where challenger = ? AND challengee = ? AND status = 0");

						preparedStatement.setInt(1, challengerId);
						preparedStatement.setInt(2, sessionId);
						int i = preparedStatement.executeUpdate();
						if (i == 1) {
							Statement getChallengerNameStatement = Database.getConnection().createStatement();
							ResultSet getChallengerName = getChallengerNameStatement
									.executeQuery("SELECT * FROM users where id = " + challengerId);
							while (getChallengerName.next()) {
								json.append("challengerId", getChallengerName.getInt("id"));
								json.append("challengerName", getChallengerName.getString("username"));
								json.append("challengeeId", sessionId);

								/*
								 * Map<Integer, UserMapDto> loginUserMap =
								 * UserMapping.getInstance().getMap();
								 * UserMapDto userMapDtoForChallenger =
								 * loginUserMap.get(getChallengerName.getInt(
								 * "id"));
								 * userMapDtoForChallenger.setBusy(true);
								 * 
								 * UserMapDto userMapDtoForChallengee =
								 * loginUserMap.get(sessionId);
								 * userMapDtoForChallengee.setBusy(true);
								 */
							}
							json.append("accept", "Your Response Send to the Challenger");
						} else {
							json.append("error", "Internal Server Error! Please Try Again After Sometime");
						}
					} else if (requestResponse.equals("refuse")) {
						preparedStatement = Database.getConnection().prepareStatement(
								"UPDATE challenge SET status = 1 where challenger = ? AND challengee = ? AND status = 0");

						preparedStatement.setInt(1, challengerId);
						preparedStatement.setInt(2, sessionId);
						int i = preparedStatement.executeUpdate();
						if (i == 1) {
							json.append("refuse", "Your Response Send to the Challenger");
						} else {
							json.append("error", "Internal Server Error! Please Try Again After Sometime");
						}
					}
				}
			} else {
				json.append("error", "No Decks Available in your account! Please Upload Decks First");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}
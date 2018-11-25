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

public class AddChallenge extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		/* Deck Status active = 0 and Inactive = 1 */
		try {
			if (session.getAttribute("id") != null && session.getAttribute("id") != "") {
				int challenger = Integer.parseInt(session.getAttribute("id").toString());
				int challengee = Integer.parseInt(request.getParameter("challengee"));

				if (challenger != challengee) {
					Statement checkPlayerStatement = Database.getConnection().createStatement();
					ResultSet checkplayerResultSet = checkPlayerStatement
							.executeQuery("select * from users where id = " + challengee);
					if (checkplayerResultSet.next()) {
						Statement checkDecksStatement = Database.getConnection().createStatement();
						ResultSet checkDecksResultSet = checkDecksStatement
								.executeQuery("select * from decks where status = 0 AND user_id = " + challenger);
						if (checkDecksResultSet.next()) {
							Statement checkRequestStatement = Database.getConnection().createStatement();
							ResultSet checkRequestResultSet = checkRequestStatement
									.executeQuery("select * from challenge where challengee = " + challengee
											+ " and challenger= " + challenger);
							if (checkRequestResultSet.next()) {
								PreparedStatement preparedStatement = Database.getConnection().prepareStatement(
										"update challenge set status = 0 where challengee = ? AND challenger= ?");
								preparedStatement.setInt(1, challengee);
								preparedStatement.setInt(2, challenger);
								int i = preparedStatement.executeUpdate();
								if (i == 1) {
									json.put("status", "success");
									json.append("msg", "Your Challenge Has Been Sent! Please Wait For Accept");
								} else {
									json.put("status", "fail");
									json.append("error", "Internal Server Error! Please Try Again After Sometime");
								}
							} else {
								PreparedStatement preparedStatement = Database.getConnection()
										.prepareStatement("insert into challenge (challenger,challengee) values (?,?)");
								preparedStatement.setInt(1, challenger);
								preparedStatement.setInt(2, challengee);
								int i = preparedStatement.executeUpdate();
								if (i == 1) {
									json.put("status", "success");
									json.append("msg", "Your Challenge Has Been Sent! Please Wait For Accept");
								} else {
									json.put("status", "fail");
									json.append("error", "Internal Server Error! Please Try Again After Sometime");
								}
							}
						} else {
							json.put("status", "fail");
							json.append("error", "No Decks Available in your account! Please Upload Decks First");
						}
					} else {
						json.put("status", "fail");
						json.append("error", "Challengee not exist");
					}
				} else {
					json.put("status", "fail");
					json.append("error", "No Challenge Request Send to Itself!");
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
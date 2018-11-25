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

public class AddBoardPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		InPlayPlayer inPlayPlayer = new InPlayPlayer();

		try {
			int player1 = Integer.parseInt(session.getAttribute("id").toString());
			int player2 = Integer.parseInt(request.getParameter("player2"));
			int deckIdForPlayer1 = 0;
			int deckIdForPlayer2 = 0;
			Statement statementForCheckGameId = Database.getConnection().createStatement();
			ResultSet resultSetForGameId = statementForCheckGameId
					.executeQuery("select * from board where status = 1 AND player2 = " + player1);
			if (!resultSetForGameId.next()) {
				Statement statementForPlayer1 = Database.getConnection().createStatement();
				ResultSet resultSetForPlayer1 = statementForPlayer1.executeQuery(
						"select deck_id from decks where id = (SELECT MIN(id) FROM decks where status = 0 AND user_id ="
								+ player1 + ")");
				if (resultSetForPlayer1.next()) {
					deckIdForPlayer1 = resultSetForPlayer1.getInt("deck_id");
				}

				Statement statementForPlayer2 = Database.getConnection().createStatement();
				ResultSet resultSetForPlayer2 = statementForPlayer2.executeQuery(
						"select deck_id from decks where id = (SELECT MIN(id) FROM decks where status = 0 AND user_id ="
								+ player2 + ")");
				if (resultSetForPlayer2.next()) {
					deckIdForPlayer2 = resultSetForPlayer2.getInt("deck_id");
				}

				if (deckIdForPlayer1 != 0 && deckIdForPlayer2 != 0) {
					PreparedStatement preparedStatement = Database.getConnection()
							.prepareStatement("insert into board (player1,player2,deck1,deck2) values (?,?,?,?)");
					preparedStatement.setInt(1, player1);
					preparedStatement.setInt(2, player2);
					preparedStatement.setInt(3, deckIdForPlayer1);
					preparedStatement.setInt(4, deckIdForPlayer2);
					int i = preparedStatement.executeUpdate();
					if (i == 1) {
						Statement statementForGetGameId = Database.getConnection().createStatement();
						ResultSet resultSetForGetGameId = statementForGetGameId
								.executeQuery("select * from board where status = 0 AND player1 = " + player1);
						if (resultSetForGetGameId.next()) {
							int gameId = resultSetForGetGameId.getInt("id");
							session.setAttribute("gameId", gameId);
							inPlayPlayer.addInPlayPlayer(gameId, player1);
						}
						json.append("msg", "You Are Successfully Registered! You may now Login");
					} else {
						json.append("error", "Internal Server Error! Please Try Again After Sometime");
					}
				}

			} else {
				inPlayPlayer.addInPlayPlayer(resultSetForGameId.getInt("id"), player1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(json.toString());
	}
}

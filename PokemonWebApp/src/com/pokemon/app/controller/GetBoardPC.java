package com.pokemon.app.controller;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;

import com.pokemon.app.dto.Board;
import com.pokemon.app.dto.Data;
import com.pokemon.app.dto.UserMapDto;
import com.pokemon.app.service.UserMapping;
import com.pokemon.database.Database;

public class GetBoardPC extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		String jsonBoard = "";

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("gameId") != null && session.getAttribute("gameId") != "") {
				Map<Integer, UserMapDto> loginUserMap = UserMapping.getInstance().getMap();
				int gameId = Integer.parseInt(session.getAttribute("gameId").toString());
				List<Integer> playersList = new ArrayList<Integer>();
				List<Integer> decksList = new ArrayList<Integer>();

				int player1 = 0;
				int player2 = 0;

				if (gameId != 0) {
					Statement statementForGetBoard = Database.getConnection().createStatement();
					ResultSet resultSetForGetBoard = statementForGetBoard
							.executeQuery("select * from board where id = " + gameId);
					while (resultSetForGetBoard.next()) {
						player1 = resultSetForGetBoard.getInt("player1");
						player2 = resultSetForGetBoard.getInt("player2");

						playersList.add(resultSetForGetBoard.getInt("player1"));
						playersList.add(resultSetForGetBoard.getInt("player2"));
						decksList.add(resultSetForGetBoard.getInt("deck1"));
						decksList.add(resultSetForGetBoard.getInt("deck2"));
					}

					if (loginUserMap.containsKey(player1) || loginUserMap.containsKey(player2)) {
						Data data = new Data();
						Board board = new Board();
						board.setDecks(decksList);
						board.setPlayers(playersList);

						Statement statementForGetPlayerDetails = Database.getConnection().createStatement();
						ResultSet resultSetForGetPlayerDetails = statementForGetPlayerDetails
								.executeQuery("select * from in_play_players where game_id = " + gameId);
						while (resultSetForGetPlayerDetails.next()) {
							Map<String, Object> playerDetails = new HashMap<String, Object>();
							playerDetails.put("status", resultSetForGetPlayerDetails.getString("status"));
							playerDetails.put("handsize", resultSetForGetPlayerDetails.getInt("hand_size"));
							playerDetails.put("decksize", resultSetForGetPlayerDetails.getInt("deck_size"));
							playerDetails.put("discardsize", resultSetForGetPlayerDetails.getInt("discard_size"));

							List<Integer> listBench = new ArrayList<Integer>();
							String[] bench = resultSetForGetPlayerDetails.getString("bench").split(",");
							for (String string : bench) {
								if (!string.trim().equals("")) {
									listBench.add(Integer.parseInt(string));
								}
							}
							playerDetails.put("bench", listBench);
							board.getPlay().put(String.valueOf(resultSetForGetPlayerDetails.getInt("player_id")),
									playerDetails);
						}
						data.setBoard(board);
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						jsonBoard = ow.writeValueAsString(data);
						jsonBoard = jsonBoard.replace("\n", "").replace(" ", "");
					}
				}

			} else {
				json.put("error", "no Game Id Available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(jsonBoard);
	}
}
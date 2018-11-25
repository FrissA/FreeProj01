package com.pokemon.app.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import com.pokemon.database.Database;

public class DrawCardsPC {

	public JsonNode drawCardsFromDeck(int userId, int cardPosition) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode node = null;
		JsonNode drawableCardNode = null;
		try {
			Statement checkDeckStatement = Database.getConnection().createStatement();
			ResultSet checkDeckResultSet = checkDeckStatement
					.executeQuery("select * from decks where status = 0 AND user_id =" + userId);
			if (checkDeckResultSet.next()) {
				node = (ArrayNode) mapper.readTree(checkDeckResultSet.getString("cards"));
				drawableCardNode = node.get(cardPosition);
				node.remove(cardPosition);
				PreparedStatement updateDecks = Database.getConnection()
						.prepareStatement("update decks set cards = ? where id=?");
				updateDecks.setString(1, node.toString());
				updateDecks.setInt(2, checkDeckResultSet.getInt("id"));
				updateDecks.execute();

				if (node.size() == 0) {
					inActiveDeckStatus(checkDeckResultSet.getInt("id"));
				}

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return drawableCardNode;
	}

	public JsonNode drawCardsFromHand(int userId, int cardPosition) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode node = null;
		JsonNode drawableCardNode = null;
		try {
			Statement checkHandStatement = Database.getConnection().createStatement();
			ResultSet checkHandResultSet = checkHandStatement
					.executeQuery("SELECT * FROM hands where user_id =" + userId);
			if (checkHandResultSet.next()) {
				node = (ArrayNode) mapper.readTree(checkHandResultSet.getString("hand"));
				drawableCardNode = node.get(cardPosition);
				node.remove(cardPosition);
				PreparedStatement updateHands = Database.getConnection()
						.prepareStatement("update hands set hand = ? where user_id = ?");
				updateHands.setString(1, node.toString());
				updateHands.setInt(2, userId);
				updateHands.execute();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return drawableCardNode;
	}

	private void inActiveDeckStatus(int id) {
		try {
			PreparedStatement updateDecks = Database.getConnection()
					.prepareStatement("update decks set status = 1 where id = ?");
			updateDecks.setInt(1, id);
			updateDecks.execute();
		} catch (Exception e) {
		}
	}
}
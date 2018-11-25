package com.pokemon.app.controller;

import java.sql.PreparedStatement;

import com.pokemon.database.Database;

public class InPlayPlayer {
	public void addInPlayPlayer(int gameId, int sessionId) {
		
		try {
			PreparedStatement preparedStatement = Database.getConnection()
					.prepareStatement("insert into in_play_players (game_id,player_id) values (?,?)");
			preparedStatement.setInt(1, gameId);
			preparedStatement.setInt(2, sessionId);
			preparedStatement.execute();
		} catch (Exception e) {
		}
	}
}
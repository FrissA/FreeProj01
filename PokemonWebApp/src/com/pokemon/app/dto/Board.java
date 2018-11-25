package com.pokemon.app.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private List<Integer> players;
	private List<Integer> decks;
	private Map<String, Map<String, Object>> play = new HashMap<String, Map<String, Object>>();

	public List<Integer> getPlayers() {
		return players;
	}

	public void setPlayers(List<Integer> players) {
		this.players = players;
	}

	public List<Integer> getDecks() {
		return decks;
	}

	public void setDecks(List<Integer> decks) {
		this.decks = decks;
	}

	public Map<String, Map<String, Object>> getPlay() {
		return play;
	}

	public void setPlay(Map<String, Map<String, Object>> play) {
		this.play = play;
	}

}
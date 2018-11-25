package com.pokemon.app.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pokemon.database.Database;

public class CheckService {
	

	public boolean isCheckUsername(String username) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = Database.getConnection().prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return flag;
	}

}

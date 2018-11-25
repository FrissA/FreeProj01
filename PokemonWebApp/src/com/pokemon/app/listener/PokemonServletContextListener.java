package com.pokemon.app.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pokemon.app.service.UserMapping;

public class PokemonServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	// Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent event) {
		UserMapping.getInstance();
	}
}

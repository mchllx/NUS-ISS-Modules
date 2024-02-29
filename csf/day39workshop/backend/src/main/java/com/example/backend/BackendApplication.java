package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.backend.service.PokemonService;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	// @Autowired
	// private PokemonService pokeSvc;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("start");

		// // NEGATIVE
		// pokeSvc.getPokemon("piakchu");

		// // POSITIVE
		// pokeSvc.getPokemon("shroomish");
	}

}

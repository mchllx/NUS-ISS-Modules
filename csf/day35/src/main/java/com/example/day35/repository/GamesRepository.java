package com.example.day35.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.day35.model.Game;

@Repository
public class GamesRepository {

    public List<Game> findGameByName(String name) {
        return null;
    }
    
}

package com.example.german_japan.model;

public class Client {
    int id;
    String name;
    GameSettings gameSettings;

    public Client()
    {
        this.gameSettings = new GameSettings();
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}

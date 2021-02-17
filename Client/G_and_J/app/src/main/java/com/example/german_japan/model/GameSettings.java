package com.example.german_japan.model;
public class GameSettings
{
    String password;
    int players = 4;
    int startingCards = 17;
    int deckCount = 2;

    public GameSettings(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getStartingCards() {
        return startingCards;
    }

    public void setStartingCards(int startingCards) {
        this.startingCards = startingCards;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    boolean validateGameSettings()
    {
        return true;
    }
}
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Feb  7 15:20:16 2021

@author: gautam
"""
from card import Deck

class Game(object):
    
    def __init__(self, game_id, game_settings):
        self.game_id = game_id
        self.game_settings = game_settings
        self.players = [None]*self.game_settings.player_cnt
        self.deck = Deck(self.game_settings.deck_cnt)

    def add_player(self, player, idx):
        if idx < 1 or idx >= self.game_settings.player_cnt:
            raise Exception('Not enough space in the game to add the player')
        if self.players[idx-1] != None:
            raise Exception('Game space is already occupied; please choose another space')
        self.players[idx-1] = player
        
    def init_game(self):
        for i in range(self.game_settings.player_cnt):
            if self.players[i] == None:
                raise Exception('Can not initialize game with empty player space')
        for c in range(self.player_start_cards):
            for p in self.players:
                p.hand.append(self.deck.cards.pop())

class GameSettings(object):
    
    def __init__(self):
        self.deck_cnt = 2
        self.player_cnt = 4
        self.player_start_cards = 17

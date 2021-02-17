#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Feb  7 15:16:20 2021

@author: gautam
"""
import enum
import random

class Card(object):
    
    def __init__(self, value, suit):
        self.value = value
        self.suit = suit
        
class CardSuit(enum.Enum):
    SPADE = 1
    DIAMOND = 2
    CLUB = 3
    HEART = 4
    
class Deck(object):
    
    def __init__(self, deck_cnt):
        self.cards = Deck.make_deck(deck_cnt)
        random.shuffle(self.cards)

    def make_deck(decks):
        cards = []
        for deck in range(decks):
            for val in range(1,14):
                for k in CardSuit._value2member_map_:
                    cards.append(Card(val, CardSuit._value2member_map_[k]))
        return cards
    

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Feb  7 15:14:06 2021

@author: gautam
"""

class Player(object):
    
    def __init__(self, name):
        self.name = name

    def create_table(con):
        cur = con.cursor()
        cur.execute("select count(*) from sqlite_master where type = 'table' and name='player'")
        if cur.fetchone()[0] == 0:
            cur.execute('create table player (name text)')
        con.commit()
    
    def insert(self, con):
        cur = con.cursor()
        cur.execute("insert into player values (?)", (self.name,))
        con.commit()
        
    def from_list(l):
        return Player(l[0])
        
    def select_players(con, qry):
        pl = []
        cur = con.cursor()
        cur.execute(qry)
        for r in cur.fetchall():
            pl.append(Player.from_list(r))
        return pl
        
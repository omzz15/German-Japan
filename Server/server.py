from flask import Flask, request, jsonify
import sqlite3

db_name = 'gj_card_game.db'
app = Flask(__name__)

@app.route('/player/register', methods=['POST'])
def player_register():
    save_player_info(request.json)
    return 'Hello, World!'

@app.route('/player/get', methods=['GET'])
def player_get():
    get_all_player_info()
    return 'Hello, World!'

def get_all_player_info():
    con = sqlite3.connect(db_name)
    cur = con.cursor()
    cur.execute('select * from player')
    l = cur.fetchall();
    print(l)
    for r in l:
        print(r)
    con.close()
    
def save_player_info(playerJson):
    name = playerJson['name']
    con = sqlite3.connect(db_name)
    cur = con.cursor()
    cur.execute("insert into player values (?)", (name,))
    con.commit()
    con.close()
    
def add_player_table(con):
    cur = con.cursor()
    cur.execute("select count(*) from sqlite_master where type = 'table' and name='player'")
    if cur.fetchone()[0] == 0:
        cur.execute('create table player (name text)')
    con.commit()        
    
def db_init():
    con = sqlite3.connect(db_name)
    #con = sqlite3.connect(':memory:')
    add_player_table(con)    
    con.close()

if __name__ == '__main__':
    db_init()
    app.run()
    
    


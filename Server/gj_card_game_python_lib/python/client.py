import json

class GameSetting:
    
    def __init__(self, password='', players=4, deckCount=2, startingCards=17):
        self.password = password
        self.players = players
        self.deckCount = deckCount
        self.startingCards = startingCards

    @classmethod
    def from_json(cls, data: dict):
        return cls(**data)
        
class Client:
    
    def __init__(self, id, name, gameSettings):
        self.id = id
        self.name = name
        self.gameSettings = gameSettings
        if self.gameSettings == None:
            self.gameSettings = GameSetting()
        
    def toJson(self):
        return json.dumps(self.__dict__, lambda o: o.__dict__)

    @classmethod
    def from_json(cls, data: dict):
        c = cls(**data)
        c.gameSettings = GameSetting.from_json(data["gameSettings"])
        return c

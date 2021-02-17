import boto3

s3_res = boto3.resource('s3')
bucket = 'gpatel-bucket'
game_folder = 'german_japan_card_game_data'
client_folder = game_folder + '/clients/'
id_file = game_folder + '/identifiers.json'

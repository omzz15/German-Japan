import json
from types import SimpleNamespace
from client import Client
import constants
from util import object_to_dict

def lambda_function_impl(event, context):
    cid = None
    if 'clientId' not in event or event['clientId'] == 0:
        obj = constants.s3_res.Object(constants.bucket, constants.id_file)
        obj_body = obj.get()['Body']
        idObj = json.loads(obj_body.read(), object_hook=lambda d: SimpleNamespace(**d))
        cid = idObj.next_client_id
        idObj.next_client_id += 1
        idDict = object_to_dict(idObj)
        obj.put(Body=json.dumps(idDict))
    else:
        cid = event['clientId']
    eBody = event['body']
    c = Client.from_json(eBody)
    c.id = cid
    client_file = f'{cid}.json'
    obj = constants.s3_res.Object(constants.bucket, constants.client_folder + client_file)
    obj.put(Body=json.dumps(object_to_dict(c)))
    return {
        'statusCode': 200,
        'body': json.dumps(object_to_dict(c))
    }

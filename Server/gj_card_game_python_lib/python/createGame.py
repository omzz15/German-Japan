from util import object_to_dict

def lambda_function_impl(event, context):
    return {
        'statusCode': 200,
        'body': json.dumps(object_to_dict(c))
    }

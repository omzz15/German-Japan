
def object_to_dict(object):
    dict = vars(object)
    for k,v in dict.items():
        if type(v).__name__ not in ['list', 'dict', 'str', 'int', 'float']:
                dict[k] = object_to_dict(v)
        if type(v) is list:
            dict[k] = list_object_to_dict(v)
    return dict

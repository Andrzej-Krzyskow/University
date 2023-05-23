import requests


def get_cat_facts(number_of_facts):
    args = {"amount": number_of_facts}
    request = requests.get("https://cat-fact.herokuapp.com/facts/random",
                           params=args)
    print()
    for x in request.json():
        print(x["text"])
    print()
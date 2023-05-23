from factory import Factory, Faker
from Classes.DataRow import DataRow


class RowFactory(Factory):
    class Meta:
        model = DataRow

    id = Faker("ssn")
    player_class = "NEUTRAL"
    type = "MINION"
    name = Faker("name")
    set = ""
    text = ""
    cost = Faker("random_int", min=0, max=10)
    attack = Faker("random_int", min=0, max=10)
    health = Faker("random_int", min=0, max=10)
    rarity = "COMMON"
    collectible = ""
    flavor = ""
    mechanics = ""
    dust = ""
    play_requirements = ""
    race = ""
    how_to_earn = ""
    how_to_earn_golden = ""
    targeting_arrow_text = ""
    faction = ""
    durability = ""
    entourage = ""

from dataclasses import dataclass


@dataclass
class DataRow:
    id: str
    player_class: str
    type: str
    name: str
    set: str
    text: str
    cost: str
    attack: str
    health: str
    rarity: str
    collectible: str
    flavor: str
    mechanics: str
    dust: str
    play_requirements: str
    race: str
    how_to_earn: str
    how_to_earn_golden: str
    targeting_arrow_text: str
    faction: str
    durability: str
    entourage: str


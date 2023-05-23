from Functions.avg_attack import avg_attack
from Functions.median_health import median_health
from Functions.count_costs import count_costs
from Functions.count_types import count_types
from Functions.total_cards import total_cards
from Functions.total_dust_cost import total_dust_cost


def print_to_console(rows):
    print(f"avg attack: {avg_attack(rows):.5f}")
    print(f"median health: {median_health(rows)}")
    print(count_types(rows))
    print(count_costs(rows))
    print(f"total cards: {total_cards(rows)}")
    print(f"total dust cost: {total_dust_cost(rows)}")
from Classes.DataContainer import DataContainer
from Functions.avg_attack import avg_attack
from Functions.count_costs import count_costs
from Functions.count_types import count_types
from Functions.median_health import median_health
from Functions.total_cards import total_cards
from Functions.total_dust_cost import total_dust_cost
from Tests.factories import RowFactory


def test_1():
    container = DataContainer()

    rows = [
        RowFactory.create(
            id="1",
            type="MINION",
            cost="1",
            attack="2",
            health="10",
            dust="[40, 400, 5, 40]"
        ),
        RowFactory.create(
            id="2",
            type="MINION",
            cost="2",
            attack="4",
            health="8",
            dust="[400, 1600, 5, 40]"
        ),
        RowFactory.create(
            id="3",
            type="SPELL",
            cost="3",
            attack="",
            health="",
            dust="[100, 400, 5, 40]"
        )]
    for row in rows:
        container.add_row(row)

    avg_atk = avg_attack(container.rows)
    median_hp = median_health(rows)
    types_no = count_types(rows)
    costs = count_costs(rows)
    cards_sums = total_cards(rows)
    dust_sum = total_dust_cost(rows)

    assert avg_atk == 3
    assert median_hp == 9
    assert types_no == {"MINION":2, "SPELL":1}
    assert costs == {'1': 1, '2': 1, '3': 1}
    assert cards_sums == 3
    assert dust_sum == 540


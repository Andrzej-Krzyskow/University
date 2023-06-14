from hydra import initialize, compose

from Utils.sql_processing import *


def test_1():
    with initialize(version_base=None, config_path="../conf"):
        cfg = compose(config_name="config.yaml")

    sum_result = float(calculate(cfg, "cards - Copy.json", "sum", "cost"))
    avg_result = float(calculate(cfg, "cards - Copy.json", "average", "cost"))

    assert round(sum_result, 2) == 29.00
    assert round(avg_result, 2) == 4.83


def test_2():
    with initialize(version_base=None, config_path="../conf"):
        cfg = compose(config_name="config.yaml")

    sum_result = float(calculate(cfg, "tv.json", "sum", "vote_average"))
    avg_result = float(calculate(cfg, "tv.json", "average", "vote_average"))

    assert round(sum_result, 2) == 21.60
    assert round(avg_result, 2) == 7.20

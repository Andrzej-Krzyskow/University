from hydra import initialize, compose

from GUI.gui_main import run_gui


def run():
    with initialize(version_base=None, config_path="conf"):
        cfg = compose(config_name="config.yaml")

    run_gui(cfg)


if __name__ == '__main__':
    run()

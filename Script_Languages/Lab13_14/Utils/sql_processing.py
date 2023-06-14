import json
import os.path

from sqlalchemy import create_engine, Column, String, Numeric, func
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.automap import automap_base
from Classes.exceptions import *
import matplotlib.pyplot as plt


def establish_database_connection(db_file_path):
    engine = create_engine(f"sqlite:///{db_file_path}")
    base = automap_base()
    base.prepare(engine, reflect=True)
    session_maker = sessionmaker(bind=engine)
    session = session_maker()
    return base, session


def criteria_str_to_list(criteria):
    criteria_list = criteria.strip("()").split(', ')
    if len(criteria_list) == 1:
        criteria_list[0] = criteria_list[0].strip("',")
    criteria_list = [x.strip("'") for x in criteria_list]
    return criteria_list


def create_graph(cfg, database_filename, column_name):
    db_file_path = cfg.db.save_path + database_filename[:-4] + "db"

    base, session = establish_database_connection(db_file_path)
    series = base.classes.series

    try:
        query_data = session.query(getattr(series, column_name)).all()
        values = [data[0] for data in query_data]

        fig, ax = plt.subplots(figsize=(8, 4))
        ax.set_title(f"Distribution of values in {column_name} column as histogram")
        ax.set_xlabel("Values")
        ax.set_ylabel("Frequency")

        n, bins, patches = ax.hist(values, bins=10, edgecolor='black')
        ax.set_xticks(bins)

        plt.close(fig)
        return fig

    except AttributeError:
        raise AttributeError
    finally:
        session.close()
        session.bind.dispose()


def calculate(cfg, database_filename, function_name, column_name):
    db_file_path = cfg.db.save_path + database_filename[:-4] + "db"

    base, session = establish_database_connection(db_file_path)
    series = base.classes.series

    try:
        rows = session.query(series).all()

        result = 0
        match function_name:
            case "average":
                result = session.query(func.avg(getattr(series, column_name))).scalar()
                print(f"Average {column_name}: {result}")

            case "sum":
                result = session.query(func.sum(getattr(series, column_name))).scalar()
                print(f"Average {column_name}: {result}")

        return result

    except AttributeError:
        raise AttributeError
    finally:
        session.close()
        session.bind.dispose()


def get_available_criteria(cfg, database_filename):
    json_file_path = cfg.db.save_path + database_filename

    with open(json_file_path, 'r', encoding="latin-1") as file:
        json_data = json.load(file)

    if not isinstance(json_data, list) or len(json_data) < 1:
        raise JsonNotSupportedError

    available_criteria = json_data[0].keys()

    return available_criteria


def get_show_data(cfg, criteria, database_filename):
    db_file_path = cfg.db.save_path + database_filename[:-4] + "db"

    criteria_list = criteria_str_to_list(criteria)

    base, session = establish_database_connection(db_file_path)
    series = base.classes.series

    data = []
    try:
        query_data = session.query(series).all()

        # print("---------START FROM SHOW----------")
        for series in query_data:
            db_element = ()
            for criteria in criteria_list:
                atr = getattr(series, criteria)
                db_element = db_element + (atr,)
                # print(f"{criteria}: {atr}")
            data.append(db_element)
        # print("-------END FROM SHOW------------")

    except AttributeError:
        raise MissingHeadersError
    finally:
        session.close()
        session.bind.dispose()
        return criteria_list, data


def prepare_database(cfg, criteria, database_filename):
    base = declarative_base()

    class Data(base):
        __tablename__ = 'series'
        id = Column(String, primary_key=True)

    db_file_path = cfg.db.save_path + database_filename[:-4] + "db"
    json_file_path = cfg.db.save_path + database_filename

    criteria_list = criteria_str_to_list(criteria)

    with open(json_file_path, 'r', encoding="latin-1") as file:
        json_data = json.load(file)

    if not isinstance(json_data, list):
        raise JsonNotSupportedError

    if os.path.exists(db_file_path):
        os.remove(db_file_path)

    engine = create_engine(f"sqlite:///{db_file_path}")

    if not all(criteria in json_data[0] for criteria in criteria_list):
        raise MissingHeadersError

    for criteria in criteria_list:
        if criteria != "id":
            setattr(Data, criteria, Column(Numeric() if is_numeric(json_data[0][criteria]) else String))

    base.metadata.create_all(engine)

    session_maker = sessionmaker(bind=engine)
    session = session_maker()

    for item in json_data:
        series = Data(id=item['id'])
        if not all(criteria in item for criteria in criteria_list):
            continue
        for criteria in criteria_list:
            if criteria != "id":
                value = item[criteria]
                if is_numeric(value):
                    value = int(value) if float(value).is_integer() else round(float(value), 2)
                setattr(series, criteria, value)

        session.add(series)

    session.commit()
    # print_data_to_console(Data, session,criteria_list)

    session.close()
    session.bind.dispose()


def is_numeric(value):
    try:
        float(value)
        return True
    except ValueError:
        return False


def print_data_to_console(Data, session, criteria_list):
    series_data = session.query(Data).all()
    for series in series_data:
        for criteria in criteria_list:
            print(f"{criteria}: {getattr(series, criteria)}")

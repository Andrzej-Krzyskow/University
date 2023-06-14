import os
import shutil
import sys
import zipfile

from kaggle import KaggleApi

from Classes.exceptions import *


def search_kaggle(search):
    api = KaggleApi()
    api.authenticate()

    # if set to json, it won't always give all the valid results
    search = api.dataset_list(search=search, file_type="json")

    return search


def download_kaggle_json(cfg, database_name):
    api = KaggleApi()
    api.authenticate()

    tmp_save_path = cfg.db.tmp_save
    destination_save_path = cfg.db.save_path
    remove_files_in_directory(tmp_save_path)

    # search for .json file, if not found, raise exception
    files_list = api.dataset_list_files(database_name).files
    json_file_name = ""

    for file in files_list:
        if str(file).endswith('.json'):
            json_file_name = str(file)
            api.dataset_download_file(database_name, json_file_name, tmp_save_path)
            extract_zip(tmp_save_path, json_file_name)

    if json_file_name:
        move_files_with_extension(tmp_save_path, destination_save_path)
    else:
        api.dataset_download_files(database_name, path=tmp_save_path, unzip=True)
        files_list = []
        move_files_with_extension(tmp_save_path, destination_save_path, files_list)
        if len(files_list) > 0:
            json_file_name = files_list[0]

    remove_files_in_directory(tmp_save_path)
    if not json_file_name:
        raise NoJsonFileFoundError


def remove_files_in_directory(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            os.remove(file_path)


def extract_zip(save_path, json_file_name):
    # extract and delete zip file if it was downloaded as zip
    zip_path = save_path + json_file_name + ".zip"

    if os.path.exists(zip_path):
        with zipfile.ZipFile(zip_path, 'r') as zip_file:
            zip_file.extractall(save_path)
        os.remove(zip_path)


def move_files_with_extension(source_directory, destination_directory, files_list=None, extension=".json"):
    if files_list is None:
        files_list = []

    for root, dirs, files in os.walk(source_directory):
        for file in files:
            if file.endswith(extension):
                source_path = os.path.join(root, file)
                destination_path = os.path.join(destination_directory, file)
                shutil.move(source_path, destination_path)
                files_list.append(file)

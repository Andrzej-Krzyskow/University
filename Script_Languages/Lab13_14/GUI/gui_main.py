import os
import sys
import threading
import tkinter as tk
from _decimal import Decimal
from json import JSONDecodeError
from tkinter import messagebox
import re
import tkinter.ttk as ttk
from kaggle.rest import ApiException
from PIL import Image, ImageTk

from GUI.WidgetFactory import WidgetFactory
from Utils.kaggle_processing import download_kaggle_json, search_kaggle
from Utils.sql_processing import *


def set_files_label(root, status_label, font_conf, background_color, relx, rely):
    widget_factory = WidgetFactory(root, background_color)
    font, size = font_conf
    font_conf = font, size, "bold"
    widget_factory.create_label("Files management", font_conf, relx, rely, "center")


def download_thread(status_label, cfg, database_name, progress_window):
    try:
        download_kaggle_json(cfg, database_name)
        messagebox.showinfo("Download Complete", "Dataset download successful!")
        update_status(status_label, "download completed")
    except ApiException:
        messagebox.showerror("Download Error",
                             "A Kaggle error occurred during dataset download.\nMost probably the given database does "
                             "not exist.")
        update_status(status_label, "download error")
    except NoJsonFileFoundError:
        messagebox.showerror("Download Error", "Dataset does not contain \".json\" file.")
        update_status(status_label, "download error")
    finally:
        progress_window.destroy()


def download_dataset(root, status_label, cfg, database_name):
    font_conf = cfg.app.font_name, cfg.app.font_size
    background_color = cfg.app.background_color
    min_x, min_y = cfg.app.progress_window_min_x, cfg.app.progress_window_min_y
    bar_length = cfg.app.bar_length

    root_factory = WidgetFactory(root, background_color)
    progress_window, bar = root_factory.create_top_level("Downloading", "400x300", min_x, min_y,
                                                         background_color,
                                                         with_progress_bar=True, bar_mode="indeterminate",
                                                         bar_length=bar_length, font_conf=font_conf)

    root.update()
    bar.start(10)

    # start the thread
    kaggle_thread = threading.Thread(target=download_thread, args=(status_label, cfg, database_name, progress_window))
    kaggle_thread.start()


def set_download_section(root, status_label, cfg, font_conf, background_color, relx, rely, button_width, entry_width):
    factory = WidgetFactory(root, background_color)

    factory.create_label("Download database", font_conf, relx, rely, "e")

    entry = factory.create_entry(entry_width, relx, rely, "w", "Database name...", font_conf)

    factory.create_button("Download database", lambda: download_dataset(root, status_label, cfg, entry.get()),
                          button_width, 0.8,
                          rely, "w")


def delete_file(status_label, dropdown_var, dropdown):
    selected_file = dropdown_var.get()

    if selected_file:
        confirmation = messagebox.askyesno("Confirmation", f"Are you sure you want to delete {selected_file}?")

        if confirmation:
            file_path = os.path.join("data", selected_file)

            try:
                os.remove(file_path)
                if os.path.exists(file_path[:-4] + "db"):
                    os.remove(file_path[:-4] + "db")
                messagebox.showinfo("Success", f"{selected_file} has been deleted successfully.")
                refresh_dropdown_filelist(status_label, dropdown_var, dropdown)
                update_status(status_label, "file deleted")
            except OSError:
                messagebox.showerror("Error", f"Failed to delete {selected_file}.")
                update_status(status_label, "no file deleted")

    else:
        messagebox.showwarning("Warning", "No file selected.")
        update_status(status_label, "no file deleted")


def refresh_dropdown_filelist(status_label, dropdown_var, dropdown, to_update=True):
    dropdown['menu'].delete(0, 'end')
    files = os.listdir("data")
    has_json_files = False

    for file in files:
        if file.endswith('.json'):
            has_json_files = True
            dropdown['menu'].add_command(label=file, command=lambda value=file: dropdown_var.set(value))

    if not has_json_files:
        dropdown_var.set("")

    if to_update:
        update_status(status_label, "list refreshed")


def refresh_dropdown_columns(criteria_var, dropdown_var, dropdown):
    criteria_list = criteria_str_to_list(criteria_var.get())

    dropdown['menu'].delete(0, 'end')

    if not criteria_list or criteria_list[0] == "":
        dropdown['menu'].add_command(label="Set criteria first", command=lambda: dropdown_var.set("Set criteria first"))
        return

    for criterion in criteria_list:
        dropdown['menu'].add_command(label=criterion, command=lambda value=criterion: dropdown_var.set(value))


def refresh_dropdown_functions(dropdown_var, dropdown):
    functions = ["sum", "average"]

    dropdown['menu'].delete(0, 'end')
    for function in functions:
        dropdown['menu'].add_command(label=function, command=lambda value=function: dropdown_var.set(value))


def set_delete_section(root, status_label, cfg, font_conf, background_color, relx, rely, button_width, dropdown_width):
    widget_factory = WidgetFactory(root, background_color)

    # label
    widget_factory.create_label("Delete database", font_conf, relx, rely, "e")

    # dropdown list
    dropdown_var = tk.StringVar(root)
    dropdown = widget_factory.create_option_menu(dropdown_var, [""], relx, rely, "w", dropdown_width, font_conf)
    dropdown_var.trace("w",
                       lambda *args: refresh_dropdown_filelist(status_label, dropdown_var, dropdown, to_update=False))
    refresh_dropdown_filelist(status_label, dropdown_var, dropdown)

    # button
    widget_factory.create_button("Delete database", lambda: delete_file(status_label, dropdown_var, dropdown),
                                 button_width, 0.8,
                                 rely, "w")

    # refresh button
    widget_factory.create_button("Refresh list",
                                 lambda: refresh_dropdown_filelist(status_label, dropdown_var, dropdown),
                                 button_width, 0.8,
                                 rely + 0.05, "w")


def show_copyable_message(root, cfg, title, message, search_width, search_height):
    background_color = cfg.app.background_color
    factory = WidgetFactory(root, background_color)
    window = factory.create_top_level(title, None, 640, 320,
                                      background_color)

    # Create a Text widget to display the message
    text = tk.Text(window, width=search_width, height=search_height)

    for item in message:
        text.insert(tk.END, str(item) + "\n")

    text.delete("end-1c")
    text.pack(fill=tk.BOTH, expand=True)


def search_thread(root, cfg, progress_window, status_label, search, search_width, search_height):
    result = search_kaggle(search)
    show_copyable_message(root, cfg, "Search Results", result, search_width, search_height)
    update_status(status_label, "results displayed")
    progress_window.destroy()


def show_search_result(root, cfg, status_label, search, search_width, search_height):
    font_conf = cfg.app.font_name, cfg.app.font_size
    background_color = cfg.app.background_color
    min_x, min_y = cfg.app.progress_window_min_x, cfg.app.progress_window_min_y
    bar_length = cfg.app.bar_length

    root_factory = WidgetFactory(root, background_color)
    progress_window, bar = root_factory.create_top_level("Searching", "400x300", min_x, min_y,
                                                         background_color,
                                                         with_progress_bar=True, bar_mode="indeterminate",
                                                         bar_length=bar_length,
                                                         font_conf=font_conf,
                                                         message="Search in progress")
    root.update()
    bar.start(10)

    show_thread = threading.Thread(target=search_thread,
                                   args=(root, cfg, progress_window, status_label, search, search_width, search_height))
    show_thread.start()


def set_search_section(root, status_label, cfg, font_conf, background_color, relx, rely, button_width, entry_width):
    search_width, search_height = cfg.app.search_width, cfg.app.search_height
    widget_factory = WidgetFactory(root, background_color)

    # label
    widget_factory.create_label("Search database", font_conf, relx, rely, "e")

    # entry
    entry = widget_factory.create_entry(entry_width, relx, rely, "w", "Search for...", font_conf)

    # button
    widget_factory.create_button("Search",
                                 lambda: show_search_result(root, cfg, status_label, entry.get(), search_width,
                                                            search_height),
                                 button_width, 0.8, rely, "w")


def set_data_processing_label(root, status_label, font_conf, background_color, relx, rely):
    font, size = font_conf
    font_conf = font, size, "bold"
    # label
    download_label = tk.Label(root, text="Data processing", font=font_conf, bg=background_color)
    download_label.place(relx=relx, rely=rely, anchor="center")


def show_set_criteria(root, status_label, criteria):
    messagebox.showinfo("Criteria", criteria)
    update_status(status_label, "criteria displayed")


def show_available_criteria(cfg, root, status_label, choose_db_dropdown_var):
    if not choose_db_dropdown_var.get():
        show_msg_box_no_file_or_criteria()
        return

    try:
        available_criteria = get_available_criteria(cfg, choose_db_dropdown_var.get())
    except JsonNotSupportedError:
        show_msg_box_json_not_supported()
        return
    except FileNotFoundError:
        show_msg_box_no_file_or_criteria()
        return

    available_criteria_formatted = ""
    for criterion in available_criteria:
        available_criteria_formatted += "- " + criterion + "\n"
    available_criteria_formatted.strip()

    messagebox.showinfo("Criteria", f"Available criteria:\n{available_criteria_formatted}")
    update_status(status_label, "criteria displayed")


def set_and_save_criteria(root, status_label, entry, criteria_var):
    criteria = entry.get()

    # Check if criteria contains only word characters
    if not re.match(r'^\w+(?:,\s*\w+)*$', criteria):
        messagebox.showwarning("Invalid Criteria",
                               "Please enter a valid comma-separated list of criteria without any special characters.")
        update_status(status_label, "criteria not set")
        return

    criteria_list = [c.strip() for c in criteria.split(",") if c.strip()]
    criteria_var.set(criteria_list)
    show_set_criteria(root, status_label, f"Criteria set:\n{criteria}")
    update_status(status_label, "criteria set")


def set_criteria_section(root, status_label, criteria_var, cfg, font_conf, background_color, relx, rely, button_width,
                         entry_width, choose_db_dropdown_var):
    widget_factory = WidgetFactory(root, background_color)

    # label
    widget_factory.create_label("Set processing criteria", font_conf, relx, rely, "e")

    # entry
    entry = widget_factory.create_entry(entry_width, relx, rely, "w", "id,name,status,vote_average", font_conf)

    # button
    widget_factory.create_button("Set", lambda: set_and_save_criteria(root, status_label, entry, criteria_var),
                                 button_width, 0.8, rely, "w")
    widget_factory.create_button("Show set criteria", lambda: show_set_criteria(root, status_label, criteria_var.get()),
                                 button_width, 0.8, rely + 0.05, "w")
    widget_factory.create_button("Show available criteria",
                                 lambda: show_available_criteria(cfg, root, status_label, choose_db_dropdown_var),
                                 button_width, 0.8, rely + 0.1, "w")


def data_to_widget(cfg, root, headers, data_list):
    top_window = tk.Toplevel(root)
    tree = ttk.Treeview(top_window, columns=headers, show='headings')

    for header in headers:
        tree.heading(header, text=header)

    for data in data_list:
        formatted_data = [f"{float(value):.2f}" if isinstance(value, Decimal) else value for value in data]
        tree.insert('', tk.END, values=formatted_data)

    scrollbar = tk.Scrollbar(top_window, orient=tk.VERTICAL, command=tree.yview)
    scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
    tree.configure(yscrollcommand=scrollbar.set)

    tree.pack(expand=True, fill='both')
    top_window.update()


def image_to_widget(cfg, root, image_path):
    top_window = tk.Toplevel(root)

    image = Image.open(image_path)

    # Create a Tkinter-compatible photo image
    photo = ImageTk.PhotoImage(image)

    image_label = tk.Label(top_window, image=photo)
    image_label.pack()

    top_window.mainloop()


def if_db_up_to_date(cfg, criteria, selected_file, database_up_to_date_var):
    if not database_up_to_date_var.get():
        try:
            prepare_database(cfg, criteria, selected_file)
        except MissingHeadersError:
            raise MissingHeadersError
        except JsonNotSupportedError:
            raise JsonNotSupportedError
        except FileNotFoundError:
            raise FileNotFoundError

    database_up_to_date_var.set("T")


def show_data_thread(cfg, root, status_label, criteria, selected_file, database_up_to_date_var, progress_window):
    try:
        if_db_up_to_date(cfg, criteria, selected_file, database_up_to_date_var)
    except JsonNotSupportedError:
        show_msg_box_json_not_supported()
        progress_window.destroy()
        return
    except MissingHeadersError:
        show_msg_box_missing_headers()
        progress_window.destroy()
        return
    except FileNotFoundError:
        show_msg_box_no_file_or_criteria()
        progress_window.destroy()
        return

    try:
        headers, data_list = get_show_data(cfg, criteria, selected_file)
        update_status(status_label, "Data displayed")
        data_to_widget(cfg, root, headers, data_list)
        progress_window.destroy()
    except JSONDecodeError:
        messagebox.showerror("File corrupted",
                             "The json file has wrong format or is corrupted and could not be decompiled.")
    except (MissingHeadersError, AttributeError):
        show_msg_box_missing_headers()
    except JsonNotSupportedError:
        show_msg_box_json_not_supported()


def show_data(cfg, root, status_label, criteria_var, dropdown_var_db, dropdown_db_name, database_up_to_date_var):
    # get necessary config from cfg
    font_conf = cfg.app.font_name, cfg.app.font_size
    background_color = cfg.app.background_color
    min_x, min_y = cfg.app.progress_window_min_x, cfg.app.progress_window_min_y
    bar_length = cfg.app.bar_length

    selected_file = dropdown_var_db.get()
    criteria = criteria_var.get()

    if selected_file and criteria:
        root_factory = WidgetFactory(root, background_color)
        progress_window, bar = root_factory.create_top_level("Processing data", "400x300", min_x, min_y,
                                                             background_color,
                                                             with_progress_bar=True, bar_mode="indeterminate",
                                                             bar_length=bar_length, font_conf=font_conf,
                                                             message="Processing data...")

        root.update()
        bar.start(10)

        show_data_thr = threading.Thread(target=show_data_thread,
                                         args=(
                                             cfg, root, status_label, criteria, selected_file, database_up_to_date_var,
                                             progress_window))
        show_data_thr.start()
    else:
        show_msg_box_no_file_or_criteria()


def process_data_calculate_thread(cfg, root, status_label, criteria, selected_file, function_name, column_name,
                                  database_up_to_date_var, progress_window):
    try:
        if_db_up_to_date(cfg, criteria, selected_file, database_up_to_date_var)
    except JsonNotSupportedError:
        show_msg_box_json_not_supported()
        progress_window.destroy()
        return
    except MissingHeadersError:
        show_msg_box_missing_headers()
        progress_window.destroy()
        return
    except FileNotFoundError:
        show_msg_box_no_file_or_criteria()
        progress_window.destroy()
        return

    try:
        result = calculate(cfg, selected_file, function_name, column_name)
        result = round(result, 2)
        update_status(status_label, "Data processed")
        data_to_widget(cfg, root, [function_name], [(result,)])
    except (MissingHeadersError, AttributeError):
        show_msg_box_missing_headers()
    finally:
        progress_window.destroy()


def process_data_graph_thread(cfg, root, status_label, criteria, selected_file, function_name, column_name, image_path,
                              database_up_to_date_var, progress_window, graph_created_event):
    try:
        if_db_up_to_date(cfg, criteria, selected_file, database_up_to_date_var)
    except JsonNotSupportedError:
        show_msg_box_json_not_supported()
        progress_window.destroy()
        return
    except MissingHeadersError:
        show_msg_box_missing_headers()
        progress_window.destroy()
        return
    except FileNotFoundError:
        show_msg_box_no_file_or_criteria()
        progress_window.destroy()
        return

    try:
        fig = create_graph(cfg, selected_file, column_name)
        fig.savefig(image_path, bbox_inches="tight")

        update_status(status_label, "Graph displayed")
        graph_created_event.set()
    except (MissingHeadersError, AttributeError):
        show_msg_box_missing_headers()
    finally:
        progress_window.destroy()


def process_data(cfg, root, status_label, criteria_var, dropdown_var_db, dropdown_db_name, dropdown_var_column,
                 dropdown_var_function, database_up_to_date_var, graph=False):
    selected_file = dropdown_var_db.get()
    column_name = dropdown_var_column.get()
    function_name = dropdown_var_function.get()
    criteria = criteria_var.get()
    image_path = cfg.app.image_tmp_path

    # get necessary config from cfg
    font_conf = cfg.app.font_name, cfg.app.font_size
    background_color = cfg.app.background_color
    min_x, min_y = cfg.app.progress_window_min_x, cfg.app.progress_window_min_y
    bar_length = cfg.app.bar_length

    if not column_name or not function_name or column_name == "Set criteria first":
        show_msg_box_column_function_not_set()
        return

    if selected_file:

        try:
            root_factory = WidgetFactory(root, background_color)
            progress_window, bar = root_factory.create_top_level("Processing data", "400x300", min_x, min_y,
                                                                 background_color,
                                                                 with_progress_bar=True, bar_mode="indeterminate",
                                                                 bar_length=bar_length, font_conf=font_conf,
                                                                 message="Processing data...")

            root.update()
            bar.start(10)

            match graph:
                case False:

                    show_data_thr = threading.Thread(target=process_data_calculate_thread,
                                                     args=(
                                                         cfg, root, status_label, criteria, selected_file,
                                                         function_name, column_name,
                                                         database_up_to_date_var, progress_window))
                    show_data_thr.start()
                case True:
                    graph_created_event = threading.Event()
                    show_data_thr = threading.Thread(target=process_data_graph_thread,
                                                     args=(
                                                         cfg, root, status_label, criteria, selected_file,
                                                         function_name, column_name, image_path,
                                                         database_up_to_date_var, progress_window, graph_created_event))
                    show_data_thr.start()
                    root.update()
                    while not graph_created_event.is_set():
                        root.update()

                    image_to_widget(cfg, root, image_path)
                    try:
                        os.remove(image_path)
                    except FileNotFoundError as e:
                        print(e, file=sys.stderr)

        except (MissingHeadersError, AttributeError):
            show_msg_box_missing_headers()
    else:
        show_msg_box_no_file_or_criteria()


def set_processing_database_section(root, status_label, criteria_var, cfg, font_conf, background_color, relx, rely,
                                    button_width,
                                    choose_db_dropdown_var, dropdown_width, database_up_to_date_var):
    # get necessary config from cfg
    spacing = cfg.app.spacing

    widget_factory = WidgetFactory(root, background_color)

    # label
    widget_factory.create_label("Choose database", font_conf, relx, rely, "e")
    widget_factory.create_label("Choose column", font_conf, relx, rely + spacing, "e")
    widget_factory.create_label("Choose function", font_conf, relx, rely + 2 * spacing, "e")

    # dropdown list
    dropdown_var_db = choose_db_dropdown_var
    dropdown_db_name = widget_factory.create_option_menu(dropdown_var_db, [""], relx, rely, "w", dropdown_width,
                                                         font_conf)
    dropdown_var_db.trace("w", lambda *args: refresh_dropdown_filelist(status_label, dropdown_var_db, dropdown_db_name,
                                                                       to_update=False))
    refresh_dropdown_filelist(status_label, dropdown_var_db, dropdown_db_name)

    dropdown_var_column = tk.StringVar(root)
    dropdown_column_name = widget_factory.create_option_menu(dropdown_var_column, [""], relx, rely + spacing, "w",
                                                             dropdown_width,
                                                             font_conf)
    dropdown_var_column.trace("w", lambda *args: refresh_dropdown_columns(criteria_var, dropdown_var_column,
                                                                          dropdown_column_name))
    refresh_dropdown_columns(criteria_var, dropdown_var_column, dropdown_column_name)

    dropdown_var_function = tk.StringVar(root)
    dropdown_function_name = widget_factory.create_option_menu(dropdown_var_function, [""], relx, rely + 2 * spacing,
                                                               "w", dropdown_width,
                                                               font_conf)
    refresh_dropdown_functions(dropdown_var_function, dropdown_function_name)

    # show button
    widget_factory.create_button("Show database",
                                 lambda: show_data(cfg, root, status_label, criteria_var, dropdown_var_db,
                                                   dropdown_db_name,
                                                   database_up_to_date_var),
                                 button_width, 0.8,
                                 rely, "w")
    # process button
    widget_factory.create_button("Process",
                                 lambda: process_data(cfg, root, status_label, criteria_var, dropdown_var_db,
                                                      dropdown_db_name, dropdown_var_column,
                                                      dropdown_var_function, database_up_to_date_var),
                                 button_width,
                                 0.8,
                                 rely + spacing / 2, "w")

    # show graph  button
    widget_factory.create_button("Show Graph",
                                 lambda: process_data(cfg, root, status_label, criteria_var, dropdown_var_db,
                                                      dropdown_db_name, dropdown_var_column, dropdown_var_function,
                                                      database_up_to_date_var, graph=True),
                                 button_width, 0.8,
                                 rely + spacing, "w")

    # refresh button
    widget_factory.create_button("Refresh list",
                                 lambda: (refresh_dropdown_filelist(status_label, dropdown_var_db, dropdown_db_name),
                                          refresh_dropdown_columns(criteria_var, dropdown_var_column,
                                                                   dropdown_column_name)),
                                 button_width, 0.8,
                                 rely + 1.5 * spacing, "w")


def update_status(status_label, status):
    status_label.config(text=f"Status: {status}")


def set_status_label(root, cfg, font_conf, background_color, relx, rely):
    widget_factory = WidgetFactory(root, background_color)

    # label
    status_label = widget_factory.create_label("Status: ready", font_conf, relx, rely, "sw", highlightthickness=2,
                                               highlightbackground="grey", width=25, text_anchor="w")
    return status_label


def criteria_var_changed(database_up_to_date_var):
    database_up_to_date_var.set("")


def set_labels(root, cfg):
    # get settings from cfg
    font_conf = (cfg.app.font_name, cfg.app.font_size)
    font_conf_small = (cfg.app.font_name, cfg.app.font_size_small)
    background_color = cfg.app.background_color
    relx, rely = cfg.app.labels_relx, cfg.app.labels_rely
    header_relx = cfg.app.header_relx
    entry_width, dropdown_width = cfg.app.entry_width, cfg.app.dropdown_width
    spacing = cfg.app.spacing
    button_width = cfg.app.button_width

    # variable to check if criteria were set/changed during runtime
    database_up_to_date_var = tk.StringVar(root)
    criteria_var_changed(database_up_to_date_var)

    # String variable to store the criteria
    criteria_var = tk.StringVar(root)
    criteria_var.trace("w", lambda *args: criteria_var_changed(database_up_to_date_var))

    # set status label
    status_label = set_status_label(root, cfg, font_conf_small, background_color, 0, 1)

    # set sections
    # file management section
    set_files_label(root, status_label, font_conf, background_color, header_relx, rely)

    set_download_section(root, status_label, cfg, font_conf, background_color, relx, rely + spacing, button_width,
                         entry_width)

    set_delete_section(root, status_label, cfg, font_conf, background_color, relx, rely + 2 * spacing, button_width,
                       dropdown_width)

    set_search_section(root, status_label, cfg, font_conf, background_color, relx, rely + 3 * spacing * 1.025,
                       button_width,
                       entry_width)

    # processing section
    set_data_processing_label(root, status_label, font_conf, background_color, header_relx, rely + 4 * spacing * 1.025)

    # dropdown_var for choosing db for processing
    choose_db_dropdown_var = tk.StringVar(root)

    set_criteria_section(root, status_label, criteria_var, cfg, font_conf, background_color, relx,
                         rely + 5 * spacing * 1,
                         button_width, entry_width, choose_db_dropdown_var)

    set_processing_database_section(root, status_label, criteria_var, cfg, font_conf, background_color, relx,
                                    rely + 6 * spacing * 1.1,
                                    button_width, choose_db_dropdown_var,
                                    dropdown_width, database_up_to_date_var)

    update_status(status_label, "ready")

    root.update()


def run_gui(cfg):
    # get necessary config from cfg
    initial_x, initial_y = cfg.app.initial_x, cfg.app.initial_y

    root = tk.Tk()
    root.title(cfg.app.title)
    root_geometry = f"{initial_x}x{initial_y}"
    root.geometry(root_geometry)

    min_x, min_y = cfg.app.min_x, cfg.app.min_y
    root.minsize(min_x, min_y)

    frame = tk.Frame(root, width=min_x, height=min_x, background=cfg.app.background_color, highlightthickness=2,
                     highlightbackground="black")
    frame.place(relx=0.5, rely=0.5, anchor="center")

    set_labels(frame, cfg)

    root.mainloop()


def show_msg_box_missing_headers():
    messagebox.showerror("Headers missing",
                         "The provided headers in criteria were not found in database.")


def show_msg_box_json_not_supported():
    messagebox.showerror("JSON not supported",
                         "The provided JSON has unusual format and it is not supported.")


def show_msg_box_no_file_or_criteria():
    messagebox.showwarning("Warning", "No file selected or criteria not set.")


def show_msg_box_column_function_not_set():
    messagebox.showinfo("No specifications set", "Column or function not set")

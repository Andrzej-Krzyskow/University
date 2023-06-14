import tkinter as tk
from tkinter import ttk


class WidgetFactory:
    def __init__(self, root, background_color):
        self.root = root
        self.background_color = background_color

    def create_label(self, text, font_conf, relx, rely, anchor, highlightthickness=0, highlightbackground="lightgrey",
                     width=0, text_anchor=tk.CENTER):
        label = tk.Label(self.root, text=text, font=font_conf, bg=self.background_color,
                         highlightthickness=highlightthickness, highlightbackground=highlightbackground, width=width,
                         anchor=text_anchor)
        label.place(relx=relx, rely=rely, anchor=anchor)

        return label

    def create_entry(self, width, relx, rely, anchor, default_text, font_conf):
        entry = tk.Entry(self.root, width=width, font=font_conf)
        entry.place(relx=relx, rely=rely, anchor=anchor)
        entry.insert(0, default_text)

        return entry

    def create_button(self, text, command, width, relx, rely, anchor):
        button = tk.Button(self.root, text=text, command=command, width=width, justify="center")
        button.place(relx=relx, rely=rely, anchor=anchor)

        return button

    def create_option_menu(self, variable, options, relx, rely, anchor, width, font_conf):
        option_menu = tk.OptionMenu(self.root, variable, options)
        option_menu.config(width=width)
        option_menu["menu"].config(font=font_conf)
        option_menu.place(relx=relx, rely=rely, anchor=anchor)

        return option_menu

    def create_top_level(self, title, geometry, min_x, min_y, background_color, with_progress_bar=False,
                         bar_length=200, bar_mode=None, font_conf=None, message="Download in progress..."):
        top_window = tk.Toplevel(self.root, background=background_color)
        top_window.title(title)
        top_window.geometry(geometry)
        top_window.minsize(min_x, min_y)

        screen_width = top_window.winfo_screenwidth()
        screen_height = top_window.winfo_screenheight()
        x = (screen_width - min_x) // 2
        y = (screen_height - min_y) // 2
        top_window.geometry(f"+{x}+{y}")

        if with_progress_bar:
            window_factory = WidgetFactory(top_window, background_color)
            bar = window_factory.create_progress_bar(bar_length, mode=bar_mode)
            window_factory.create_label(message, font_conf, 0.5, 0.4, "center")
            return top_window, bar

        return top_window

    def create_progress_bar(self, length, relx=0.5, rely=0.5, anchor=tk.CENTER, mode=None):
        if not mode:
            mode = "determinate"

        bar = ttk.Progressbar(self.root, length=length, mode=mode)
        bar.place(relx=relx, rely=rely, anchor=anchor)
        return bar

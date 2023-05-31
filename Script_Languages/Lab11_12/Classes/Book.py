from dataclasses import dataclass

from bs4 import BeautifulSoup
from matplotlib.figure import Figure


@dataclass
class Book:
    id: str
    content: BeautifulSoup
    title: str
    author: str
    chapters: list
    paragraphs_lengths: list
    first_chapter_fig: Figure

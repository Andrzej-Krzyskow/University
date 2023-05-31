import os
import sys
import re
import urllib.request
from PIL import Image, ImageDraw, ImageFont

import bs4 as bs4
import requests
import matplotlib.pyplot as plt

from Classes.Book import Book
from Exceptions.BookNotFoundError import BookNotFoundError
from Exceptions.InvalidBookFormatError import InvalidBookFormatError


def get_picture_source(content, id):
    images = content.select("img")
    source_url = ""
    if images:
        source = images[0]["src"]
        source_url = f"https://www.gutenberg.org/cache/epub/{id}/{source}"

    return source_url


def download_picture(url, image_path):
    urllib.request.urlretrieve(url, image_path)


def process_image(image_path, picture_config):
    path, left, upper, right, lower, resize, watermark_text = picture_config.values()

    try:
        img = Image.open(image_path)
    except FileNotFoundError as e:
        print("\nBook does not contain image", file=sys.stderr)
        return

    x, y = img.size
    if x < 150 or y < 150:
        print("\nBook image is too small", file=sys.stderr)
        return

    new_image = img

    if x > 1000 and y > 2000:
        new_image = new_image.crop((left, upper, x - right, y - lower))
        x, y = new_image.size

    resize_width = int(x / resize)
    resize_height = int(y / resize)

    if resize_width > 400 and resize_height > 800:
        new_image = new_image.resize((resize_width, resize_height))
        x, y = new_image.size

    watermark = Image.new("RGBA", new_image.size, (0, 0, 0, 0))
    draw = ImageDraw.Draw(watermark)
    font_size = int(y / 20)
    font = ImageFont.truetype("impact.ttf", font_size)
    watermark_x = 25
    watermark_y = y - 350 if y >= 350 else 0
    draw.text((watermark_x, watermark_y), watermark_text, font=font, fill=(0, 183, 255, 150), stroke_width=7,
              stroke_fill=(0, 0, 0, 170))

    watermarked_image = Image.alpha_composite(new_image.convert("RGBA"), watermark)
    os.remove(image_path)
    watermarked_image.save(image_path[:-4] + f".png")
    img.close()


def count_words(chapters, chapter_no=None, round_down=True):
    sums = []

    if chapter_no is not None:
        chapters = [chapters[chapter_no]]

    for i, chapter in enumerate(chapters):
        sums.append([])
        for paragraph in chapter.select("p"):
            paragraph = paragraph.text
            if paragraph:
                paragraph = re.findall("\\b[a-zA-Z-']+\\b", paragraph)
                if round_down:
                    rounded_down = len(paragraph) - len(paragraph) % 10
                    sums[i].append(rounded_down)
                else:
                    sums[i].append(len(paragraph))

        sums[i].sort()
    return sums


def graph_paragraph(paragraph_lengths, chapter_id):
    fig, ax = plt.subplots(figsize=(8, 4))

    ax.set_title(f"Distribution of Paragraph Lengths in {chapter_id + 1}. Chapter")
    ax.set_xlabel("Number of Words")
    ax.set_ylabel("Frequency")

    n, bins, patches = ax.hist(paragraph_lengths, bins=10, edgecolor='black')
    ax.set_xticks(bins)

    plt.close(fig)
    return fig


def scrap_book(id, cfg):
    url = f"https://www.gutenberg.org/cache/epub/{id}/pg{id}-images.html"
    request = requests.get(url)
    content = bs4.BeautifulSoup(request.text, "lxml")

    if not request:
        raise BookNotFoundError

    p_tags_container = content.select("div.container p")
    title = p_tags_container[0].text.split(": ")[1]
    author = p_tags_container[1].text.split(": ")[1]
    class_chapters = content.select(".chapter")
    chapters = []

    for x in class_chapters:
        if len(x.text) > 1500:
            chapters.append(x)

    if not class_chapters or not chapters:
        raise InvalidBookFormatError

    url = get_picture_source(content, id)
    image_path = cfg.picture.path
    download_picture(url, image_path)
    process_image(image_path, cfg.picture)

    if os.path.exists(cfg.picture.path):
        os.remove(cfg.picture.path)

    lengths = count_words(chapters)
    first_chapter_fig = graph_paragraph(lengths[0], 0)

    return Book(id, content, title, author, chapters, lengths, first_chapter_fig)

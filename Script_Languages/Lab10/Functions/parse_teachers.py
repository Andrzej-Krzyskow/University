import bs4
import requests

from Classes.Teacher import Teacher


def __print_teachers__(teachers, letter):
    print(f"\nThe list of teachers - {letter}:\n")
    for x in teachers:
        print(f"{x.fullname:<25} -->\t{x.email}")


def parse_teachers(letter):
    page = 1
    teachers = []

    while True:
        page_url = f"https://wit.pwr.edu.pl/en/faculty/structure/employees/page{page}.html?letter={letter}"
        request = requests.get(page_url)
        content = bs4.BeautifulSoup(request.text, 'html.parser')

        employees = content.select("div .news-box")
        if not employees:
            break

        for x in employees:
            title = x.select("a")[0].get("title")
            email = x.select("p")[0].get_text().split(': ')[1]
            teachers.append(Teacher(title, email))

        page += 1

    __print_teachers__(teachers, letter)

    return teachers

import wikipedia

page_name = input("What would you like to search?\n")

wikipedia.set_lang("en")

try:
    page = wikipedia.page(page_name)

    print("The URL of the page: ", page.url, "\n")
    print("The summary:\n", page.summary)

except:
    print("An error was encountered")
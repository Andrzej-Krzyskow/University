import smtplib
from datetime import datetime
from email.message import EmailMessage


def send_email(credentials, email_data, filename=""):
    server = smtplib.SMTP(host=credentials["server"], port=credentials["port"])
    server.ehlo()
    server.starttls()

    msg, topic, recipient = email_data
    usr, passwd = credentials["username"], credentials["pass"]

    current_datetime = datetime.now()
    formatted_datetime = current_datetime.strftime("%Y-%m-%d %H:%M:%S")
    msg += f"\n{formatted_datetime}"

    message = EmailMessage()
    message["From"] = f"Andy <{usr}>"
    message["To"] = f"Andy2 <{recipient}>"
    message["Subject"] = topic
    message.set_content(msg)

    if filename:
        with open(filename, "rb") as f:
            data = f.read()
            message.add_attachment(data, maintype="application", subtype="vnd.ms-excel",
                                   filename=filename)

    server.login(usr, passwd)
    server.send_message(message)
    server.quit()

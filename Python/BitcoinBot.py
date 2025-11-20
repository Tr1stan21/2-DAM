from bs4 import BeautifulSoup  #del módulo bs4, necesitamos BeautifulSoup
import requests
import schedule

def bot_send_text(bot_message):
    bot_token = '8567555016:AAHRS9uiE1OhQSOg2Ku1e85uVNRK4P0o-Dg'
    bot_chatID = '7354036162'
    send_text = 'https://api.telegram.org/bot' + bot_token + '/sendMessage?chat_id=' + bot_chatID + '&parse_mode=Markdown&text=' + bot_message

    response = requests.get(send_text)

    return response

test_bot = bot_send_text('¡Hola, Telegram!')
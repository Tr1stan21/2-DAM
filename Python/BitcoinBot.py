from bs4 import BeautifulSoup  #del módulo bs4, necesitamos BeautifulSoup
import requests
import schedule

def bot_send_text(bot_message):
    bot_token = '8567555016:AAHRS9uiE1OhQSOg2Ku1e85uVNRK4P0o-Dg'
    bot_chat_id = '7354036162'
    send_text = 'https://api.telegram.org/bot' + bot_token + '/sendMessage?chat_id=' + bot_chat_id + '&parse_mode=Markdown&text=' + bot_message
    response = requests.get(send_text)
    return response

def btc_scraping():
    url = requests.get('https://awebanalysis.com/es/coin-details/bitcoin/')
    soup = BeautifulSoup(url.content, 'html.parser')
    result = soup.find('td', {'class': 'text-larger text-price'})
    format_result = result.text
    print(format_result)
    return format_result

def report():
    btc_price = f'El precio de Bitcoin es de {btc_scraping()}'
    bot_send_text(btc_price)

if __name__ == '__main__':
    #schedule.every().day.at("13:24").do(report)
    #schedule.every().minute.do(report)
    schedule.every(1).hour.do(report)
    while True:
        schedule.run_pending()


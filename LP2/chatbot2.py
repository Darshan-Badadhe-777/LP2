from datetime import date
import time
import webbrowser

responses={
    "hey":"hey How are you ",
    "date":date.today(),
    "time":time.ctime(time.time()),
    "open browser":"http://youtube.com"
}
def chat():
    while True:
        inp = input("User : ")
        inp = inp.lower()
        if inp in responses:
            if inp == 'open browser':
                webbrowser.open_new(responses[inp])
            else:
                 print(responses[inp]) 
        else:
            print("Soory unable to understand")
        if inp == 'bye':
                print("Bye bye ")
                break

chat()
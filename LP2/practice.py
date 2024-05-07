from datetime import date
import time
import webbrowser
import requests
import imdb
import speech_recognition as sr
import pyjokes

# Initialize IMDb client
imdb_client = imdb.IMDb()

responses = {
    "hey": "Hey! How are you?",
    "date": date.today(),
    "time": time.ctime(time.time()),
    "open browser": "http://youtube.com",
    "weather": "Please specify a location for weather information.",
    "personalized recommendations": "Please specify your preferences for personalized recommendations.",
    "movie and tv show recommendations": "Please specify a movie or TV show title.",
    "voice recognition": "Please speak something for voice recognition.",
    "joke": pyjokes.get_joke(),
    "fun fact": "Here's a fun fact: Penguins can jump as high as 6 feet in the air!"
}

def get_weather(city):
    api_key = "YOUR_OPENWEATHERMAP_API_KEY"
    url = f"http://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}&units=metric"
    response = requests.get(url)
    data = response.json()
    if data["cod"] == 200:
        weather_desc = data["weather"][0]["description"]
        temp = data["main"]["temp"]
        return f"The weather in {city} is {weather_desc} with a temperature of {temp}°C."
    else:
        return "Sorry, unable to fetch weather information for that location."

def get_personalized_recommendations(preferences):
    # Implement personalized recommendations based on user preferences
    return "Here are some personalized recommendations based on your preferences."

def get_movie_or_tv_show_recommendations(title):
    results = imdb_client.search_movie(title)
    if results:
        movie = results[0]
        imdb_client.update(movie)
        return f"Title: {movie['title']}, Rating: {movie.get('rating', 'N/A')}, Year: {movie.get('year', 'N/A')}"
    else:
        return "Sorry, couldn't find recommendations for that title."

def voice_recognition():
    recognizer = sr.Recognizer()
    with sr.Microphone() as source:
        print("Speak something...")
        audio = recognizer.listen(source)
    
    try:
        text = recognizer.recognize_google(audio)
        return f"You said: {text}"
    except sr.UnknownValueError:
        return "Sorry, unable to recognize the speech."
    except sr.RequestError:
        return "Sorry, there was an error processing your request."

def chat():
    while True:
        inp = input("User: ")
        inp = inp.lower()
        if inp in responses:
            if inp == 'open browser':
                webbrowser.open_new(responses[inp])
            elif inp == 'weather':
                city = input("Enter city name: ")
                print(get_weather(city))
            elif inp == 'personalized recommendations':
                preferences = input("Enter your preferences: ")
                print(get_personalized_recommendations(preferences))
            elif inp == 'movie and tv show recommendations':
                title = input("Enter movie or TV show title: ")
                print(get_movie_or_tv_show_recommendations(title))
            elif inp == 'voice recognition':
                print(voice_recognition())
            else:
                print(responses[inp])
        elif inp == 'joke':
            print(pyjokes.get_joke())
        elif inp == 'fun fact':
            print("Here's a fun fact: Penguins can jump as high as 6 feet in the air!")
        else:
            print("Sorry, unable to understand.")
        if inp == 'bye':
            print("Goodbye!")
            break

chat()

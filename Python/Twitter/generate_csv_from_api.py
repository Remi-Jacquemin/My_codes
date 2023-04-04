import tweepy
import configparser
import re
import unidecode
import nltk
from nltk.stem import WordNetLemmatizer
from nltk.corpus import stopwords
import pandas as pd


#nltk.download('stopwords')
stop_words = set(stopwords.words('french'))
#nltk.download('wordnet')
lemmatizer = WordNetLemmatizer()


config = configparser.ConfigParser()
config.read('config.ini')

api_key = config['twitter']['api_key']
api_key_secret = config['twitter']['api_key_secret']
access_token = config['twitter']['access_token']
access_token_secret = config['twitter']['access_token_secret']

auth = tweepy.OAuthHandler(api_key, api_key_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)

def format_tweet(text):
    # Lowercase the text
    text = text.lower()
    # Remove hashtags, URLs
    text = re.sub(r'#\S+', '', text)
    text = re.sub(r'https?:\/\/\S+', '', text)
    # Remove punctuation
    text = re.sub(r'[^\w\s]', '', text)
    # Remove formatting (bold, italic....)
    text = unidecode.unidecode(text)
    # Remove new line and carriage
    text = text.replace('\n', ' ').replace('\r', '')
    # Remove extra spaces
    text.strip()
    text = " ".join(text.split())
    # Tokenize the text
    tokens = nltk.word_tokenize(text)
    # # Remove stop words
    tokens = [token for token in tokens if token not in stop_words]
    # # Lemmatize the remaining tokens
    lemmas = [lemmatizer.lemmatize(token) for token in tokens]
    return lemmas

def extract_tweets_from_name(name, nb_tweets):
    tweets = []
    for tweet in tweepy.Cursor(api.user_timeline, screen_name=name, tweet_mode="extended").items():
        if not tweet.full_text.startswith("RT") and not tweet.in_reply_to_status_id:
            full_text = format_tweet(tweet.full_text)
            if full_text :
                tweets.append(full_text)
            if len(tweets) == nb_tweets:
                break
    return tweets

def main(pairs, nb_tweets):
    X_list = []
    Y_list = []
    for name in pairs:
        X_list += extract_tweets_from_name(name, nb_tweets)
        Y_list += [pairs[name] for i in range(nb_tweets)]
    return X_list, Y_list

pairs = {
    "BarackObama" : 0,
    "JoeBiden" : 0, 
    "realDonaldTrump" : 0,
    "Snowden" : 0,
    "MrBeast" : 1,
    "DudePerfect" : 1,
    "SeaNanners" : 1,
    "adinross" : 1
}
        
data = dict(zip(['person', 'rate'], main(pairs, 150)))

df = pd.DataFrame(data)

df.to_csv(r'C:\Users\Remi\Desktop\Projects\Twitter\political_tweets.csv', index=False, header=True)






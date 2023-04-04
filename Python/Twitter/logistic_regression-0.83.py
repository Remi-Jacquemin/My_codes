import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
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

data = pd.read_csv('political_tweets.csv')

# Split the dataset into features and labels
X = data['person']
y = data['rate']


# Extract features using a count vectorizer
vectorizer = CountVectorizer()
X = vectorizer.fit_transform(X)

# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

# Train a logistic regression model
model = LogisticRegression()
model.fit(X_train, y_train)

# Evaluate the model on the test set
accuracy = model.score(X_test, y_test)
print(f'Test accuracy: {accuracy:.2f}')

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

tweet1 = "I will soon have a million subscribers, thanks a lot !"
tweet2 = "I am affraind that the next elections will be for the democrats"
tweet3 = "I'm thinkink of the number of views on my last video"
tweet4 = "The very next law that I will ask for will be beneficial for everyone"
tweets = [format_tweet(tweet1), format_tweet(tweet2), format_tweet(tweet3), format_tweet(tweet4)]

tweets = [' '.join(tweet) for tweet in tweets]

# Extract features from the tweets
features = vectorizer.transform(tweets)


labels = model.predict(features)

for i, tweet in enumerate(tweets):
    print(f'Tweet: {tweet}')
    print(f'Predicted label: {labels[i]}')
    print()
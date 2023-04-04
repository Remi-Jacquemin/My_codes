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
from sklearn.linear_model import Ridge
from sklearn.metrics import mean_absolute_error
import matplotlib.pyplot as plt

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

regressor = Ridge()
regressor.fit(X_train, y_train)

# Make predictions for the test set
predictions = regressor.predict(X_test)

# Compute the mean absolute error between the predicted and the true labels
mae = mean_absolute_error(y_test, predictions)
print(mae)


# Plot the predicted values against the true values
plt.scatter(y_test, predictions)

# Add a line with slope 1 and intercept 0, to represent perfect prediction
plt.plot([0, max(y_test)], [0, max(y_test)], '--k')

# Add labels and show the plot
plt.xlabel('True values')
plt.ylabel('Predicted values')
plt.show()
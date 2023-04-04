import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.linear_model import LogisticRegression

# Load the dataset
data = pd.read_csv('political_tweets.csv')

# Split the dataset into features and labels
X = data['person']
y = data['rate']

# Convert the tokenized texts into a matrix of token counts
vectorizer = CountVectorizer()
X = vectorizer.fit_transform(X)
print(X)
print(type(X))
import nltk
from nltk.corpus import stopwords

file = open("sample.txt","r")
text = file.read()
file.close()

tokens = nltk.word_tokenize(text)

stop_words = set(stopwords.words('english'))
filered_tokens = []

for w in tokens:
    if w.lower() not in stop_words:
        filered_tokens.append(w)
        
filtered_text = " ".join(filered_tokens)

print(filtered_text)

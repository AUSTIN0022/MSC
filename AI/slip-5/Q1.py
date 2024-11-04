import nltk
from nltk.stem import WordNetLemmatizer

# Ensure necessary resources are available
nltk.download('punkt_tab')
nltk.download('punkt')
nltk.download('wordnet')

text = "studies studying studied"

# Tokenize the text
tokenized_text = nltk.word_tokenize(text)
print("Tokenized text:", tokenized_text)

# Initialize the lemmatizer
lemmatizer = WordNetLemmatizer()

# Lemmatize tokens
print("Lemmatized tokens:")
for token in tokenized_text:
    print(lemmatizer.lemmatize(token)) 
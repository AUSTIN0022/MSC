import string

str = input("Enter string: ")

no_punc = ""

for char in str:
    if char not in string.punctuation:
        no_punc += char
        
print(no_punc)

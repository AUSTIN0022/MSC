import random

word_list = ["python", "hangman", "challenge", "programming", "random", "game"]

# Function to display the current state of the word
def display_word(word, guessed_letters):
    display = ""
    for letter in word:
        if letter in guessed_letters:
            display += letter
        else:
            display += "_ "
    return display

def hangman():
    word = random.choice(word_list)
    guessed_letters = set()
    wrong_attempts = 0
    max_attempts = 6

    print("Welcome to Hangman!")
    print("Guess the word by entering one letter at a time.")
    print("You have", max_attempts, "wrong attempts allowed.")

    while wrong_attempts < max_attempts:
        current_state = display_word(word, guessed_letters)
        print("\nWord:", current_state)
        
        # Check if player has won
        if "_" not in current_state:
            print("GG! You've guessed the right word:", word)
            return
        
        guess = input("Enter a letter: ").lower()
        
        # Validate input
        if len(guess) != 1 or not guess.isalpha():
            print("Please enter a single alphabet.")
            continue

        # Check if letter was already guessed
        if guess in guessed_letters:
            print("You already guessed that letter!")
            continue

        # Process guess
        guessed_letters.add(guess)
        if guess in word:
            print("Good guess!")
        else:
            wrong_attempts += 1
            print("Wrong guess. Attempts left:", max_attempts - wrong_attempts)

    print("\nGame Over! The word was:", word)

hangman()

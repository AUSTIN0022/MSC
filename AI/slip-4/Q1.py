
import random


def choose_word():
    words = ["python", "hangman", "programming", "computer", "science", "challenge"]
    return random.choice(words)

def display_word(word, guessed_letters):
    displayed_word = ""
    for letter in word:
        if letter in guessed_letters:
            displayed_word += letter
        else:
            displayed_word += "_"
    return displayed_word

def hangman():
    print("Welcome to Hangman!")
    
    # Choose a random word
    word_to_guess = choose_word()
    
    # Initialize variables
    guessed_letters = []
    attempts_left = 6  # You can adjust the number of allowed attempts
    
    while attempts_left > 0:
        print("\nAttempts left:", attempts_left)
        current_display = display_word(word_to_guess, guessed_letters)
        print("Current word:", current_display)
        
        guess = input("Enter a letter: ").lower()
        
        # Check if the guess is a single alphabet
        if not guess.isalpha() or len(guess) != 1:
            print("Please enter a valid single letter.")
            continue
        
        # Check if the letter has already been guessed
        if guess in guessed_letters:
            print("You already guessed that letter. Try again.")
            continue
        
        guessed_letters.append(guess)
        
        # Check if the guess is correct
        if guess not in word_to_guess:
            attempts_left -= 1
            print("Incorrect guess! Try again.")
        
        # Check if the player has guessed all letters
        if "_" not in display_word(word_to_guess, guessed_letters):
            print("\nCongratulations! You guessed the word:", word_to_guess)
            break
    
    if attempts_left == 0:
        print("\nSorry, you ran out of attempts. The word was:", word_to_guess)

if __name__ == "__main__":
    hangman()

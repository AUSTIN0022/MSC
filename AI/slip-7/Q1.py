def print_board(board):
    for i in range(3):
        print(" | ".join(board[i*3:(i+1)*3]))
        if i < 2:
            print("-------")

def check_winner(board, player):
    for i in range(3):
        if all(board[i*3+j] == player for j in range(3)) or \
           all(board[i+j*3] == player for j in range(3)):
            return True
    if all(board[i] == player for i in [0, 4, 8]) or \
       all(board[i] == player for i in [2, 4, 6]):
        return True
    return False

def is_full(board):
    return all(cell != " " for row in board for cell in row)

def play_game():
    board = [" " for _ in range(9)]
    current_player = "X"

    while True:
        print_board(board)
        
        move = int(input(f"Player {current_player}'s turn. Enter move (1-9): ")) - 1
        
        if move < 0 or move > 8:
            print("Invalid input. Enter a number between 1 and 9.")
            continue
        
        if board[move] != " ":
            print("That cell is already occupied. Try again.")
            continue
        
        board[move] = current_player
        
        if check_winner(board, current_player):
            print_board(board)
            print(f"Player {current_player} wins!")
            break
        
        if is_full(board):
            print_board(board)
            print("It's a tie!")
            break
        
        current_player = "O" if current_player == "X" else "X"

if __name__ == "__main__":
    play_game()

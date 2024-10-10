N = 8

def print_board(board):
    for i in range(N):
        for j in range(N):
            print(board[i][j], end=" ")
        print()

def is_safe(board, row, col):
    # Check the current row on the left side
    for i in range(col):
        if board[row][i] == 1:
            return False
    
    # Check upper diagonal on the left side
    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
        if board[i][j] == 1:
            return False

    # Check lower diagonal on the left side
    for i, j in zip(range(row, N), range(col, -1, -1)):
        if board[i][j] == 1:
            return False

    return True

def solveNqueen(board, col):
    if col >= N:
        return True

    for i in range(N):
        if is_safe(board, i, col):
            board[i][col] = 1  # Place queen
            if solveNqueen(board, col + 1):  # Recur to place rest
                return True
            board[i][col] = 0  # Backtrack if placing queen doesn't work

    return False

board = [[0] * N for _ in range(N)]

if not solveNqueen(board, 0):
    print("Solution does not exist")
else:
    print_board(board)

import heapq

GOAL_STATE = [[1, 2, 3], [4, 5, 6], [7, 8, 0]]

# Helper function to find the position of a specific tile in a state
def find_position(state, value):
    for i in range(3):
        for j in range(3):
            if state[i][j] == value:
                return i, j
    return -1, -1

# Calculate Manhattan distance as the heuristic for A* algorithm
def manhattan_distance(state):
    distance = 0
    for i in range(3):
        for j in range(3):
            if state[i][j] != 0:
                goal_i, goal_j = find_position(GOAL_STATE, state[i][j])
                distance += abs(i - goal_i) + abs(j - goal_j)
    return distance

# Find possible moves for the blank tile
def get_neighbors(state):
    neighbors = []
    i, j = find_position(state, 0)
    possible_moves = [
        (i - 1, j), # Up
        (i + 1, j), # Down
        (i, j - 1), # Left
        (i, j + 1)  # Right
    ]
    for new_i, new_j in possible_moves:
        if 0 <= new_i < 3 and 0 <= new_j < 3:
            # Swap blank with neighbor tile
            new_state = [row[:] for row in state]
            new_state[i][j], new_state[new_i][new_j] = new_state[new_i][new_j], new_state[i][j]
            neighbors.append(new_state)
    return neighbors

# Solve 8-puzzle using A* algorithm
def a_star_8_puzzle(initial_state):
    # Priority queue for A* search
    open_list = []
    heapq.heappush(open_list, (manhattan_distance(initial_state), 0, initial_state, []))
    visited = set()
    visited.add(tuple(map(tuple, initial_state)))
    
    while open_list:
        estimated_cost, moves, current_state, path = heapq.heappop(open_list)
        
        # Check if goal state is reached
        if current_state == GOAL_STATE:
            return path + [current_state]
        
        # Generate neighbors and add them to the open list if they haven't been visited
        for neighbor in get_neighbors(current_state):
            neighbor_tuple = tuple(map(tuple, neighbor))
            if neighbor_tuple not in visited:
                visited.add(neighbor_tuple)
                new_path = path + [current_state]
                heapq.heappush(open_list, (moves + 1 + manhattan_distance(neighbor), moves + 1, neighbor, new_path))
    
    return None  # If no solution found

def print_solution(path):
    if path is None:
        print("No solution found.")
        return
    for step, state in enumerate(path):
        print(f"Step {step}:")
        for row in state:
            print(row)
        print()

# Define the initial state
initial_state = [
    [1, 2, 3],
    [4, 0, 6],
    [7, 5, 8]
]

# Solve the puzzle
solution_path = a_star_8_puzzle(initial_state)

print("Solution path:")
print_solution(solution_path)

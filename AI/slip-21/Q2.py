from itertools import permutations


def solve_cryptarithmetic(equation):
    unique_letters = set("".join(equation))
    
    if len(unique_letters) > 10:
        return "Invalid equation. More than 10 unique letters."
    
    # Convert the list of unique letters to a sorted list for consistent ordering
    unique_letters = sorted(unique_letters)
    
    for perm in permutations(range(10), len(unique_letters)):
        mapping = dict(zip(unique_letters, perm))
        
        # Skip leading zero cases
        if any(mapping[equation[i][0]] == 0 for i in range(len(equation))):
            continue
        
        # Calculate the values of each word based on the current mapping
        left_sum = sum(mapping[letter] * 10**i for i, letter in enumerate(equation[0][::-1]))
        right_sum = sum(mapping[letter] * 10**i for i, letter in enumerate(equation[1][::-1]))
        result_sum = sum(mapping[letter] * 10**i for i, letter in enumerate(equation[2][::-1]))
        
        # Check if the equation holds
        if left_sum + right_sum == result_sum:
            return {letter: mapping[letter] for letter in unique_letters}
    
    return "No solution found"

if __name__ == "__main__":
    equation = ["GO", "TO", "OUT"]
    solution = solve_cryptarithmetic(equation)
    
    if isinstance(solution, dict):
        print("Solution found:")
        for letter, value in solution.items():
            print(f"{letter}: {value}")
    else:
        print(solution)

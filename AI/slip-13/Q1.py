import math


def minmax(depth, nodeIndex, maxPlayer, values, target):
    if depth == target:
        return values[nodeIndex]
    
    if maxPlayer:
        best = -math.inf
        for i in range(2):
            val = minmax(depth + 1, nodeIndex * 2 + i, False, values, target)
            best = max(best, val)
        return best
    else:
        best = math.inf
        for i in range(2):
            val = minmax(depth + 1, nodeIndex * 2 + i, True, values, target)
            best = min(best, val)
        return best

# Test the algorithm
values = [-1, 4, 2, 6, -3, -5, 0, 7]
print(f"The optimal value is: {minmax(0, 0, True, values, 3)}")

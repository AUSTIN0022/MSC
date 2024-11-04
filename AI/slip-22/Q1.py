import math


def alphaBeta(depth, nodeIndex,alpha, beta, maxPlayer, values, target):
    if depth == target:
        return values[nodeIndex]
    
    if maxPlayer:
        best = -math.inf
        for i in range(2):
            val = alphaBeta(depth + 1, nodeIndex * 2 + i,alpha, beta, False, values, target)
            best = max(best, val)
            alpha = max(alpha, best)
            if beta <= alpha:
                break
        return best
    else:
        best = math.inf
        for i in range(2):
            val = alphaBeta(depth + 1, nodeIndex * 2 + i,alpha, beta, True, values, target)
            best = min(best, val)
            beta = min(beta, best)
            if beta <= alpha:
                break
        return best

# Test the algorithm
values = [-1, 4, 2, 6, -3, -5, 0, 7]
print(f"The optimal value is: {alphaBeta(0, 0, -math.inf, math.inf, True, values, 3)}")

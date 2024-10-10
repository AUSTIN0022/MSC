def dfs(graph, start, visited,target):
    if start not in visited:
        print(start, end=" ")
        visited.add(start)
        if start == target:
            print(f"Target found {target}")
            return True
        
        for neighbor in graph[start]:
            if dfs(graph, neighbor, visited,target):
                return True
    
    return False
# Example usage
graph = {
    '1': ['2', '3'],
    '2': ['4'],
    '3': ['2'],
    '4': ['5','6'],
    '5': ['3','7'],
    '6': [],
    '7': ['6'],
}
visited = set()
start = '1'
target = '7'

print("Depth-First Search Traversal:")
dfs(graph, start,visited, target)

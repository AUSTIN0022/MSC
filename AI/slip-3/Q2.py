def dfs(graph, start, target, visited=None):
    if visited is None:
        visited = set()
    
    visited.add(start)
    print(start, end=" ")

    if start == target:
        print(f"\nTarget node '{target}' found.")
        return

    for neighbor in graph[start]:
        if neighbor not in visited:
            dfs(graph, neighbor, target, visited)

    # If we exit the loop without finding the target, check if it was not found
    if start == target:
        print(f"\nTarget node '{target}' found.")
    elif all(neighbor in visited for neighbor in graph[start]):
        print(f"\nTarget node '{target}' not found.")

graph = {
    '1': ['2', '3','4'],
    '2': ['1','4','5'],
    '3': ['1','4'],
    '4': ['1', '2','3','7'],
    '5': ['2', '6','7'],
    '6': ['5','7'],
    '7': ['4','5','6'],
}

start_node = '2'
target_node = '7'

print("Depth-First Search Traversal:")
dfs(graph, start_node, target_node)

def bfs(graph, start, target):
    visited = set()
    queue = [start]
    visited.add(start)

    while queue:
        node = queue.pop(0)
        print(node, end=" ")

        if node == target:
            print(f"\nTarget node '{target}' found.")
            return

        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)

    print(f"\nTarget node '{target}' not found.")

graph = {
    '1': ['2', '4'],
    '2': ['3'],
    '3': ['4', '5','6'],
    '4': ['2'],
    '5': ['7','8'],
    '6': ['8'],
    '7': ['8'],
    '8':[]
}

start = '1'
target = '8'

print("Breadth-First Search Traversal:")
bfs(graph, start, target)

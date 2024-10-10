def tower_of_hanoi(n, source, target, auxiliary):
    if n == 1:
        print(f"Move disk 1 from {source} to {target}")
        return
    tower_of_hanoi(n - 1, source, auxiliary, target)
    print(f"Move disk {n} from {source} to {target}")
    tower_of_hanoi(n - 1, auxiliary, target, source)

if __name__ == "__main__":
    # Define the number of disks and the tower names
    num_disks = int(input("Enter the number of disks: "))
    source_tower = "Source"
    target_tower = "Target"
    auxiliary_tower = "Auxiliary"

    # Run Tower of Hanoi algorithm
    tower_of_hanoi(num_disks, source_tower, target_tower, auxiliary_tower)

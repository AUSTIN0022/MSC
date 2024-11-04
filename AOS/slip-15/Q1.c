#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <sys/stat.h>

int main() {
    DIR *dir = opendir(".");
    if (dir == NULL) {
        printf("\nCould not open the directory!\n");
        return 1;
    }

    long n;
    printf("Enter the size in bytes (n): ");
    scanf("%ld", &n);

    struct dirent *entry;
    struct stat fileStat;

    printf("\nFiles in the directory with size greater than %ld bytes:\n", n);
    while ((entry = readdir(dir)) != NULL) {
        if (entry->d_type == DT_REG) {
            // Get file stats
            if (stat(entry->d_name, &fileStat) == 0) {
                if (fileStat.st_size > n) {
                    printf(" %s (%ld bytes)\n", entry->d_name, fileStat.st_size);
                }
            } else {
                perror("Error getting file stats");
            }
        }
    }

    closedir(dir);
    return 0;
}

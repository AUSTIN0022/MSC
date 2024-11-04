#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <string.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <file1> <file2> ...\n", argv[0]);
        return 1;
    }

    DIR *dir = opendir(".");
    if (dir == NULL) {
        printf("\nCould not open the directory!\n");
        return 1;
    }

    struct dirent *entry;
    int found;

    // Iterate over all command-line arguments (file names)
    for (int i = 1; i < argc; i++) {
        found = 0;  // Reset found flag for each file
        rewinddir(dir);  // Reset directory stream to the beginning

        while ((entry = readdir(dir)) != NULL) {
            if (entry->d_type == DT_REG && strcmp(entry->d_name, argv[i]) == 0) {
                printf("File %s is present in the directory.\n", argv[i]);
                found = 1;
                break;
            }
        }

        if (!found) {
            printf("File %s is not present in the directory.\n", argv[i]);
        }
    }

    closedir(dir);
    return 0;
}

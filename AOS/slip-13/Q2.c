#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <prefix>\n", argv[0]);
        exit(1);
    }

    char *prefix = argv[1];
    struct dirent *entry;

    DIR *dir = opendir(".");
    if (dir == NULL) {
        perror("opendir");
        exit(1);
    }

    printf("Files starting with '%s':\n", prefix);
    while ((entry = readdir(dir)) != NULL) {
        if (strncmp(entry->d_name, prefix, strlen(prefix)) == 0) {
            printf("%s\n", entry->d_name);
        }
    }

    closedir(dir);
    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <time.h>
#include <dirent.h>
#include <string.h>

int file_month(const char *filename, const char *target_month) {
    struct stat file_stat;

    // Get file statistics
    if (stat(filename, &file_stat) != 0) {
        perror("Error");
        return 1;
    }

    struct tm *timeinfo;
    timeinfo = localtime(&file_stat.st_ctime);

    // Get the month in which the file was created
    char month[20];
    strftime(month, sizeof(month), "%B", timeinfo);

    // Compare with the target month
    if (strcasecmp(month, target_month) == 0) {
        printf("The file '%s' was created in the month of %s.\n", filename, month);
    }

    return 0;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <month>\n", argv[0]);
        return 1;
    }

    const char *target_month = argv[1];
    DIR *dir;
    struct dirent *entry;

    // Open the current directory
    if ((dir = opendir(".")) == NULL) {
        perror("Error opening directory");
        return 1;
    }

    // Iterate through the files in the directory
    while ((entry = readdir(dir)) != NULL) {
        // Skip directories
        if (entry->d_type == DT_REG) {
            file_month(entry->d_name, target_month);
        }
    }

    closedir(dir);
    return 0;
}

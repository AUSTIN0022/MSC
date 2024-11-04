#include <stdio.h>
#include <stdlib.h>
#include <sys/resource.h>

int main() {
    struct rlimit limit;

    // Get the current limit for maximum number of open files
    if (getrlimit(RLIMIT_NOFILE, &limit) == 0) {
        printf("Current limit on open files: soft=%ld, hard=%ld\n", limit.rlim_cur, limit.rlim_max);
    } else {
        perror("getrlimit");
        return 1;
    }

    // Set a new limit for maximum number of open files
    limit.rlim_cur = 1024;
    limit.rlim_max = 2048;
    if (setrlimit(RLIMIT_NOFILE, &limit) == 0) {
        printf("New limit on open files set: soft=%ld, hard=%ld\n", limit.rlim_cur, limit.rlim_max);
    } else {
        perror("setrlimit");
        return 1;
    }

    // Get the current limit for maximum memory usage
    if (getrlimit(RLIMIT_AS, &limit) == 0) {
        printf("Current limit on memory: soft=%ld bytes, hard=%ld bytes\n", limit.rlim_cur, limit.rlim_max);
    } else {
        perror("getrlimit");
        return 1;
    }

    return 0;
}
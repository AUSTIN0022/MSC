#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/times.h>
#include <time.h>

#define CLK_TCK sysconf(_SC_CLK_TCK)

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <number_of_children>\n", argv[0]);
        exit(1);
    }

    int n = atoi(argv[1]);
    if (n <= 0) {
        printf("Number of children must be positive\n");
        exit(1);
    }

    pid_t pid;
    struct tms start_time, end_time;
    clock_t start, end;

    start = times(&start_time);

    for (int i = 0; i < n; i++) {
        pid = fork();

        if (pid == -1) {
            perror("fork");
            exit(1);
        }

        if (pid == 0) {
            printf("Child process %d (PID %d) is running...\n", i + 1, getpid());
            for (long j = 0; j < 100000000; j++) {
                // Do nothing, just consume CPU time
            }
            exit(0);
        }
    }

    // Parent process waits for all children to finish
    for (int i = 0; i < n; i++) {
        wait(NULL);
    }

    end = times(&end_time);

    double user_time = (end_time.tms_cutime - start_time.tms_cutime) / (double) CLK_TCK;
    double system_time = (end_time.tms_cstime - start_time.tms_cstime) / (double) CLK_TCK;

    printf("Total cumulative time spent by %d children:\n", n);
    printf("User mode: %.2f seconds\n", user_time);
    printf("Kernel mode: %.2f seconds\n", system_time);

    return 0;
}
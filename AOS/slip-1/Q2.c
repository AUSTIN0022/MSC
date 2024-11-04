#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

void alarm_handler(int signum) {
    printf("Alarm fired! Signal %d received by parent process.\n", signum);
}

int main() {
    pid_t pid;
    // Set up signal handler for SIGALRM in the parent process
    signal(SIGALRM, alarm_handler);

    // Create a child process
    pid = fork();

    if (pid == 0) {
        // Child process
        printf("Child process (PID: %d) sleeping for 2 seconds before sending SIGALRM...\n", getpid());
        sleep(2);
        // Send SIGALRM to parent process
        kill(getppid(), SIGALRM);

        printf("Child process sent SIGALRM to parent.\n");
        exit(0);
    } else {
        // Parent process
        printf("Parent process (PID: %d) waiting for SIGALRM...\n", getpid());
        wait(NULL); // Wait for the child to finish
        sleep(1); // Sleep for a short time to allow the signal handler to execute
        printf("Parent process exiting.\n");
    }
    return 0;
}
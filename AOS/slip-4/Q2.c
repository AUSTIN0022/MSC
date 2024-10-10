#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

// Signal handler for the child process
void handle_signal(int signum) {
    switch (signum) {
        case SIGHUP:
            printf("Child process received SIGHUP signal.\n");
            break;
        case SIGINT:
            printf("Child process received SIGINT signal.\n");
            break;
        case SIGQUIT:
            printf("My Papa has Killed me!!!\n");
            exit(0);
            break;
    }
}

int main() {
    pid_t pid = fork();

    if (pid == -1) {
        perror("fork");
        exit(1);
    }

    if (pid == 0) {  // Child process
        // Set up the signal handlers
        signal(SIGHUP, handle_signal);
        signal(SIGINT, handle_signal);
        signal(SIGQUIT, handle_signal);

        // Infinite loop to keep the child process running
        while (1) {
            pause();  // Wait for signals
        }
    } else {  // Parent process
        // Parent sends signals to the child process every 3 seconds
        for (int i = 0; i < 5; i++) {
            sleep(3);
            if (i < 4) {
                kill(pid, (i % 2 == 0) ? SIGHUP : SIGINT);  // Send SIGHUP or SIGINT
            } else {
                kill(pid, SIGQUIT);  // Send SIGQUIT after 15 seconds
            }
        }

        // Wait for the child process to terminate
        wait(NULL);
        printf("Parent process is terminating.\n");
    }

    return 0;
}

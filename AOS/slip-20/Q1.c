#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

void handle_signal(int sig) {
    if (sig == SIGUSR1) {
        printf("Received signal to stop process\n");
    } else if (sig == SIGUSR2) {
        printf("Received signal to continue process\n");
    }
}

int main() {
    pid_t pid;

    // Set up signal handlers for the child process
    signal(SIGUSR1, handle_signal);
    signal(SIGUSR2, handle_signal);

    pid = fork();

    if (pid < 0) {
        perror("fork failed");
        exit(1);
    }

    if (pid == 0) {  // Child process
        while (1) {
            printf("Child process is running...\n");
            sleep(1);
        }
    } else {  // Parent process
        printf("Parent process created child with PID %d\n", pid);
        
        sleep(5);  // Let the child process run for 5 seconds

        printf("Suspending the child process...\n");
        kill(pid, SIGSTOP);  // Suspend the child process
        sleep(5);  // Wait for 5 seconds

        printf("Resuming the child process...\n");
        kill(pid, SIGCONT);  // Resume the child process
        sleep(5);  // Let the child process run for 5 more seconds

        printf("Terminating the child process...\n");
        kill(pid, SIGKILL);  // Terminate the child process

        wait(NULL);  // Wait for the child process to terminate
        printf("Parent process has terminated the child process.\n");
    }

    return 0;
}
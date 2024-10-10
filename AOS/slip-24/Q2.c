#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

pid_t child_pid; // Global variable to store child process ID

// Signal handler for SIGCHLD (child process termination)
void chld_handler(int signum) {
    int status;
    pid_t pid = waitpid(child_pid, &status, WNOHANG); // Non-blocking wait

    if (pid > 0) {
        if (WIFEXITED(status)) {
            printf("Child process (PID: %d) exited with status %d.\n", pid, WEXITSTATUS(status));
        } else if (WIFSIGNALED(status)) {
            printf("Child process (PID: %d) killed by signal %d.\n", pid, WTERMSIG(status));
        }
        exit(0);
    }
}

// Signal handler for SIGALRM (alarm signal)
void alrm_handler(int signum) {
    printf("Child process did not complete within 5 seconds. Killing child process (PID: %d).\n", child_pid);
    kill(child_pid, SIGKILL); // Kill the child process
}

int main() {
    // Set up signal handler for SIGCHLD
    if (signal(SIGCHLD, chld_handler) == SIG_ERR) {
        perror("signal(SIGCHLD)");
        exit(1);
    }

    // Set up signal handler for SIGALRM
    if (signal(SIGALRM, alrm_handler) == SIG_ERR) {
        perror("signal(SIGALRM)");
        exit(1);
    }

    // Create a child process
    child_pid = fork();

    if (child_pid == -1) {
        perror("fork");
        exit(1);
    }

    if (child_pid == 0) { // Child process
        // Replace this with any command or program you want to execute
        printf("Child process (PID: %d) running...\n", getpid());
        execlp("sleep", "sleep", "10", NULL); // Example command (sleep for 10 seconds)
        perror("execlp"); // If execlp fails
        exit(1);
    } else { // Parent process
        alarm(5); // Set an alarm for 5 seconds

        // Parent process waits for child process to terminate
        while (1) {
            pause(); // Wait for any signal
        }
    }
    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid;
    int status;

    pid = fork();

    if (pid < 0) { 
        perror("fork");
        exit(1);
    }

    if (pid == 0) {  // Child process
        printf("Child process (PID: %d) is running...\n", getpid());
        
        // Simulate some work
        sleep(3);
        
        // Exit with a status code (for ex, 42)
        exit(42);
    } else {  // Parent process
        printf("Parent process is waiting for the child to terminate...\n");

        wait(&status);

        if (WIFEXITED(status)) {
            // If the child exited normally, print its exit status
            printf("Child process terminated with exit status: %d\n", WEXITSTATUS(status));
        } else if (WIFSIGNALED(status)) {
            // If the child was terminated by a signal, print the signal number
            printf("Child process terminated by signal: %d\n", WTERMSIG(status));
        } else {
            printf("Child process did not terminate normally\n");
        }
    }
    return 0;
}

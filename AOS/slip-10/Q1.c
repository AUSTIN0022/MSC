#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int pipefd[2];
    pid_t pid1, pid2;

    // Create the pipe
    if (pipe(pipefd) == -1) {
        printf("pipe");
        exit(1);
    }

    // Create first child process
    pid1 = fork();

    if (pid1 == -1) {
        printf("fork");
        exit(1);
    }

    if (pid1 == 0) {  // First child process
        close(pipefd[0]); // Close unused read end

        dup2(pipefd[1], STDOUT_FILENO);
        close(pipefd[1]);

        // Execute the "ls" command
        execlp("ls", "ls", "-l", NULL);
        printf("execlp"); // If execlp fails
        exit(1);
    }

    // Create second child process
    pid2 = fork();
    if (pid2 == -1) {
        printf("fork");
        exit(1);
    }

    if (pid2 == 0) {  // Second child proces
        close(pipefd[1]); // Close unused write end

        // Redirect stdin to the read end of the pipe
        dup2(pipefd[0], STDIN_FILENO);
        close(pipefd[0]);

        execlp("wc", "wc", "-l", NULL); // Execute the "wc" command
        printf("execlp");// If execlp fails
        exit(1);
    }
    close(pipefd[0]);
    close(pipefd[1]);

    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);

    return 0;
}
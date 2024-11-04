#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

int main() {
    int pipefd[2];
    pid_t pid1, pid2;
    sigset_t new_set, old_set;

    // Block SIGINT (Ctrl-C) and SIGQUIT (Ctrl-\)
    sigemptyset(&new_set);
    sigaddset(&new_set, SIGINT);
    sigaddset(&new_set, SIGQUIT);
    sigprocmask(SIG_BLOCK, &new_set, &old_set);

    // Create the pipe
    if (pipe(pipefd) == -1) {
        perror("pipe");
        exit(1);
    }

    // Create first child process
    pid1 = fork();
    if (pid1 == -1) {
        perror("fork");
        exit(1);
    }

    if (pid1 == 0) {  // First child process
        close(pipefd[0]); // Close unused read end

        dup2(pipefd[1], STDOUT_FILENO);
        close(pipefd[1]);

        sleep(3);

        execlp("ls", "ls", "-l", NULL); // Execute the "ls" command
        perror("execlp"); // If execlp fails
        exit(1);
    }

    // Create second child process
    pid2 = fork();
    if (pid2 == -1) {
        perror("fork");
        exit(1);
    }

    if (pid2 == 0) {  // Second child process
        close(pipefd[1]); // Close unused write end

        sleep(3);

        // Redirect stdin to the read end of the pipe
        dup2(pipefd[0], STDIN_FILENO);
        close(pipefd[0]);

        // Execute the "wc" command
        execlp("wc", "wc", "-l", NULL);
        perror("execlp"); // If execlp fails
        exit(1);
    }

    close(pipefd[0]);
    close(pipefd[1]);

    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);

    // Restore the original signal mask
    sigprocmask(SIG_SETMASK, &old_set, NULL);
    return 0;
}

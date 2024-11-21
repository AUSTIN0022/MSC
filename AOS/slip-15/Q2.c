#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

pid_t child_pid;

void chld_handler(int signum) {
    int status;
    pid_t pid = waitpid(child_pid, &status, WNOHANG);

    if (pid > 0) {
        if (WIFEXITED(status)) {
            printf("Child process (PID: %d) exited with status %d.\n", pid, WEXITSTATUS(status));
        } else if (WIFSIGNALED(status)) {
            printf("Child process (PID: %d) killed by signal %d.\n", pid, WTERMSIG(status));
        }
        exit(0);
    }
}

void alrm_handler(int signum) {
    printf("Child process did not complete within 5 seconds. Killing child process (PID: %d).\n", child_pid);
    kill(child_pid, SIGKILL);
}

int main() {
    
    if (signal(SIGCHLD, chld_handler) == SIG_ERR) {
        perror("signal(SIGCHLD)");
        exit(1);
    }

    if (signal(SIGALRM, alrm_handler) == SIG_ERR) {
        perror("signal(SIGALRM)");
        exit(1);
    }

    child_pid = fork();

    if (child_pid == -1) {
        perror("fork");
        exit(1);
    }

    if (child_pid == 0) {
        printf("Child process (PID: %d) running...\n", getpid());
        execlp("sleep", "sleep", "10", NULL);
        perror("execlp");
        exit(1);
    } else { 
        alarm(5); 

        while (1) {
            pause(); 
        }
    }
    return 0;
}

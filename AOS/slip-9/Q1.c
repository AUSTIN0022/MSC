#include <stdio.h>
#include <unistd.h>

int main() {
    int pipefds[2];
    int returnstatus;
    int pid;
    char writemessage[20] = "Hello, Child!";
    char readMessage[20];

    // Create an unnamed pipe
    returnstatus = pipe(pipefds);
    if (returnstatus == -1) {
        printf("Unable to create pipe\n");
        return 1;
    }

    // Create a new process
    pid = fork();

    if (pid == -1) {
        printf("Unable to fork process\n");
        return 1;
    }

    if (pid != 0) {
        // Parent process
        close(pipefds[0]);  // Close the read end of the pipe

        printf("In parent: Writing to pipe - Message is '%s'\n", writemessage);
        write(pipefds[1], writemessage, sizeof(writemessage));

        close(pipefds[1]);  // Close the write end of the pipe after writing
    } else {
        // Child process
        close(pipefds[1]);  // Close the write end of the pipe

        read(pipefds[0], readMessage, sizeof(readMessage));
        printf("In child: Reading from pipe - Message is '%s'\n", readMessage);

        close(pipefds[0]);  // Close the read end of the pipe after reading
    }

    return 0;
}

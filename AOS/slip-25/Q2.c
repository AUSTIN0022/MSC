#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main() {
    int fd;

    // Open the file "output.txt" for writing, create it if it doesn't exist
    fd = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 077);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    // Duplicate the file descriptor fd to stdout (file descriptor 1)
    dup2(fd, STDOUT_FILENO);

    // Now any printf statements will write to "output.txt"
    printf("This output is redirected to 'output.txt'.\n");
    printf("Using dup and open system calls to redirect stdout.\n");

    // Close the file descriptor
    close(fd);

    return 0;
}

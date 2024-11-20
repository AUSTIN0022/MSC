#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>    
#include <stdlib.h>    

int main() {
    
    int original_stdout = dup(STDOUT_FILENO);
    if (original_stdout == -1) {
        perror("dup");
        exit(1);
    }

    int file_fd = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (file_fd == -1) {
        perror("open");
        exit(1);
    }

    if (dup2(file_fd, STDOUT_FILENO) == -1) {
        perror("dup2");
        close(file_fd);
        exit(1);
    }

    printf("This text will be written to output.txt\n");
    printf("This is another line that goes to the file\n");

    // Flush the stdout buffer
    fflush(stdout);

    if (dup2(original_stdout, STDOUT_FILENO) == -1) {
        perror("dup2");
        exit(1);
    }

    close(file_fd);
    close(original_stdout);

    printf("Back to printing to console!\n");
    return 0;
}

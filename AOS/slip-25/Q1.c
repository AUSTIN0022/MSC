#include <stdio.h>

int main() {
    // Open a file in write mode ("w") and associate it with stdout
    FILE *file = freopen("output.txt", "w", stdout);

    if (file == NULL) {
        perror("freopen");
        return 1;
    }

    // From this point, anything printed using printf will go to "output.txt"
    printf("This is a demonstration of redirecting stdout to a file.\n");
    printf("All this text will be written to 'output.txt' instead of the console.\n");

    fclose(file);

    return 0;
}
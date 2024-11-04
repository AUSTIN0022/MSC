#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

volatile sig_atomic_t sig_count = 0; // To track the number of times SIGINT is received

void sigint_handler(int signum) {
    sig_count++;

    if (sig_count == 1) {
        printf("Caught SIGINT (Ctrl-C)! Press Ctrl-C again to exit.\n");
    } else if (sig_count == 2) {
        printf("Caught SIGINT (Ctrl-C) again. Exiting...\n");
        exit(0);
    }
}

int main() {
    // Set up signal handler for SIGINT
    if (signal(SIGINT, sigint_handler) == SIG_ERR) {
        perror("signal");
        exit(1);
    }
    // Infinite loop to keep the program running
    while (1) {
        printf("Running... Press Ctrl-C to test signal handling.\n");
        sleep(5); // Sleep for 5 seconds to make the program wait
    }

    return 0;
}

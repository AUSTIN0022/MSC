#include <stdio.h>
#include <sys/stat.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <file1> <file2> ...\n", argv[0]);
        return 1;
    }

    struct stat fileStat;

    for (int i = 1; i < argc; i++) {
        if (stat(argv[i], &fileStat) < 0) {
            printf("Error");
            continue;
        }

        printf("File: %s\n", argv[i]);
        printf("Inode Number: %ld\n", (long)fileStat.st_ino);

        printf("File Type: ");
        switch (fileStat.st_mode & S_IFMT) {
            case S_IFREG:  printf("Regular File\n"); break;
            case S_IFDIR:  printf("Directory\n"); break;
            case S_IFLNK:  printf("Symbolic Link\n"); break;        
            case S_IFCHR:  printf("Character Device\n"); break;
            case S_IFBLK:  printf("Block Device\n"); break;
            case S_IFIFO:  printf("FIFO/PIPE\n"); break;
            case S_IFSOCK: printf("Socket\n"); break;
            default:       printf("Unknown\n"); break;
        }
        printf("\n");
    }
    return 0;
}

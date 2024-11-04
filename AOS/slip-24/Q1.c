#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char* argv[]) {
    struct stat buffer;
    int flag;

    if (argc < 2) {
        printf("Usage: %s <file1> <file2> ...\n", argv[0]);
        return 1;
    }

    for (int i = 1; i < argc; i++) {
        printf("\n\n%d: File : %s ", i, argv[i]);
        flag = stat(argv[i], &buffer);

        if (flag == 0) {
            printf("File Exists\n");
            printf("INode Number : %ld\n", (long)buffer.st_ino);
            printf("File Type: ");

            switch (buffer.st_mode & S_IFMT) {
                case S_IFREG:  printf("Regular File\n"); break;
                case S_IFDIR:  printf("Directory\n"); break;
                case S_IFCHR:  printf("Character Device\n"); break;
                case S_IFBLK:  printf("Block Device\n"); break;
                case S_IFIFO:  printf("FIFO/PIPE\n"); break;
                case S_IFLNK:  printf("Symbolic Link\n"); break;
                case S_IFSOCK: printf("Socket\n"); break;
                default:       printf("Unknown\n"); break;
            }

        } else {
            printf("File does not exist!\n");
        }
        printf("___________________________________________________________________\n");
    }
    return 0;
}
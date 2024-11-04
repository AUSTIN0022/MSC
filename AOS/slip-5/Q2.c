#include<stdio.h>
#include<unistd.h>
#include<string.h>

int main()
{
    int pipefds[2];
    char msg1[20] = "Hello world";
    char msg2[20] = "Hello SPPU";
    char msg3[20] = "Linux is Funny";
    char readMessage[20];
    
    if(pipe(pipefds) == -1) {
        printf("Unable to create pipe\n");
        return 1;
    }
    
    int pid = fork();
    
    if(pid == -1) {
        printf("Fork failed\n");
        return 1;
    }
    
    if(pid > 0) {  // Parent process
        close(pipefds[1]);  // Close unused write end
        
        read(pipefds[0], readMessage, sizeof(readMessage));
        printf("In parent:- Message is %s\n", readMessage);
        sleep(1);
        
        read(pipefds[0], readMessage, sizeof(readMessage));
        printf("In parent:- Message is %s\n", readMessage);
        sleep(1);
        
        read(pipefds[0], readMessage, sizeof(readMessage));
        printf("In parent:- Message is %s\n", readMessage);
        
        close(pipefds[0]);  // Close read end after reading
    } 
    else {  // Child process
        close(pipefds[0]);  // Close unused read end
        
        printf("In child:- Writing Message1: %s\n", msg1);
        write(pipefds[1], msg1, sizeof(msg1));
        sleep(1);
        
        printf("In child:- Writing Message2: %s\n", msg2);
        write(pipefds[1], msg2, sizeof(msg2));
        sleep(1);
        
        printf("In child:- Writing Message3: %s\n", msg3);
        write(pipefds[1], msg3, sizeof(msg3));
        
        close(pipefds[1]);  // Close write end after writing
    }
    return 0;
}

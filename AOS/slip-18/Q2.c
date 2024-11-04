#include<stdio.h>
#include<unistd.h>

int main()
{
	int pipefds[2];
	int returnstatus;
	int pid;
	char msg1[20] = "Hello world", msg2[20] = "Hello SPPU", msg3[20] = "Linux is Funny";
	char readMessage[20];
	
	returnstatus = pipe(pipefds);
	
	if(returnstatus == 1){
		printf("Unable to create pipe 1\n");
		return 1;
	}
	
	pid = fork();
	
	if(pid != 0){
		close(pipefds[0]);

		printf("In parent:- Message is %s\n", msg1);
		write(pipefds[1], msg1, sizeof(msg1));
		sleep(1);
		
		printf("In parent:- Message is %s\n", msg2);
		write(pipefds[1], msg2, sizeof(msg2));
		sleep(1);
		
		printf("In parent:- Message is %s\n", msg3);
		write(pipefds[1], msg3, sizeof(msg3));
	} else {
		close(pipefds[1]);

		read(pipefds[0], readMessage, sizeof(readMessage));
		printf("In child:- Message is %s\n", readMessage);
		sleep(1);
		
		read(pipefds[0], readMessage, sizeof(readMessage));
		printf("In child:- Message is %s\n", readMessage);
		sleep(1);
		
		read(pipefds[0], readMessage, sizeof(readMessage));
		printf("In child:- Message is %s\n", readMessage);
	}
	return 0;
}

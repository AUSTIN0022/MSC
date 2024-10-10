#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<time.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<string.h>


int main(int argc, char* argv[])
{
	char fname[20];
	struct stat buffer;
	int flag;
	
	for(int i = 1; i <= argc-1; i++){
	
		printf("\n\n%d: File : %s ",i, argv[i]);
		flag = stat(argv[i], &buffer);
	
		if(flag == 0){
		
			printf("File Exists\n");
			printf("\n INode : %ld", buffer.st_ino);
			printf("\n File Size : %ld", buffer.st_size);
			printf("\n No of link to file : %ld", buffer.st_nlink);
			printf("\n Last Access Time : %s", ctime(&buffer.st_atime));
			printf(" Last Modified Time : %s", ctime(&buffer.st_mtime));
			printf(" Permissions for file : ");
			if(buffer.st_mode && R_OK)
				printf("\t Read");
			if(buffer.st_mode && W_OK)
				printf("\t Write");
			if(buffer.st_mode && X_OK)
				printf("\t Execute");
		} else {
			printf("\nFile does not exist!");
		}
        printf("\n_____________________________________________________________________\n");
	}
	return 0;
}

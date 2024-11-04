#include<stdio.h>
#include<stdlib.h>
#include<dirent.h>


int main(){
	DIR *dir = opendir(".");
	
	if(dir == NULL){
		printf("\nCould Not open the directory!");
		return 1;
	}
	
	int file_cnt = 0;
	
	struct dirent *entry;
	
	printf("\nFile in Directory : \n");
	while((entry = readdir(dir)) != NULL){
		if(entry->d_type == DT_REG){
			printf(" %s \n",entry->d_name);
			file_cnt++;
		}
	}
	
	printf("\nNo of file present : %d\n", file_cnt);
	
	closedir(dir);
	return 0;
}

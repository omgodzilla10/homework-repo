#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define LINESIZE 128

size_t prompt_user (void) {
	int selection = 0;
	char readSelection[LINESIZE] = "";
	
	printf("%s", "Enter an integer");
	if(fgets(readSelection, LINESIZE, stdin)) {
		sscanf(readSelection, "%d", &selection);
		return selection;
	} else {
		clearerr(stdin);
		return 0;
	}
}


void append(char word[], size_t n) {
	char entered_string[LINESIZE];
}

void modify(size_t idx) {
	
}

void displayAll(FILE *fp) {
	int c = 0;
	while(!feof(fp)) {
		c = fgetc(fp);
		printf("%c", c);
	}
}

int main (int argc, char *argv[]) {
	int user_response = 0;
	char records_file[LINESIZE] = "";
	FILE *fp;
	
	if(argc > 1) {
		strcpy(argv[1], records_file);
		printf("%s", records_file);
		
		/* Create the text file if it doesn't already exist. */
		if((fp = fopen(records_file, "ab+")) == 0) {
			perror("fopen failed");
			return 0;
		}
		
		while(user_response != -2 && user_response != EOF) {
			user_response = prompt_user();
			
			switch (user_response) {
				case -2: return 0;
				case -1: append(records_file, LINESIZE);
					break;
				case 0: displayAll(fp);
					break;
				default: modify(user_response);
					break;
			}
		}
	} else {
		printf("%s", "Missing file name");
	}
	
	fclose(fp);
	return 0;
}
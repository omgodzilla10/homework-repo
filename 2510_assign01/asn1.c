#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define LINESIZE 1024

size_t prompt_user (void) {
	int selection = 0;
	char readSelection[LINESIZE] = "";
	
	printf("\nMain Menu\n\n - To modify a student's records, enter a record number.");
	printf("\n - Enter 0 to display all students");
	printf("\n - Enter -1 to append to the record");
	printf("\n - Enter -2 to exit the application\n");
	
	if(fgets(readSelection, LINESIZE, stdin)) {
		sscanf(readSelection, "%d", &selection);
		return selection;
	} else {
		clearerr(stdin);
		return 0;
	}
}


void get_student_info(FILE *fp, const char prompt[], size_t n) {
	char entered_string[n];
	char word[n];
	
	while (1) {
		printf("%s\n", prompt);
		if(fgets(entered_string, n, stdin)) {
			sscanf(entered_string, "%s", word);
			printf("%s\n", word);
		
			if(word == EOF) {
				break;
			} else if(strlen(word) <= n) {
				break;
			}
		} else {
			clearerr(stdin);
		}
	}
}

void appendStudent(FILE *fp) {
	char prompt[] = "Enter the student's information";
	get_student_info(fp, prompt, LINESIZE);
}

void modify(size_t idx) {
	
}

void displayAll(FILE *fp) {
	int c = 0;
	while(!feof(fp)) {
		c = fgetc(fp);
		printf("%d", c);
	}
}

int main (int argc, char *argv[]) {
	int user_response = 0;
	FILE *fp;
	
	if(argc == 2) {
		/* Create the text file if it doesn't already exist. */
		if((fp = fopen(argv[1], "wb+")) == 0) {
			perror("fopen failed");
			return 0;
		}
		
		while(user_response != -2 && user_response != EOF) {
			user_response = prompt_user();
			
			switch (user_response) {
				case -2: return 0;
				case -1: appendStudent(fp);
					break;
				case 0: displayAll(fp);
					break;
				default: modify(user_response);
					break;
			}
		}
	} else {
		perror("Missing file name");
	}
	
	fclose(fp);
	return 0;
}
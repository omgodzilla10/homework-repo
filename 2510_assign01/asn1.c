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


void append_student_info(FILE *fp, const char prompt[], size_t n) {
	char entered_string[LINESIZE];
	char word[LINESIZE];
	
	while (1) {
		printf("%s\n", prompt);
		if(fgets(entered_string, LINESIZE, stdin)) {
			sscanf(entered_string, "%s", word);
			
			if(strlen(word) < n || strlen(word) > n) {
				printf("\n%s%d%s\n", "Line must contain ", n, " characters.");
				continue;
			}
			
			break;
		} else {
			clearerr(stdin);
		}
	}
	
	fprintf(fp, "%s", word);
}

void appendStudent(FILE *fp) {
	char idPrompt[] = "Enter the student's ID";
	char gradePrompt[] = "Enter the student's grade";
	
	append_student_info(fp, idPrompt, 9);
	fprintf(fp, " ");
	append_student_info(fp, gradePrompt, 3);
	fprintf(fp, "\n");
}

void modify(size_t idx) {
}

void displayAll(FILE *fp) {
	char word[LINESIZE];
	char entered_string[LINESIZE];
	int row = 1;
	int grade = 0;
	fseek(fp, 0, SEEK_SET);
	
	while (fgets(entered_string, LINESIZE, fp) != NULL) {
		sscanf(entered_string, "%s %d", word,&grade);
		fprintf(stderr, "%d ", row++);
		fprintf(stderr, "%s %d\n", word, grade);
		
		if(*word == EOF) {
			break;
		} else {
			clearerr(stdin);
		}
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
		
		while(user_response != -2) {
			user_response = prompt_user();
			
			switch (user_response) {
				case -2: return 0;
				case -1: appendStudent(fp);
					break;
				case 0: displayAll(fp);
					break;
				default: modify(user_response);
			}
		}
	} else {
		perror("Missing file name");
	}
	
	fclose(fp);
	return 0;
}
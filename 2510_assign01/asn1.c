#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define LINESIZE 1024

/*
	Returns true if the passed in string is a valid student ID.
*/
int valid_std_id (char word[]) {
	size_t i;
	
	if (strcmp(word, "-1") == 0) {
		return 1;
	}
	
	if (strlen(word) != 9) {
		printf("\n%s\n", "Line must contain 9 characters.");
		return 0;
	} 
	
	if (word[0] != 'a' && word[0] != 'A') {
		printf("\n%s\n", "Student ID must start with an 'a'");
		return 0;
	}
	
	for (i = 1; i < strlen(word); i++) {
		if (!isdigit((int)word[i])) {
			printf("\n%s\n", "Student ID cannot contain any letters following 'a'");
			return 0;
		}
	}
	
	return 1;
}

/*
	Prints the given row, student ID, and grade, to the file.
*/
void append_to_file(FILE *fp, int row, char stdId[], char gradeStr[]) {
	fprintf(fp, "%d %s ", row, stdId);
	
	if (strlen(gradeStr) == 1) {
		fprintf(fp, "%s  ", gradeStr);
	} else if (strlen(gradeStr) == 2) {
		fprintf(fp, "%s ", gradeStr);
	} else {
		fprintf(fp, "%s", gradeStr);
	}
	
	fprintf(fp, "\n");
}

/*
	Prompts the user for a student id and a score, and appends
	the student's information to the file.
*/
int append_student(FILE *fp, int row) {
	char entered_string[LINESIZE];
	char stdId[LINESIZE];
	char gradeStr[LINESIZE];
	char promptId[] = "Please enter the student's ID.";
	char promptGrade[] = "Please enter the student's grade.";
	int grade = 0;
	
	/* Prompt the user for the student ID. */
	while (1) {
		printf("%s\n", promptId);
		if (fgets(entered_string, LINESIZE, stdin)) {
			sscanf(entered_string, "%s", stdId);
			
			if (strcmp(stdId, "-1") == 0) {
				return 0;
			}
			
			if (valid_std_id(stdId)) {
				break;
			}
		} else {
			clearerr(stdin);
		}
	}
	
	/* Prompt the user for the student' score. */
	while (1) {
		printf("%s\n", promptGrade);
		if (fgets(entered_string, LINESIZE, stdin)) {
			sscanf(entered_string, "%d", &grade);
			sscanf(entered_string, "%s", gradeStr);
			
			if (strcmp(gradeStr, "-1") == 0) {
					return 0;
			}
			
			if (grade > 100) {
				printf("\n%s\n", "Grade cannot be above 100.");
			} else if (grade < -1) {
				printf("\n%s\n", "Grade cannot be below 0.");
			} else break;
		} else {
			clearerr(stdin);
		}
	}
	
	append_to_file(fp, row, stdId, gradeStr);
	return 1;
}

/*
	Seeks to the given row number in the file.
*/
int seek_to_row(FILE *fp, int row) {
	int rowLength = 0;
	char entered_string[LINESIZE];
	
	fseek(fp, 0, SEEK_SET);
	if(fgets(entered_string, LINESIZE, fp)) {
		rowLength = strlen(entered_string);
		fseek(fp, rowLength * (row - 1), SEEK_SET);
	}
	
	return rowLength;
}

/*
	Prints the student information at the given row.
*/
int print_student_at(FILE *fp, int row) {
	char stdId[LINESIZE];
	char entered_string[LINESIZE];
	int grade = 0;
	int rowLength = 0;
	
	rowLength = seek_to_row(fp, row);
	
	if (fgets(entered_string, rowLength, fp)) {
		sscanf(entered_string, "%d %s %d", &row, stdId, &grade);
		fprintf(stderr, "%d %s %d\n", row, stdId, grade);
		fseek(fp, rowLength * (row - 1), SEEK_SET);
		return 1;
	}
	
	return 0;
}

/*
	Allow the user to change the student's information on the
	specified row.
*/
void modify(FILE *fp, int idx) {
	if (idx > 0) {
		if (print_student_at(fp, idx)) {
			append_student(fp, idx);
		}
	}
}

/*
	Prints the information for each student.
*/
void displayAll(FILE *fp) {
	char word[LINESIZE];
	char entered_string[LINESIZE];
	int row;
	int grade = 0;
	fseek(fp, 0, SEEK_SET);
	
	while (fgets(entered_string, LINESIZE, fp)) {
		sscanf(entered_string, "%d %s %d", &row, word, &grade);
		fprintf(stderr, "%d %s %d\n", row, word, grade);
		
		if (*word == EOF) {
			break;
		} else {
			clearerr(stdin);
		}
	}
}

/*
	Displays the main menu and prompts the user to select an option.
	Returns the user's selection.
*/
size_t prompt_user (void) {
	int selection = 0;
	char readSelection[LINESIZE];
	
	printf("\nMain Menu\n\n - To modify a student's records, enter a record number.");
	printf("\n - Enter 0 to display all students");
	printf("\n - Enter -1 to append to the record");
	printf("\n - Enter -2 to exit the application\n\n");
	
	if (fgets(readSelection, LINESIZE, stdin)) {
		sscanf(readSelection, "%d", &selection);
		return selection;
	} else {
		clearerr(stdin);
		return 0;
	}
}

/*
	The main method, requires a file name as a command-line
	argument.
*/
int main (int argc, char *argv[]) {
	int user_response = 0;
	int row = 1;
	FILE *fp;
	
	if (argc == 2) {
		/* Create the text file if it doesn't already exist. */
		if ((fp = fopen(argv[1], "wb+")) == 0) {
			perror("fopen failed");
			return 0;
		}
		
		while (user_response != -2) {
			user_response = prompt_user();
			
			switch (user_response) {
				case -2: return 0;
				case -1: 
					if (append_student(fp, row))
						row++;
					break;
				case 0: displayAll(fp);
					break;
				default: modify(fp, user_response);
			}
		}
	} else {
		perror("Missing file name");
	}
	
	fclose(fp);
	return 0;
}
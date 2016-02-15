#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define LINESIZE 1024

/*
	This program will allow the user to enter student records (a student ID and a grade)
	to a text file. The user is able to append new student records, modify existing
	student records, and display all students.
	
	Author: Trevor Hoefsloot
	Feb 12th, 2016
*/

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
void append_to_file(FILE *fp, char stdId[], char gradeStr[]) {
	fprintf(fp, "%s ", stdId);
	
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
int append_student(FILE *fp) {
	char enteredString[LINESIZE];
	char stdId[LINESIZE];
	char gradeStr[LINESIZE];
	char promptId[] = "Enter the student's ID.";
	char promptGrade[] = "Enter the student's grade.";
	int grade = 0;
	
	/* Prompt the user for the student ID. */
	while (1) {
		printf("%s\n", promptId);
		if (fgets(enteredString, LINESIZE, stdin)) {
			if (sscanf(enteredString, "%s", stdId) == 1) {
			
				if (strcmp(stdId, "-1") == 0) {
					return 0;
				}
				
				if (valid_std_id(stdId)) {
					break;
				}
			}
		} else {
			clearerr(stdin);
			return 0;
		}
	}
	
	/* Prompt the user for the student' score. */
	while (1) {
		printf("%s\n", promptGrade);
		if (fgets(enteredString, LINESIZE, stdin)) {
			if (sscanf(enteredString, "%d", &grade) == 1 &&
				sscanf(enteredString, "%s", gradeStr) == 1) {
			
				if (strcmp(gradeStr, "-1") == 0) {
						return 0;
				}
				
				if (grade > 100) {
					printf("\n%s\n", "Grade cannot be above 100.");
				} else if (grade < -1) {
					printf("\n%s\n", "Grade cannot be below 0.");
				} else break;
			}
		} else {
			clearerr(stdin);
			return 0;
		}
	}
	
	append_to_file(fp, stdId, gradeStr);
	return 1;
}

/*
	Seeks to the given row number in the file.
	Returns the length of each row.
*/
int seek_to_row(FILE *fp, int row) {
	int rowLength = 0;
	char enteredString[LINESIZE];
	
	fseek(fp, 0, SEEK_SET);
	if(fgets(enteredString, LINESIZE, fp)) {
		rowLength = strlen(enteredString);
		fseek(fp, rowLength * (row - 1), SEEK_SET);
	}
	
	return rowLength;
}

/*
	Prints the student information at the given row.
	Returns 1 if the student is printed successfully.
*/
int print_student_at(FILE *fp, int row) {
	char stdId[LINESIZE];
	char enteredString[LINESIZE];
	int grade = 0;
	int rowLength = 0;
	
	rowLength = seek_to_row(fp, row);
	
	/* Prints the row if it exists. */
	if (fgets(enteredString, rowLength, fp)) {
		if ((sscanf(enteredString, "%s %d", stdId, &grade) == 2)) {
			fprintf(stderr, "%d %s %d\n", row, stdId, grade);
			fseek(fp, rowLength * (row - 1), SEEK_SET);
			return 1;
		}
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
			append_student(fp);
		}
	}
}

/*
	Prints the information for each student.
*/
void display_all(FILE *fp) {
	char word[LINESIZE];
	char enteredString[LINESIZE];
	int row = 1;
	int grade = 0;
	fseek(fp, 0, SEEK_SET);
	
	/* Loop through and print each line. */
	while (fgets(enteredString, LINESIZE, fp)) {
		if (sscanf(enteredString, "%s %d", word, &grade) == 2) {
			fprintf(stderr, "%d %s %d\n", row++, word, grade);
			
			if (*word == EOF) {
				break;
			} else {
				clearerr(stdin);
			}
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
		if (sscanf(readSelection, "%d", &selection) == 1) {
			return selection;
		}
	}
	
	return -3;
}

/*
	The main method, requires a file name as a command-line
	argument.
*/
int main (int argc, char *argv[]) {
	int userResponse = 0;
	int row = 1;
	FILE *fp;
	
	if (argc == 2) {
		/* Create the text file if it doesn't already exist. */
		if ((fp = fopen(argv[1], "wb+")) == 0) {
			perror("fopen failed");
			return 0;
		} else {
			while (userResponse != -2) {
				userResponse = prompt_user();
				
				switch (userResponse) {
					case -2: return 0;
					case -1: 
						if (append_student(fp))
							row++;
						break;
					case 0: display_all(fp);
						break;
					case -3: break;
					default: modify(fp, userResponse);
				}
			}	
		}
	} else {
		perror("Missing file name");
	}
	
	fprintf(fp, "\n");
	fclose(fp);
	return 0;
}
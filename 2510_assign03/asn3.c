#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXSIZE 1024
#define NAMESIZE 20

typedef struct { 
	char last[NAMESIZE]; /* last name (1 word) */ 
	char first[NAMESIZE]; /* first name (1 word) */ 
} name;
	
typedef struct { 
	name name; 
	int score; /* score (between 0 & 100 inclusive) */ 
} record;

int parse_record(char entered_record[]) {
	int i;
	int j = 0;
	int spaceCount = 0;
	char firstName[NAMESIZE] = "";
	char lastName[NAMESIZE] = "";
	char grade[4] = "";
	
	record newRecord;
	name newName;
	
	for (i = 0; entered_record[i] != '\0'; i++) {
		if (entered_record[i] == ' ') {
			spaceCount++;
			j = 0;
		} else {
			switch (spaceCount) {
				case 0: firstName[j++] = entered_record[i];
					break;
				case 1: lastName[j++] = entered_record[i];
					break;
				case 2: grade[j++] = entered_record[i];
					break;
			}
		}
	}
	
	if (spaceCount != 2)
		return 0;
	
	if (!sscanf(grade, "%d", &newRecord.score) || newRecord.score < 0 || newRecord.score > 100)
		return 0;
	
	if (!sscanf(firstName, "%s", newName.first))
		return 0;
	
	if (!sscanf(lastName, "%s", newName.last))
		return 0;
	
	newRecord.name = newName;
	
	return 1;
}

int main (void) {
	char entered_string[MAXSIZE] = "";
	while(fgets(entered_string, MAXSIZE, stdin)) {
		if (!parse_record(entered_string))
			/* temp */
			fprintf(stderr, "Invalid record entered.");
	}
	printf(entered_string);
	
	return 1;
}

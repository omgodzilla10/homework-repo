#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#define MAXSIZE 256
#define NAMESIZE 20
#define BLOCK 2

typedef struct { 
	char last[NAMESIZE]; /* last name (1 word) */ 
	char first[NAMESIZE]; /* first name (1 word) */ 
} name;
	
typedef struct { 
	name name; 
	int score; /* score (between 0 & 100 inclusive) */ 
} record;

int parse_record(char entered_record[], record *recordPtr) {
	int i;
	int j = 0;
	int spaceCount = 0;
	int leadingSpaces = 1;
	char firstName[NAMESIZE] = "";
	char lastName[NAMESIZE] = "";
	char grade[4] = "";
	
	record newRecord;
	name newName;
	
	for (i = 0; entered_record[i] != '\0'; i++) {
		entered_record[i] = tolower(entered_record[i]);
		if (i > 0 && entered_record[i] == ' ' && entered_record[i-1] == ' ') {
			leadingSpaces = 1;
		} else if (entered_record[i] == ' ' && !leadingSpaces) {
			spaceCount++;
			j = 0;
		} else if (entered_record[i] != ' ') {
			leadingSpaces = 0;
			switch (spaceCount) {
				case 0: firstName[j++] = entered_record[i];
					break;
				case 1: lastName[j++] = entered_record[i];
					break;
				case 2: grade[j++] = entered_record[i];
					if (grade[j-1] == '.') {
						return 0;
					}
					
					break;
			}
		}
	}
	
	if (spaceCount != 2)
		return 0;
	
	if (strlen(firstName) > NAMESIZE || strlen(lastName) > NAMESIZE)
		return 0;
	
	if (!sscanf(grade, "%d", &newRecord.score) || newRecord.score < 0 || newRecord.score > 100)
		return 0;
	
	if (!sscanf(firstName, "%s", newName.first))
		return 0;
	
	if (!sscanf(lastName, "%s", newName.last))
		return 0;
	
	newRecord.name = newName;
	*recordPtr = newRecord;
	
	return 1;
}

void display_records(record *recordList, size_t numAllocated) {
	size_t i;
	for (i = 0; i < numAllocated; i++) {
		printf("\n%s %s %d", recordList[i].name.last, recordList[i].name.first, recordList[i].score);
	}
}

int main (void) {
	size_t numAllocated = 0;
	size_t numUsed = 0;
	char entered_string[MAXSIZE] = "";
	record *tempList;
	record *recordList;
	record newRecord;
	
	while(fgets(entered_string, MAXSIZE, stdin)) {
		if (numUsed == numAllocated) {
			if (!(tempList = realloc(recordList, (numAllocated + BLOCK) * sizeof(record)))) {
				fprintf(stderr, "Failed to allocate additional memory.");
				return 0;
			}
			
			recordList = tempList;
			numAllocated += BLOCK;
		}
		
		if(parse_record(entered_string, &newRecord)) {
			recordList[numUsed++] = newRecord;
		}
	}
	
	display_records(recordList, numUsed);
	
	return 1;
}

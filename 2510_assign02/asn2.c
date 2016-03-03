#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define LINESIZE 256

int string_contains(char x, char string[]) {
	int i;
	for (i = strlen(string); i >= 0; i--) {
		if (string[i] == x)
			return i;
	}
	
	return -1;
}

void translate_string(char *entered_string, char set1[], char set2[]) {
	int i;
	int j = 0;
	
	for (i = 0; entered_string[i] != '\0'; i++) {
		j = string_contains(entered_string[i], set1);
		
		if (j != -1 && j < strlen(set2))
			entered_string[i] = set2[j];
	}
}

/*
	Parses hyphens from the command-line argument and places letters into the
	passed in set. Returns 0 if no hyphen was parsed, or if a hyphen is parsed, 
	returns the new position in the set.
*/
int parse_hyphen(char argv[], char set[], int currentArgIndex, int currentSetIndex) {
	char initChar;
	char finalChar;
	
	if (argv[currentArgIndex] == '-') {
		if ((currentArgIndex - 1) >= 0 && argv[currentArgIndex + 1] != '\0' 
			&& isalpha(argv[currentArgIndex - 1]) && isalpha(argv[currentArgIndex + 1])) {
			initChar = argv[currentArgIndex - 1];
			finalChar = argv[currentArgIndex + 1];
			
			currentSetIndex--;
			while (initChar != finalChar) {
				set[currentSetIndex++] = initChar++;
			}
			
			return currentSetIndex;
		}
	}
	
	return -1;
}

int parse_escape(char argv[], char set[], int currentArgIndex, int currentSetIndex) {
	if (argv[currentArgIndex] == '\\') {
		if (argv[currentArgIndex + 1] != '\0') {
			switch (argv[currentArgIndex + 1]) {
				case '\\': set[currentSetIndex++] = '\\';
					return currentSetIndex;
				case 'a': set[currentSetIndex++] = '\a';
					return currentSetIndex;
				case 'b': set[currentSetIndex++] = '\b';
					return currentSetIndex;
				case 'f': set[currentSetIndex++] = '\f';
					return currentSetIndex;
				case 'n': set[currentSetIndex++] = '\n';
					return currentSetIndex;
				case 'r': set[currentSetIndex++] = '\r';
					return currentSetIndex;
				case 't': set[currentSetIndex++] = '\t';
					return currentSetIndex;
				case 'v': set[currentSetIndex++] = '\v';
					return currentSetIndex;
				case '\'': set[currentSetIndex++] = '\'';
					return currentSetIndex;
				case '"': set[currentSetIndex++] =  '"';
					return currentSetIndex;
			}
		}
	}
	
	return -1;
}

void copy_set(char argv[], char set[]) { 
	int i;
	int j = 0;
	int parsedEscape = 0;
	int parsedHyphen = 0;
	
	for (i = 0; argv[i] != '\0'; i++) {
		if ((parsedEscape = parse_escape(argv, set, i, j)) != -1) {
			j = parsedEscape;
			i++;
		} else if ((parsedHyphen = parse_hyphen(argv, set, i, j)) != -1) {
			j = parsedHyphen;
		} else set[j++] = argv[i];
	}
}

void parse_sets(char argv1[], char argv2[], char set1[], char set2[]) {
	copy_set(argv1, set1);
	copy_set(argv2, set2);
}

int main(int argc, char *argv[]) {
	char entered_string[LINESIZE];
	char set1[LINESIZE];
	char set2[LINESIZE];
	
	if (argc == 3) {
		parse_sets(argv[1], argv[2], set1, set2);
		printf("%s %s", set1, set2);
		while (fgets(entered_string, LINESIZE, stdin)) {
			translate_string(entered_string, set1, set2);
			printf(entered_string);
		}
	} else fprintf(stderr, "Incorrect number of arguments.");
	
	return 0;
}
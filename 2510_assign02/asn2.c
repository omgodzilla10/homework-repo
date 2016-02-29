#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define LINESIZE 256

int string_contains(char x, char string[]) {
	int i;
	for (i = 0; string[i] != '\0'; i++) {
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

void copy_set(char argv[], char set[]) { 
	int i;
	int j = 0;
	char initChar;
	char finalChar;
	
	char nextChar;
	for (i = 0; argv[i] != '\0'; i++) {
		if (argv[i] == '\\') {
			if ((nextChar = argv[i + 1]) != '\0') {
				switch (nextChar) {
					case '\\': set[j++] = '\\';
						i++;
						break;
					case 'a': set[j++] = '\a';
						i++;
						break;
					case 'b': set[j++] = '\b';
						i++;
						break;
					case 'f': set[j++] = '\f';
						i++;
						break;
					case 'n': set[j++] = '\n';
						i++;
						break;
					case 'r': set[j++] = '\r';
						i++;
						break;
					case 't': set[j++] = '\t';
						i++;
						break;
					case 'v': set[j++] = '\v';
						i++;
						break;
					case '\'': set[j++] = '\'';
						i++;
						break;
					case '"': set[j++] =  '"';
						i++;
						break;
				}
			}
		} else if (argv[i] == '-') {
			if ((i - 1) >= 0 && argv[i + 1] != '\0' && isalpha(argv[i - 1])
				&& isalpha(argv[i + 1])) {
				initChar = argv[i - 1];
				finalChar = argv[i + 1];
				
				while (initChar <= finalChar) {
					set[j++] = initChar++;
				}
			} else set[j++] = argv[i];
		} else {
			set[j++] = argv[i];
		}
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
		while (fgets(entered_string, LINESIZE, stdin)) {
			translate_string(entered_string, set1, set2);
			printf(entered_string);
		}
	} else fprintf(stderr, "Incorrect number of arguments.");
	
	return 0;
}
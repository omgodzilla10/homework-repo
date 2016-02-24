#include <stdio.h>
#include <string.h>

#define LINESIZE 1024

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

int main(int argc, char *argv[]) {
	char entered_string[LINESIZE];
	
	if (argc == 3) {
		while (fgets(entered_string, LINESIZE, stdin)) {
			translate_string(entered_string, argv[1], argv[2]);
			printf(entered_string);
		}
	} else fprintf(stderr, "Incorrect number of arguments.");
	
	return 0;
}
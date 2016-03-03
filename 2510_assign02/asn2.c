#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define LINESIZE 256

int string_contains(char x, char string[]);
void translate_string(char *entered_string, char set1[], char set2[]);
int parse_hyphen(char argv[], char set[], int currentArgIndex, int currentSetIndex);
int parse_escape(char argv[], char set[], int currentArgIndex, int currentSetIndex);
void copy_set(char argv[], char set[]);
void parse_sets(char argv1[], char argv2[], char set1[], char set2[]);
void truncate_sets(char set1[], char set2[]);

/*
	Finds the given character 'x' in the passed-in string.
	
	char x - The character to search for.
	char string[] - The string to search in.
	
	Returns -1 if not found, or the character's last occurence in the string if it is found.
*/
int string_contains(char x, char string[]) {
	int i;
	for (i = strlen(string); i >= 0; i--) {
		if (string[i] == x)
			return i;
	}
	
	return -1;
}

/*
	Translates the given string, using set1 and set2. Every character of set1 that is found
	in the given string is translated to the corresponding character in set2 of the same index.
	
	char entered_string[] - The string to translate.
	char set1[] - The first set of characters to translate from the entered string.
	char set2[] - The second set of characters, which will be translated to.
*/
void translate_string(char entered_string[], char set1[], char set2[]) {
	int i;
	int j = 0;
	
	for (i = 0; entered_string[i] != '\0'; i++) {
		j = string_contains(entered_string[i], set1);
		
		if (j != -1 && j < strlen(set2))
			entered_string[i] = set2[j];
	}
}

/*
	Parses hyphens from the command-line argument and places each character in the hyphen's 'range'
	into the passed in set. For example, the range 'a-d' will be translated into 'abcd' and stored
	in the 'set' string.
	
	char argv[] - The user's passed in command-line argument to parse and copy.
	char set[] - The string to copy the parsed 'argv' string into.
	int currentArgIndex - The current location in the 'argv' string that is being parsed as the 
		array is being looped through.
	int currentSetIndex - The last index of the 'set' string, where the parsed characters from
		'argv' can be copied.
		
	Returns 0 if no hyphen was parsed, or if a hyphen was parsed, 
		returns the last index of the set.
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

/*
	Parses escape sequences from the command-line argument. In the command-line argument, 
	the escape sequences typed in by the user make up two separate characters (ie. '\' and 'n'). 
	This function will take both characters and combine them into a single escape character (ie. '\n')
	wherever a valid escape sequence is found.
	
	char argv[] - The user's passed in command-line argument to parse and copy.
	char set[] - The string to copy the parsed 'argv' string into.
	int currentArgIndex - The current location in the 'argv' string that is being parsed as the 
		array is being looped through.
	int currentSetIndex - The last index of the 'set' string, where the parsed characters from
		'argv' can be copied.
		
	Returns 0 if no escape sequence was parsed, or if an escape sequence was parsed, 
		returns the last index of the set.
*/
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

/*
	Copies the given command-line argument into the 'set' string, and performs escape sequence parsing and
	character range parsing while copying over each individual character.
	
	char argv[] - The user's passed in command-line argument to parse and copy.
	char set[] - The string to copy the parsed 'argv' string into.
*/
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

/*
	Truncates both of the given strings so that both have an equal length.
	
	char set1[] - The first string.
	char set2[] - The second string.
*/
void truncate_sets(char set1[], char set2[]) {
	int i;
	for (i = 0; set1[i] != '\0' && set2[i] != '\0'; i++);
	set1[i] = '\0';
	set2[i] = '\0';
}

/*
	Copies and parses the two given command-line arguments into the 'set' strings.
	
	char argv1[] - The first command-line argument passed in by the user.
	char argv2[] - The second command-line argument passed in by the user.
	char set1[] - The string to copy and parse argv1 into.
	char set2[] - The string to copy and parse argv2 into.
*/
void parse_sets(char argv1[], char argv2[], char set1[], char set2[]) {
	copy_set(argv1, set1);
	copy_set(argv2, set2);
	truncate_sets(set1, set2);
}

/* 
	The main function. 
	
	int argc - The number of command-line arguments.
	char *argv[] - An array of each command-line argument.
	
	Returns 0 if the program was ran succesfully, otherwise returns 1.
*/
int main(int argc, char *argv[]) {
	char entered_string[LINESIZE] = "";
	char set1[LINESIZE] = "";
	char set2[LINESIZE] = "";
	
	if (argc == 3) {
		parse_sets(argv[1], argv[2], set1, set2);
		while (fgets(entered_string, LINESIZE, stdin)) {
			translate_string(entered_string, set1, set2);
			printf(entered_string);
		}
	} else {
		fprintf(stderr, "Incorrect number of arguments.");
		return 1;
	}
	
	return 0;
}
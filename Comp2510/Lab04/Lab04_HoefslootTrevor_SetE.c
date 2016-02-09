#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define LINESIZE 1024
#define CHECK(PRED) printf("%s ... %s\n", (PRED) ? "passed" : "FAILED!", #PRED)

/*
	- returns 1 if a word is successfully read and stored in the array word
	of n characters; otherwise returns 0 on end-of-file or when the user inputs the word
	specified by eof.
	
	use fgets - store somewhere, use sscanf to scan one string or digit from that line.
*/

int get_word(const char prompt[], char word[], size_t n, const char eof[]) {
	char entered_string[n];
	
	while (1) {
		printf("%s\n", prompt);
		if(fgets(entered_string, n, stdin)) {
			sscanf(entered_string, "%s", word);
			printf("%s\n", word);
		
			if(word == EOF || strcmp(word, eof) == 0) {
				return 0;
			} else if(strlen(word) <= n) {
				return 1;
			}
		} else {
			clearerr(stdin);
			return 0;
		}
		
		
	}
}

/*
	- If an integer between min & max (inclusive) is successfully read, 
	it is returned; Otherwise, returns 0 on end-of-file or when the user
	inputs the word specified by eof.
*/

int get_int(const char prompt[], int min, int max, int eof) {
	
	char read_int[LINESIZE] = "";
	int digit = 0;
	
	printf("%s", prompt);
	
	if(!fgets(read_int, LINESIZE, stdin)) {
		clearerr(stdin);
		return 0;
	}
	
	sscanf(read_int, "%d\n", &digit);
	
	if(digit == eof || digit == EOF) {
		return 0;
	}else if(digit >= max && digit <= min) {
		return digit;
	}
}

int main(void) {
	char eof[] = "eof";
	char blank[LINESIZE] = "";
	char promptWord[] = "Enter a word.";
	char promptInt[] = "Enter an integer.";
	CHECK(get_word(promptWord, blank, 20, eof) != 0);
	CHECK(get_int(promptInt, 100, 10, eof) != 0);
	
	return 0;
}
	
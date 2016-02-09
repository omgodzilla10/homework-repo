#include <stdio.h>
#include <ctype.h>

#define CHECK(PRED) printf("%s ... %s\n", (PRED) ? "passed" : "FAILED!", #PRED)

/*
	- Replaces all occurrences of the character oldc in the string s by the character newc
	- Returns the number of replacements
*/
size_t str_replace_all(char s[], int oldc, int newc) {
	size_t i;
	size_t counter = 0;
	for(i = 0; s[i] != '\0'; i++) {
		if(s[i] == oldc) {
			s[i] = newc;
			counter++;
		}
	}
	
	return counter;
}
/*
	- Replaces the last occurrence of the character oldc in the string s by the character newc.
	- Returns the number of replacements (0 or 1)
*/
size_t str_replace_last(char s[], int oldc, int newc) {
	size_t lastIndex = 0;
	size_t lastIndexFound = 0;
	size_t i;
	
	for(i = 0; s[i] != '\0'; i++) {
		if(s[i] == oldc) {
			lastIndex = i;
			lastIndexFound = 1;
		}
	}
	
	if(lastIndexFound) {
		s[lastIndex] = newc;
	}
	
	return lastIndexFound;
}

/*
	- Returns 1 if the string s consists entirely of digits; other, returns 0.
*/
int str_all_digits(const char s[]) {
	size_t i;
	for(i = 0; s[i] != '\0'; i++) {
		if(!isdigit((int) s[i]))
			return 0;
	}
	
	return 1;
}

/*
	- Returns 1 if the strings s and t have the same sequence of characters
	- Otherwise, returns 0.
*/
int str_equal(const char s[], const char t[]) {
	size_t i;
	for(i = 0; s[i] != '\0'; i++) {
		if(s[i] != t[i])
			return 0;
	}
	
	/* Final check, to see if arrays are same length */
	if(s[i] != t[i])
		return 0;
	
	return 1;
}

int main(void) {
	char s[] = "hello world";
	char j[] = "hello world";
	char g[] = "1235";
	
	CHECK(str_replace_all(s, 'l', 'x') == 3);
	CHECK(str_replace_all(s, 'x', 'l') == 3);
	CHECK(str_replace_all(s, 'o', 'e') == 2);
	
	CHECK(str_replace_last(j, 'h', 'l') == 1);
	CHECK(str_replace_last(j, 't', 'l') == 0);
	CHECK(str_replace_last(j, 'o', 'e') == 1);
	
	CHECK(str_all_digits(j) == 0);
	CHECK(str_all_digits(s) == 0);
	CHECK(str_all_digits(g) == 1);
	
	CHECK(str_equal(s, "helllooowoorld") == 0);
	CHECK(str_equal(j, "hello") == 0);
	CHECK(str_equal(g, "1235") == 1);
	
	return 0;
}

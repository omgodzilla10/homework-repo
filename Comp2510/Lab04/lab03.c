#include <stdio.h>
#include <ctype.h>

void squeeze_spaces(void) {
	int c;
	int prev_char = -1;
	
	while((c = getchar()) != EOF) {
		if(c != ' ') {
			putchar(c);
		} else if(c != prev_char) {
			putchar(c);
		}
		
		prev_char = c;
	}
}

void format_words(void) {
	int c;
	int prev_char;
	int first_char = 1;
	while((c = getchar()) != EOF) {
		if((prev_char == ' ' && c != ' ') || first_char) {
			putchar(toupper(c));
			first_char = 0;
		} else putchar(c);
		
		if(c == '\n')
			first_char = 1;
		
		prev_char = c;
	}
}

int main(void) {
	format_words();
	
	return 0;
}
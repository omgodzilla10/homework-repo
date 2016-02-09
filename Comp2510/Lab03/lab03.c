#include <stdio.h>

void squeeze_spaces(void) {
	int c;
	while ((c = getchar()) != EOF) {
		putchar(c);
	}
}


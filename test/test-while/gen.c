#include <stdio.h>
int main() {
  int x;
  x = 0;
  x = x;
  x = 4;
  printf("%.1f\n", (double)(4));
  while (x <= 6) {
    x = x + 1;
    printf("%.1f\n", (double)(x + 1));
  }
  if (x == 7) {
    printf("%.1f\n", (double)(100));
  }
  return 0;
}

#include <stdio.h>
int main() {
  int x;
  x = 0;
  x = x;
  x = 4;
  printf("%.1f\n", (double)(4));
  if (x == 4) {
    printf("%.1f\n", (double)(100));
  }
  return 0;
}

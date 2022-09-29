#include <stdio.h>
#include <stdbool.h>


// Aquí suponemos que la entrada es un array de enteros, porque
// C no tiene funciones genéricas, salvo que se utilicen punteros a void
int count(int *arr, int length, bool (*f)(int)) {
  int result = 0;
  for (int i = 0; i < length; i++) {
    if ((*f)(arr[i])) result++;
  }
  return result;
}


bool is_even(int x) {
  return x % 2 == 0;
}

bool is_positive(int x) {
  return x > 0;
}


int main() {
  int a[] = {1, -4, 3, 2, 6, 8, 9};  
  int longitud = sizeof(a) / sizeof(int);
  
  printf("%d\n", count(a, longitud, is_even));      // &rarr; 4
  printf("%d\n", count(a, longitud, is_positive));  // &rarr; 6
}
#include <stdio.h>


typedef enum { ENTERO, CARACTER } TipoDato;

typedef struct {
  TipoDato tag;
  union {
    int entero;
    char caracter;
  };
} EnteroCaracter;


void imprimir(EnteroCaracter ec) {
  switch (ec.tag) {
    case ENTERO:
      printf("Es un entero: %d\n", ec.entero);
      break;
    case CARACTER:
      printf("Es un carácter: %c\n", ec.caracter);
      break;
  }
}


int main() {
  EnteroCaracter ec = { ENTERO, 65 };
  imprimir(ec);
  EnteroCaracter ec2 = { CARACTER, 'b' };
  imprimir(ec2);
}
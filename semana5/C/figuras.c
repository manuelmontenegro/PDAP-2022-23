#include <stdio.h>
#include <math.h>

typedef struct {
  double x, y;
} Punto;

typedef enum { CIRCULO, RECTANGULO, SEGMENTO } TipoFigura;

typedef struct {
  Punto centro;
  double ancho, alto;
} Rectangulo;

typedef struct {
  Punto centro;
  double radio;
} Circulo;

typedef struct {
  Punto desde, hasta;
} Segmento;

typedef struct {
  TipoFigura tag;
  union {
    Rectangulo rectangulo;
    Circulo circulo;
    Segmento segmento;    
  };
} Figura;


double area(Figura f) {
  switch (f.tag) {
    case CIRCULO:
      return f.circulo.radio * f.circulo.radio * M_PI;
    case RECTANGULO:
      return f.rectangulo.ancho * f.rectangulo.alto;
    case SEGMENTO:
      return 0;
  }
}


int main() {
  Figura f;
  f.tag = CIRCULO;
  f.circulo.centro = (Punto){ 10, 20 };
  f.circulo.radio = 1.0;
  printf("Área: %f\n", area(f));
}
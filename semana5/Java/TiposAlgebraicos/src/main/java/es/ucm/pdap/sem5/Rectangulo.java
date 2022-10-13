package es.ucm.pdap.sem5;

import java.util.function.BiFunction;

public final class Rectangulo implements Figura {
    private Punto centro;
    private double ancho, alto;

    public Rectangulo(Punto centro, double ancho, double alto) {
        this.centro = centro;
        this.ancho = ancho;
        this.alto = alto;
    }

    public Punto getCentro() {
        return centro;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    @Override
    public <R> R match(BiFunction<Punto, Double, R> circulo, TriFunction<Punto, Double, Double, R> rectangulo, BiFunction<Punto, Punto, R> segmento) {
        return rectangulo.apply(centro, ancho, alto);
    }
}

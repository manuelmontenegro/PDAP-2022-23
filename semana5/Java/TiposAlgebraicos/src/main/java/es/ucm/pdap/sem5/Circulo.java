package es.ucm.pdap.sem5;

import java.util.function.BiFunction;

public final class Circulo implements Figura {
    private Punto centro;
    private double radio;

    public Circulo(Punto centro, double radio) {
        this.centro = centro;
        this.radio = radio;
    }

    public Punto getCentro() {
        return centro;
    }

    public double getRadio() {
        return radio;
    }

    @Override
    public <R> R match(BiFunction<Punto, Double, R> circulo, TriFunction<Punto, Double, Double, R> rectangulo, BiFunction<Punto, Punto, R> segmento) {
        return circulo.apply(centro, radio);
    }
}

package es.ucm.pdap.sem5;

public class Main {
    public static double area(Figura f) {
        if (f instanceof Circulo c) {
            return c.getRadio() * c.getRadio() * Math.PI;
        } else if (f instanceof Rectangulo r) {
            return r.getAncho() * r.getAlto();
        } else if (f instanceof Segmento) {
            return 0.0;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double area2(Figura f) {
        return f.match(
                (centro, radio) -> radio * radio * Math.PI,
                (centro, ancho, alto) -> ancho * alto,
                (desde, hasta) -> 0.0
        );
    }

}

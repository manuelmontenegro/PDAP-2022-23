package es.ucm.pdap.sem5;

import java.util.function.BiFunction;

public final class Segmento implements Figura {
    private Punto desde, hasta;

    public Segmento(Punto desde, Punto hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public Punto getDesde() {
        return desde;
    }

    public Punto getHasta() {
        return hasta;
    }

    @Override
    public <R> R match(BiFunction<Punto, Double, R> circulo, TriFunction<Punto, Double, Double, R> rectangulo, BiFunction<Punto, Punto, R> segmento) {
        return segmento.apply(desde, hasta);
    }
}

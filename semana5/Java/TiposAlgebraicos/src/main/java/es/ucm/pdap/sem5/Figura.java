package es.ucm.pdap.sem5;
import java.util.function.BiFunction;

public sealed interface Figura permits Circulo, Rectangulo, Segmento {
    public <R> R match(
            BiFunction<Punto, Double, R> circulo,
            TriFunction<Punto, Double, Double, R> rectangulo,
            BiFunction<Punto, Punto, R> segmento
    );
}

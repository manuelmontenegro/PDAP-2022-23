
import { fromEvent, interval, map, concatMap, startWith, scan, takeUntil } from 'rxjs';


const textoContador = document.getElementById('contador');
const botonReiniciar = document.getElementById('reiniciar');

const reini$ = fromEvent(botonReiniciar, 'click').pipe(map(() => true));

function contadorHasta(parada$) {
    return interval(1000).pipe(
        takeUntil(parada$),
        scan(ac => ac + 1, 0),
        startWith(0)
    )
}

const contador$ = reini$.pipe(
    startWith(false),
    concatMap(() => contadorHasta(reini$)),
)

contador$.subscribe(c => textoContador.innerText = `${c}`);

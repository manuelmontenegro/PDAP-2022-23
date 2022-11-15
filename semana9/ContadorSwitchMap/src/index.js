
import { fromEvent, interval, map, startWith, scan, switchMap } from 'rxjs';


const textoContador = document.getElementById('contador');
const botonReiniciar = document.getElementById('reiniciar');

const reini$ = fromEvent(botonReiniciar, 'click').pipe(map(() => true));

function contador() {
    return interval(1000).pipe(
        scan(ac => ac + 1, 0),
        startWith(0)
    )
}

const contador$ = reini$.pipe(
    startWith(false),
    switchMap(() => contador()),
)

contador$.subscribe(c => textoContador.innerText = `${c}`);

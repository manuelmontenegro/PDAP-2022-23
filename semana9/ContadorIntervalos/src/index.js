
import { fromEvent, interval, map, merge, startWith, scan, withLatestFrom } from 'rxjs';


const textoContador = document.getElementById('contador');
const botonIniciar = document.getElementById('iniciar');
const botonParar = document.getElementById('parar');

const ini$ = fromEvent(botonIniciar, 'click').pipe(map(() => true));
const para$ = fromEvent(botonParar, 'click').pipe(map(() => false));

const activo$ = merge(ini$, para$).pipe(startWith(false));

const contador$ = interval(1000).pipe(
    withLatestFrom(activo$),
    scan((ac, [_, activo]) => activo ? ac + 1 : ac, 0),
    startWith(0)
);

contador$.subscribe(c => textoContador.innerText = `${c}`);
activo$.subscribe(act => {botonIniciar.disabled = act; botonParar.disabled = !act});

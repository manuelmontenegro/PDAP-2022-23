
import { fromEvent, startWith, map, scan, merge } from 'rxjs'

const incrementarDOM = document.querySelector('.incrementar');
const contadorDOM = document.querySelector('.contador');
const decrementarDOM = document.querySelector('.decrementar');

const evtIncrementar$ = fromEvent(incrementarDOM, 'click');
const evtDecrementar$ = fromEvent(decrementarDOM, 'click');

const incrementar$ = evtIncrementar$.pipe(map(evt => 1));
const decrementar$ = evtDecrementar$.pipe(map(evt => -1));

const accion$ = merge(incrementar$, decrementar$);

const contador$ = accion$.pipe(
    startWith(0),
    scan((ac, comando) => ac + comando)
);

contador$.subscribe(contador => contadorDOM.innerHTML = contador);
contador$.subscribe(contador => decrementarDOM.disabled = (contador === 0));

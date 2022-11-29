import './rxjs-adapter.js'
import { run } from '@cycle/run'
import { makeDOMDriver } from '@cycle/dom'
import { startWith, map, scan, merge } from 'rxjs'
import { jsx } from 'snabbdom'

function intent(sources) {
    const evtIncrementar$ = sources.DOM.select('button.incrementar').events('click');
    const evtDecrementar$ = sources.DOM.select('button.decrementar').events('click');

    const incrementar$ = evtIncrementar$.pipe(map(evt => +1));
    const decrementar$ = evtDecrementar$.pipe(map(evt => -1));

    return merge(incrementar$, decrementar$);
}


// estado = valor actual del contador
// accion = +1 o -1, según se quiera incrementar o decrementar
function reducer(estado, accion) {
    return estado + accion;
}

function model(accion$) {
    return accion$.pipe(
        scan((estado, accion) => reducer(estado, accion), 0),
        startWith(0)
    );
}

function view(estado$) {
    return estado$.pipe(
        map(contador =>
            <div>
                <button class={{ incrementar: true }}>+</button>
                <div class={{ contador: true }}>{contador}</div>
                <button class={{ decrementar: true }} attrs={{ disabled: contador === 0 }}>-</button>
            </div>
        )
    );
}


function main(sources) {
    return { DOM: view(model(intent(sources))) };
}

run(main, { DOM: makeDOMDriver('#app') });
import './rxjs-adapter.js'
import { startWith, map, scan, merge } from 'rxjs'
import { jsx } from 'snabbdom'

function intent(sources) {
    const evtIncrementar$ = sources.DOM.select('button.incrementar').events('click');
    const evtDecrementar$ = sources.DOM.select('button.decrementar').events('click');

    const incrementar$ = evtIncrementar$.pipe(map(evt => +1));
    const decrementar$ = evtDecrementar$.pipe(map(evt => -1));

    return merge(incrementar$, decrementar$);
}


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

export default function Contador(sources) {
    console.log('Recibido: ');
    const value$ = model(intent(sources));
    return { DOM: view(value$), value: value$ };
}


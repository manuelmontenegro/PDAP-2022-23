import './rxjs-adapter.js'
import { run } from '@cycle/run'
import { makeDOMDriver } from '@cycle/dom'
import { startWith, map, scan, merge } from 'rxjs'
import { jsx } from 'snabbdom'


function main(sources) {
    const evtIncrementar$ = sources.DOM.select('button.incrementar').events('click');
    const evtDecrementar$ = sources.DOM.select('button.decrementar').events('click');

    const incrementar$ = evtIncrementar$.pipe(map(evt => +1));
    const decrementar$ = evtDecrementar$.pipe(map(evt => -1));

    const accion$ = merge(incrementar$, decrementar$);

    const contador$ = accion$.pipe(
        startWith(0),
        scan((ac, x) => ac + x)
    );

    const dom$ = contador$.pipe(
        map(contador =>
            <div>
                <button class={{ incrementar: true }}>+</button>
                <div class={{ contador: true }}>{contador}</div>
                <button class={{ decrementar: true }} attrs={{ disabled: contador === 0 }}>-</button>
            </div>
        )
    );
    
    return { DOM: dom$ };
}

run(main, { DOM: makeDOMDriver('#app') });
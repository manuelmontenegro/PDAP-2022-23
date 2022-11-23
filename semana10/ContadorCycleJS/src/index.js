import './rxjs-adapter.js'
import { run } from '@cycle/run'
import { button, div, makeDOMDriver, h } from '@cycle/dom'
import { startWith, map, scan, merge } from 'rxjs'


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
            div([
                button('.incrementar','+'),
                div('.contador', contador.toString()),
                button('.decrementar', { props: { disabled: contador === 0 } }, '-')
            ])
        )
    );
    
    return { DOM: dom$ };
}

run(main, { DOM: makeDOMDriver('#app') });
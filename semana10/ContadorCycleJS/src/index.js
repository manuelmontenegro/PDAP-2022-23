import './rxjs-adapter.js'
import { run } from '@cycle/run'
import { button, div, makeDOMDriver } from '@cycle/dom'
import { startWith, map, scan, merge } from 'rxjs'


function main(sources) {
    const incrementar$ = sources.DOM.select('.incrementar').events('click').pipe(map(evt => +1));
    const decrementar$ = sources.DOM.select('.decrementar').events('click').pipe(map(evt => -1));

    const contador$ = merge(incrementar$, decrementar$).pipe(
        startWith(0),
        scan((ac, x) => ac + x)
    );

    const dom$ = contador$.pipe(
        map(contador =>
            div([
                button('.incrementar', '+'),
                div('.contador', contador.toString()),
                button('.decrementar', { attrs: {disabled: contador === 0 } }, '-')
            ])
        )
    );
    
    return { DOM: dom$ };
}

run(main, { DOM: makeDOMDriver('#app') });
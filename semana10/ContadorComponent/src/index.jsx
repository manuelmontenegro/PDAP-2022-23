import './rxjs-adapter.js'
import { run } from '@cycle/run'
import { makeDOMDriver } from '@cycle/dom'
import { map, combineLatest } from 'rxjs'
import { jsx } from 'snabbdom'
import Contador from './contador.jsx'

function main(sources) {
    const { DOM: contador1DOM$, value: contador1Value$ } = Contador(sources, 'c1');
    const { DOM: contador2DOM$, value: contador2Value$ } = Contador(sources, 'c2');

    const suma$ = combineLatest([contador1Value$, contador2Value$]).pipe(
        map(([x, y]) => x + y)
    );

    const dom$ = combineLatest([contador1DOM$, contador2DOM$, suma$]).pipe(
        map(([contador1DOM, contador2DOM, contadorValue]) => 
            <div>
                {contador1DOM}
                {contador2DOM}
                <p>La suma de ambos contadores es {contadorValue}</p>
            </div>
        )        
    );

    return { DOM: dom$ };
}

run(main, { DOM: makeDOMDriver('#app') });
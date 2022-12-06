import './rxjs-adapter';
import { jsx } from 'snabbdom'
import { makeDOMDriver } from '@cycle/dom';
import { makeHTTPDriver } from '@cycle/http'
import { run } from '@cycle/run'
import { map, startWith, switchAll, withLatestFrom } from 'rxjs'


function main(sources) {
    const nombre$ = sources.DOM.select('.nombre').events('input').pipe(map(evt => evt.target.value));
    const boton$ = sources.DOM.select('.botonBuscar').events('click');

    const resultado$ = sources.HTTP.select('busquedaPorNombre').pipe(
        switchAll(),
        map(response => JSON.parse(response.text)),
        map(json => json.items.map(item => ({ 
            id: item.id,
            name: item.name,
            description: item.description,
            owner: item.owner.login,
            url: item.html_url }))),
        startWith([])
    );

    const comandoBuscar$ = boton$.pipe(withLatestFrom(nombre$), map(([_, nombre]) => nombre));

    const peticion$ = comandoBuscar$.pipe(
        map(nombre => ({
            url: 'https://api.github.com/search/repositories',
            method: 'GET',
            query: {
                q: `${nombre} in:name`,
                per_page: 5
            },
            category: 'busquedaPorNombre'
        }))
    );

    const dom$ = resultado$.pipe(
        map(repos =>
            <div>
                Búsqueda por nombre:
                <input attrs={{ type: 'text' }} class={{ nombre: true }} />
                <button class={{ botonBuscar: true }}>Buscar</button>
                <table class={{ resultados: true }}>
                    <tr>
                        <th>Usuario</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                    </tr>
                    {
                        repos.map(repo =>
                            <tr>
                                <td>{repo.owner}</td>
                                <td><a attrs={{ href: repo.url }}>{repo.name}</a></td>
                                <td>{repo.description}</td>
                            </tr>
                        )
                    }
                </table>
            </div>
        )
    );
    return { DOM: dom$, HTTP: peticion$ }
}

const drivers = {
    DOM: makeDOMDriver('#app'),
    HTTP: makeHTTPDriver()
}

run(main, drivers);


import { map } from 'rxjs';

var contextCounter = 0;

export default function isolate(Component, context) {
    if (context === undefined) {
        context = `ctx${contextCounter++}`;
    }
    return sources => {
        const sourcesRestricted = {...sources, DOM: sources.DOM.select(`#${context}`)};
        const sinks = Component(sourcesRestricted);
        const newDOM$ = sinks.DOM.pipe(map(dom => {
            dom.sel += `#${context}`;
            return dom;
        }));
        return {...sinks, DOM: newDOM$};
    }
}


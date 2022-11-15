
export function crearObservador(id) {
    return {
        next: x => { console.log(`Observador ${id} recibe ${x}`); },
        complete: () => { console.log(`Observador ${id} recibe finalización`); },
        error: () => { console.log(`Observador ${id} recibe error ${e.message}`); }
    };
}
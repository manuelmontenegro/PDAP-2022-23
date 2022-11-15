import { of } from 'rxjs';

const obs$ = of(10, 20, 30);

const observador = {
    next: function(x) {
        console.log(`He recibido el valor ${x}`);
    },
    complete: function() {
        console.log(`Ya no recibo más eventos`);
    },
    error: function(err) {
        console.log(`Se ha producido un error: ${err.message}`);
    }
    
}
obs$.subscribe(observador);
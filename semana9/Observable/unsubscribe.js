import { interval } from 'rxjs';

const obs$ = interval(1000);

const suscripcion = obs$.subscribe((x) => {
    console.log(`Recibido: ${x}`);
    if (x >= 3) {
        console.log('Cancelando suscripción');
        suscripcion.unsubscribe();
    }
});
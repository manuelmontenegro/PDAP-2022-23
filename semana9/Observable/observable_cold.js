import { Observable, of } from 'rxjs';

const obs$ = new Observable(s => {
    console.log('Generando: 10');
    s.next(10);
    console.log('Generando: 20');
    s.next(20);
    console.log('Notificando fin de secuencia');
    s.complete();
});

obs$.subscribe(x => console.log(`Observador 1 recibe: ${x}`));
console.log('---');
obs$.subscribe(x => console.log(`Observador 2 recibe: ${x}`));
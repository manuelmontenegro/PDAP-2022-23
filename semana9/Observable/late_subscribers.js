import { Subject } from 'rxjs'
import { crearObservador } from './crear_observador.js';

const suj$ = new Subject();
suj$.next(10);
suj$.next(20);
suj$.subscribe(crearObservador('C'));
suj$.next(30);
suj$.complete();

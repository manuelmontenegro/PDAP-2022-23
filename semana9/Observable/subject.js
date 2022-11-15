import { Subject } from 'rxjs';
import { crearObservador } from './crear_observador.js';

const suj$ = new Subject();

suj$.subscribe(crearObservador('A'));
suj$.subscribe(crearObservador('B'));

suj$.next(10);
suj$.next(20);
suj$.next(30);
suj$.complete();

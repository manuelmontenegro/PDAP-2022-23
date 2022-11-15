import { BehaviorSubject } from 'rxjs';
import { crearObservador } from './crear_observador.js';

const suj$ = new BehaviorSubject(0);    // 0 es el valor inicial
suj$.subscribe(crearObservador('A'));
suj$.next(10);
suj$.subscribe(crearObservador('C'));
suj$.complete();

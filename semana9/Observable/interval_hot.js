import { interval, share } from 'rxjs';

const obs$ = interval(1000).pipe(share());

obs$.subscribe(x => console.log(`Suscriptor 1 recibe ${x}`));

setTimeout(() => {
    obs$.subscribe(x => console.log(`Suscriptor 2 recibe ${x}`));
}, 3500);
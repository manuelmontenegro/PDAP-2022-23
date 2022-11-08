
import { fromEvent, map, filter, take, scan, zip } from 'rxjs';

const area = document.getElementById('areaClicks');


const coord$ = fromEvent(area, 'click').pipe(
    map(evt => ({x: evt.offsetX, y: evt.offsetY})),
    filter(({x, y}) => x < area.clientWidth / 2)
);

const cont$ = coord$.pipe(scan((ac, _) => ac + 1, 0));

zip(coord$, cont$).pipe(
        take(10)
    ).subscribe(([{x, y}, cont]) => {
        console.log(`Pulsación en (${x}, ${y}) / contador = ${cont}`);
    });


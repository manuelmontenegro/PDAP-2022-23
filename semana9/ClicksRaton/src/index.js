
import { fromEvent, map, filter, take } from 'rxjs';

const area = document.getElementById('areaClicks');

const click$ = fromEvent(area, 'click').pipe(
    map(evt => ({x: evt.offsetX, y: evt.offsetY})),
    filter(({x, y}) => x < area.clientWidth / 2),
    take(10)
);

click$.subscribe(({x, y}) => console.log(`Click en (${x},${y})`));

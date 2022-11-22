import { setAdapt } from '@cycle/run/lib/adapt';
import { Subject } from 'rxjs'


setAdapt(xstr => {
    const result = new Subject();
    xstr.addListener({
        next: i => result.next(i),
        error: err => result.error(err),
        complete: () => result.complete(),
    });
    return result;
});
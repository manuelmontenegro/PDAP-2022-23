import fs from 'fs';
import readline from 'readline';
import { of, Observable, endWith, concatMap, mergeMap, scan, reduce } from 'rxjs';


function fromFile(filename) {
    return new Observable((subscriber) => {
        const rl = readline.createInterface({
            input: fs.createReadStream(filename),
            crlfDelay: Infinity
        });
        
        rl.on('line', (l) => {
            subscriber.next(l);            
        });

        rl.on('close', () => {
            subscriber.complete();
        });        
    });
}

of('ejemplo1.txt', 'ejemplo2.txt', 'ejemplo3.txt').pipe(
    mergeMap(f => fromFile(f)),
    reduce((ac, _) => ac + 1, 0)
).subscribe(numL => console.log(`Número de líneas total: ${numL}`));

    
    

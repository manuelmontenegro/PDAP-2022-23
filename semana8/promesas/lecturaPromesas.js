import fs from 'node:fs/promises'

const p = fs.readFile('ejemplo.txt', { encoding: 'utf8' });
console.log('El programa sigue...')

p.then(str => {
    console.log("Leído fichero:")
    console.log(str);
});
p.catch(error => {
    console.log(`Ha ocurrido un error: ${error.message}`);
});
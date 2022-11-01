import fs from 'node:fs/promises'

function numLineas(fich) {
    return fs.readFile(fich, {encoding: 'utf8'})
             .then(str => str.split(/\n\r|\n|\r/).length);
}

numLineas('ejemplo.txt').then(n => console.log(`Tiene ${n} líneas`));


const ps = [numLineas('fich1.txt'),
            numLineas('fich2.txt'),
            numLineas('fich3.txt')]

Promise.all(ps)
        .then(([n1, n2, n3]) => console.log(`Entre los tres ficheros hay ${n1 + n2 + n3} líneas`))
        .catch(err => console.log(`Error: ${err.message}`));
import fs from 'node:fs/promises'

fs.readFile('ejemplo.txt', {encoding: 'utf8'})
    .then(str => str.split(/\n\r|\n|\r/).length)
    .then(num => fs.writeFile('resultado.txt', 
                              `Número de líneas: ${num}`))
    .then(() => console.log('Fichero escrito correctamente'))
    .catch(err => console.log(`Error: ${err.message}`));

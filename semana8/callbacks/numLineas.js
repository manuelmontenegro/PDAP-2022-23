import fs from "node:fs"

function numLineas(fich, callback) {
    fs.readFile(fich, {encoding: 'utf8'}, (err, result) => {
        if (err) {
            callback(err);
        } else {
            const numL = result.split(/\n\r|\n|\r/).length;
            callback(null, numL);            
        }
    })
}

numLineas('ejemplo.txt', (err, n) => {
    if (err) {
        console.log('Error al leer el fichero');
    } else {
        console.log(`Número de líneas: ${n}`);
    }
})
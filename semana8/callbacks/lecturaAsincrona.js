import fs from "node:fs"

console.log("Antes de readFile");
fs.readFile("ejemplo.txt", 
            { encoding: "utf-8" },
            (err, contenido) => {
                if (err) {
                    console.log("Se ha producido un error:");
                    console.log(err.message);
                } else {
                    console.log("Fichero leído correctamente:");
                    console.log(contenido);
                }        
            }
);                
console.log("Después de readFile");   
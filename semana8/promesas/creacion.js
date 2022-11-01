
Promise.resolve(5).then(n => console.log(n));   // → 5

Promise.reject(new Error('Ups!'))
       .catch(err => console.log(err.message)); // → Ups!

// La siguiente promesa se cumple transcurrido un segundo, devolviendo
// la cadena "OK":
const p = new Promise((resolve, reject) => {
    setTimeout(() => resolve('OK'), 1000)
});

// Imprimimos el valor devuelto cuando la promesa finalice:
p.then(str => console.log(str));
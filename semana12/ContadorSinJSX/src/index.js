import React from 'react'
import { createRoot } from 'react-dom/client'

const valorContador = 3;

const contadorDOM = React.createElement('div', null, 
    React.createElement('button', null, '+'),
    React.createElement('div', { className: 'contador' }, valorContador.toString()),
    React.createElement('button', null, '-')
);

const root = createRoot(document.getElementById('app'))
root.render(contadorDOM);
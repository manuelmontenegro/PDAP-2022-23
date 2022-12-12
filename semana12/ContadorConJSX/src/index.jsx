import React from 'react'
import { createRoot } from 'react-dom/client'

const valorContador = 3;

const contadorDOM = 
    <div>
        <button>+</button>
        <div className="contador">{valorContador}</div>
        <button>-</button>
    </div>

const root = createRoot(document.getElementById('app'))
root.render(contadorDOM);
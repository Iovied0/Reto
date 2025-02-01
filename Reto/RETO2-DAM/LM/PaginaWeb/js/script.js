/*Index*/

// Detecta si hay un error en la URL y muestra un mensaje
window.onload = function() {   
    const parametrosUrl = new URLSearchParams(window.location.search);    // Obtiene los parámetros de la URL (lo que aparece después del '?')
    
    if (parametrosUrl.has('errorea') && parametrosUrl.get('errorea') === '1') {    // Comprobar si está escrito 'errorea' y si su valor es '1'
        document.getElementById('mensajeError').style.display = 'block';    // Si la condición es verdadera, mostramos el mensaje de error (cambiar su estilo para hacerlo visible)
    } else {
        document.getElementById('mensajeError').style.display = 'none';    // Si no se cumple la condición, ocultar el mensaje de error (asegurando que esté invisible)
    }
};

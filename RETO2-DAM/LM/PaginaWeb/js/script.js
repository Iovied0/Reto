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

// Calcula automaticamente los dias del viaje
function calcularDias() {
    let fechaInicio = document.getElementById("fechaInicio").value;
    let fechaFin = document.getElementById("fechaFin").value;
    let campoDias = document.getElementById("dias");

    if (fechaInicio && fechaFin) {
        let inicio = new Date(fechaInicio);
        let fin = new Date(fechaFin);

        let diferencia = (fin - inicio) / (1000 * 60 * 60 * 24);

        if (diferencia >= 0) {
            campoDias.value = diferencia;
        } else {
            campoDias.value = "";
            alert("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }
    }
}

// Aparece o desaparece la lista de los registros dependiendo del boton que elijas
document.addEventListener("DOMContentLoaded", function () {
    const tipoServicioRadios = document.querySelectorAll('input[name="tipoServicio"]');
    
    const seccionHospedaje = document.getElementById("seccionHospedaje");
    const seccionVuelo = document.getElementById("seccionVuelo");
    const seccionOtros = document.getElementById("seccionOtros");

    function mostrarOpciones() {
        const tipoServicioSeleccionado = document.querySelector('input[name="tipoServicio"]:checked').value;

        // Ocultar todas las secciones
        seccionHospedaje.style.display = "none";
        seccionVuelo.style.display = "none";
        seccionOtros.style.display = "none";

        // Mostrar solo la sección correspondiente
        if (tipoServicioSeleccionado === "hospedaje") {
            seccionHospedaje.style.display = "block";
        } else if (tipoServicioSeleccionado === "vuelo") {
            seccionVuelo.style.display = "block";
        } else if (tipoServicioSeleccionado === "otros") {
            seccionOtros.style.display = "block";
        }
    }

    // Agregar eventos a los radio buttons
    tipoServicioRadios.forEach(radio => {
        radio.addEventListener("change", mostrarOpciones);
    });

    // Llamar a la función al cargar la página por si ya hay algo seleccionado
    mostrarOpciones();
});


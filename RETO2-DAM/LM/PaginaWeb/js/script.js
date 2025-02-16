/*Index*/

// Detecta si hay un error en la URL y muestra un mensaje
window.onload = function () {
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

    // Ocultar todas las secciones al cargar la página
    seccionHospedaje.style.display = "none";
    seccionVuelo.style.display = "none";
    seccionOtros.style.display = "none";

    function mostrarOpciones() {
        const tipoServicioSeleccionado = document.querySelector('input[name="tipoServicio"]:checked');

        // Ocultar todas las secciones
        seccionHospedaje.style.display = "none";
        seccionVuelo.style.display = "none";
        seccionOtros.style.display = "none";

        // Verificar si hay un tipo de servicio seleccionado antes de mostrar
        if (tipoServicioSeleccionado) {
            if (tipoServicioSeleccionado.value === "hospedaje") {
                seccionHospedaje.style.display = "block";
            } else if (tipoServicioSeleccionado.value === "vuelo") {
                seccionVuelo.style.display = "flex"; // Se muestra solo al hacer clic en la opción
            } else if (tipoServicioSeleccionado.value === "otros") {
                seccionOtros.style.display = "block";
            }
        }
    }

    // Agregar eventos a los radio buttons
    tipoServicioRadios.forEach(radio => {
        radio.addEventListener("change", mostrarOpciones);
    });

    // Asegurar que al cargar la página no se muestre nada si no hay selección
    mostrarOpciones();
});

//Botones de ida y vuelta
document.addEventListener("DOMContentLoaded", function () {
    const btnIda = document.getElementById("btnIda");
    const btnIdaVuelta = document.getElementById("btnIdaVuelta");
    const formularioIda = document.getElementById("formularioIda");
    const formularioVuelta = document.getElementById("formularioVuelta");

    // Ocultar formularios al cargar la página
    formularioIda.classList.add("ocultar");
    formularioVuelta.classList.add("ocultar");

    btnIda.addEventListener("click", function () {
        formularioIda.classList.remove("ocultar");
        formularioVuelta.classList.add("ocultar");
    });

    btnIdaVuelta.addEventListener("click", function () {
        formularioIda.classList.remove("ocultar");
        formularioVuelta.classList.remove("ocultar");
    });
});


// evento para manejar extraer código del aeropuerto usando substring()
document.addEventListener("DOMContentLoaded", function () {
    const aeropuertoSelect = document.getElementById("aeropuerto");

    if (aeropuertoSelect) {
        aeropuertoSelect.addEventListener("change", function () {
            const opcionSeleccionada = aeropuertoSelect.options[aeropuertoSelect.selectedIndex].text;

            // Extrae solo el código del aeropuerto (asumiendo que está antes del " - ")
            const codigoAeropuerto = opcionSeleccionada.substring(0, opcionSeleccionada.indexOf(" - "));

            console.log("Código seleccionado:", codigoAeropuerto); // Para verificar en consola
        });
    }
});




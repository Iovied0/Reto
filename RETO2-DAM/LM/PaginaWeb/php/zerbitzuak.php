<?php
// Conectar a la base de datos
$conn = new mysqli("localhost:3307", "root", "", "reto2_g2_dam1");
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Formulario para registrar servicios en un viaje">
    <title>Registrar Servicio</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="background">
        <div class="cajaCentro">
            <h1>Registrar Servicio</h1>
            <form action="/registrar_servicio" method="POST">
                <label for="viaje">Seleccionar viaje:</label>
                <select id="viaje" name="viaje" required>
                    <option value="">--Seleccionar--</option>
                    <?php
                    $sql = "SELECT id, nombreViaje FROM viaje";
                    $result = $conn->query($sql);
                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<option value='" . $row['id'] . "'>" . $row['nombreViaje'] . "</option>";
                        }
                    }
                    ?>
                </select>

                <label>¿Qué servicio desea registrar?</label>
                <div class="opcionServicio">
                    <label for="vuelo">Vuelo</label>
                    <input type="radio" id="vuelo" name="tipoServicio" value="vuelo" required>

                    <label for="hospedaje">Hospedaje</label>
                    <input type="radio" id="hospedaje" name="tipoServicio" value="hospedaje">

                    <label for="otros">Otros</label>
                    <input type="radio" id="otros" name="tipoServicio" value="otros">
                </div>

                <!-- Sección de Hospedaje -->
                <div id="seccionHospedaje">
                    <label for="hotel">Nombre del hotel:</label>
                    <input type="text" id="hotel" name="hotel">
                    
                    <label for="ciudad">Ciudad:</label>
                    <input type="text" id="ciudad" name="ciudad">
                    
                    <label for="precio">Precio (€):</label>
                    <input type="number" id="precio" name="precio">
                    
                    <label for="entrada">Fecha de entrada:</label>
                    <input type="date" id="entrada" name="entrada">
                    
                    <label for="salida">Fecha de salida:</label>
                    <input type="date" id="salida" name="salida">
                    
                    <label for="tipoHabitacion">Tipo de habitación:</label>
                    <select id="tipoHabitacion" name="tipoHabitacion">
                        <option value="">--Seleccionar--</option>
                        <option value="individual">Individual</option>
                        <option value="doble">Doble</option>
                        <option value="suite">Suite</option>
                    </select>
                </div>

                <!-- Sección de Vuelo -->
                <div id="seccionVuelo">
                    <div class="column">
                        <label for="tipoVuelo">Tipo de vuelo:</label>
                        <select id="tipoVuelo" name="tipoVuelo">
                            <option value="ida">Ida</option>
                            <option value="ida_vuelta">Ida y vuelta</option>
                        </select>
                        
                        <label for="origen">Aeropuerto de origen:</label>
                        <input type="text" id="origen" name="origen">
                        
                        <label for="destino">Aeropuerto de destino:</label>
                        <input type="text" id="destino" name="destino">
                        
                        <label for="codigoVuelo">Código del vuelo:</label>
                        <input type="text" id="codigoVuelo" name="codigoVuelo">
                        
                        <label for="aerolinea">Aerolínea:</label>
                        <input type="text" id="aerolinea" name="aerolinea">
                    </div>
                    <div class="column">
                        <label for="precioVuelo">Precio (€):</label>
                        <input type="number" id="precioVuelo" name="precioVuelo">
                        
                        <label for="fechaVuelo">Fecha del vuelo:</label>
                        <input type="date" id="fechaVuelo" name="fechaVuelo">
                        
                        <label for="horaVuelo">Hora del vuelo:</label>
                        <input type="time" id="horaVuelo" name="horaVuelo">
                        
                        <label for="duracionVuelo">Duración (horas):</label>
                        <input type="number" id="duracionVuelo" name="duracionVuelo">
                    </div>
                </div>
                <!-- Sección de Otros Servicios -->
                <div id="seccionOtros">
                    <label for="nombreServicio">Nombre del servicio:</label>
                    <input type="text" id="nombreServicio" name="nombreServicio">
                    
                    <label for="fechaServicio">Fecha:</label>
                    <input type="date" id="fechaServicio" name="fechaServicio">
                    
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion"></textarea>
                    
                    <label for="precioServicio">Precio (€):</label>
                    <input type="number" id="precioServicio" name="precioServicio">
                </div>

                <button type="submit">Guardar Servicio</button>
            </form>

            <footer>
                <ul>
                    <li><a href="#">Sobre nosotros</a></li>
                    <li><a href="#">Política de privacidad</a></li>
                    <li><a href="#">Contacto</a></li>
                </ul>
            </footer>
        </div>
    </div>

    <script src="../js/script.js"></script>
</body>
</html>

<?php
$conn->close();
?>

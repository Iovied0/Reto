<?php
// Conectar a la base de datos
$conn = new mysqli("localhost:3307", "root", "", "reto2_g2_dam1");
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $errores = [];  //inicializa array errores  para capturar posibles errores

    // Validar CodigoVuelo
    if (empty($_POST["codigoVuelo"])) {
        $errores[] = "El código del vuelo es obligatorio.";
    } elseif (!preg_match("/^[A-Za-z]{2}[0-9]{4}$/", $_POST["codigoVuelo"])) {
        $errores[] = "El código debe contener exactamente 2 letras seguidas de 4 números.";
    }

    // Validar nombre
    if (empty($_POST["nombre"])) {
        $errores[] = "El nombre del viaje es obligatorio.";
    } elseif (!preg_match("/^[a-zA-ZÀ-ÿ\\s]{3,50}$/", $_POST["nombre"])) {
        $errores[] = "El nombre solo puede contener letras y espacios (mínimo 3 caracteres).";
    }
    // Validar fechas
    $fechaInicio = $_POST["entrada"];
    $fechaFin = $_POST["salida"];
    $fechaActual = date("Y-m-d");

    if (empty($entrada) || empty($salida)) {
        $errores[] = "Ambas fechas son obligatorias.";
    } elseif ($entrada < $fechaActual) {
        $errores[] = "La fecha de inicio no puede ser anterior a la fecha actual.";
    } elseif ($entrada > $salida) {
        $errores[] = "La fecha de inicio no puede ser posterior a la fecha de finalizacion.";
    } elseif ((strtotime($salida) - strtotime($entrada)) / 86400 < 1) {
        $errores[] = "La fecha de finalizacion debe ser al menos 1 dia despues de la fecha de inicio.";
        $errores[] = "El numero de dias debe ser positivo";
    }

    // Validar descripcion y servicios excluidos (Si los valores estan vacios, los convierte en una cadena vacia (''))
    $descripcion = htmlspecialchars($_POST["descripcion"] ?? '');

    // Si hay errores, almacenarlos en sesion y redirigir de nuevo al formulario
    if (!empty($errores)) {
        $_SESSION['errores'] = $errores;
        header("Location: bidaia.php");
        exit();
    }

    $SESSION["datos_validos"] = [
        "hotel" => $_POST["hotel"],
        "ciudad" => $ciudad,
        "precio" => $_POST["precio"],
        "entrada" => $_POST["entrada"],
        "salida" => $_POST["salida"],
        "tipoHabitacion" => $_POST["tipoHabitacion"],
        "origen" => $_POST["origen"],
        "destino" => $_POST["destino"],
        "codigoVuelo" => $_POST["codigoVuelo"],
        "precioVuelo" => $_POST["precioVuelo"],
        "fechaVuelo" => $_POST["fechaVuelo"],
        "horaVuelo" => $_POST["horaVuelo"],
        "duracionVuelo" => $_POST["duracionVuelo"],
        "nombreServicio" => $_POST["nombreServicio"],
        "fechaServicio" => $_POST["fechaServicio"],
        "descripcion" => $_POST["descripcion"],
        "precioServicio" => $_POST["precioServicio"],
    ];


    header("Location: zerbitzuak_gorde.php");
    exit();

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
            <form action="zerbitzuak.php" method="POST">
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
                    <input type="number" id="precio" name="precio" min="0">

                    <label for="entrada">Fecha de entrada:</label>
                    <input type="date" id="entrada" name="entrada">

                    <label for="salida">Fecha de salida:</label>
                    <input type="date" id="salida" name="salida">

                    <label for="tipoHabitacion">Tipo de habitación:</label>
                    <select id="tipoHabitacion" name="tipoHabitacion">
                        <option value="">--Seleccionar--</option>
                        <?php
                        $sql = "SELECT codigo, descripcion FROM tipodormitorio";
                        $result = $conn->query($sql);
                        if ($result->num_rows > 0) {
                            while ($row = $result->fetch_assoc()) {
                                echo "<option value='" . $row['codigo'] . "'>" . $row['descripcion'] . "</option>";
                            }
                        }
                        ?>
                    </select>
                </div>

                <!-- Sección de Vuelo -->
                <div id="seccionVuelo">
                    <div id="opcionesVuelo">
                        <button type="button" id="btnIda">Ida</button>
                        <button type="button" id="btnIdaVuelta">Ida y Vuelta</button>
                    </div>

                    <!-- Formulario de Ida -->
                    <div id="formularioIda" class="column">
                        <h3>Detalles del Vuelo (Ida)</h3>

                        <label for="origen">Aeropuerto de origen:</label>
                        <select id="origen" name="origen" required>
                            <option value="">--Seleccionar--</option>
                            <?php
                            $sql = "SELECT aeropuerto.codigo FROM aeropuerto";
                            $result = $conn->query($sql);
                            while ($row = $result->fetch_assoc()) {
                                echo "<option value='" . $row['codigo'] . "'>" . $row['codigo'] . "</option>";
                            }
                            ?>
                        </select>

                        <label for="destino">Aeropuerto de destino:</label>
                        <select id="destino" name="destino" required>
                            <option value="">--Seleccionar--</option>
                            <?php
                            $result = $conn->query($sql);
                            while ($row = $result->fetch_assoc()) {
                                echo "<option value='" . $row['codigo'] . "'>" . $row['codigo'] . "</option>";
                            }
                            ?>
                        </select>

                        <label for="codigoVuelo">Código del vuelo:</label>
                        <input type="text" id="codigoVuelo" name="codigoVuelo">

                        <label for="aerolinea">Aerolínea:</label>
                        <select id="aerolinea" name="aerolinea">
                            <?php
                            $sql = "SELECT codigo, nombre FROM aerolineas";
                            $result = $conn->query($sql);

                            if ($result->num_rows > 0) {
                                while ($row = $result->fetch_assoc()) {
                                    echo "<option value='" . $row['codigo'] . "'>" . $row['nombre'] . "</option>";
                                }
                            }
                            ?>
                        </select>

                        <label for="precioVuelo">Precio (€):</label>
                        <input type="number" id="precioVuelo" name="precioVuelo" min="0">

                        <label for="fechaIda">Fecha de ida:</label>
                        <input type="date" id="fechaIda" name="fecha_ida">

                        <label for="horaVuelo">Hora del vuelo:</label>
                        <input type="time" id="horaVuelo" name="horaVuelo">

                        <label for="duracionVuelo">Duración (horas):</label>
                        <input type="number" id="duracionVuelo" name="duracionVuelo" min="0">
                    </div>

                    <!-- Formulario de Vuelta (Solo si es ida y vuelta) -->
                    <div id="formularioVuelta" class="column">
                        <h3>Detalles del Vuelo (Vuelta)</h3>

                        <label for="origenVuelta">Aeropuerto de origen:</label>
                        <select id="origenVuelta" name="origen_vuelta">
                            <option value="">--Seleccionar--</option>
                            <?php
                            $sql = "SELECT aeropuerto.codigo FROM aeropuerto";
                            $result = $conn->query($sql);
                            while ($row = $result->fetch_assoc()) {
                                echo "<option value='" . $row['codigo'] . "'>" . $row['codigo'] . "</option>";
                            }
                            ?>
                        </select>

                        <label for="destinoVuelta">Aeropuerto de destino:</label>
                        <select id="destinoVuelta" name="destino_vuelta">
                            <option value="">--Seleccionar--</option>
                            <?php
                            $result = $conn->query($sql);
                            while ($row = $result->fetch_assoc()) {
                                echo "<option value='" . $row['codigo'] . "'>" . $row['codigo'] . "</option>";
                            }
                            ?>
                        </select>

                        <label for="codigoVueloVuelta">Código del vuelo (vuelta):</label>
                        <input type="text" id="codigoVueloVuelta" name="codigoVueloVuelta">

                        <label for="aerolinea">Aerolínea:</label>
                        <select id="aerolinea" name="aerolinea">
                            <?php
                            $sql = "SELECT codigo, nombre FROM aerolineas";
                            $result = $conn->query($sql);

                            if ($result->num_rows > 0) {
                                while ($row = $result->fetch_assoc()) {
                                    echo "<option value='" . $row['codigo'] . "'>" . $row['nombre'] . "</option>";
                                }
                            }
                            ?>
                        </select>

                        <label for="fechaVuelta">Fecha de vuelta:</label>
                        <input type="date" id="fechaVuelta" name="fecha_vuelta">

                        <label for="horaVuelta">Hora del vuelo (vuelta):</label>
                        <input type="time" id="horaVuelta" name="horaVuelta">

                        <label for="duracionVuelta">Duración (horas):</label>
                        <input type="number" id="duracionVuelta" name="duracionVuelta" min="0">
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
                    <input type="number" id="precioServicio" name="precioServicio" min="0">
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
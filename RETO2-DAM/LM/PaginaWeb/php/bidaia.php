<?php
session_start();
// Conectar a la base de datos
    $conn = new mysqli("localhost:3307", "root", "", "reto2_g2_dam1");
    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    }

    $errores = $_SESSION['errores'] ?? [];
    unset($_SESSION['errores']); // Limpiar errores despues de mostrarlos

// Validar que los datos han sido enviados
if ($_SERVER["REQUEST_METHOD"] == "POST") {    
        $errores = [];  //inicializa array errores  para capturar posibles errores

        // Validar nombre
        if (empty($_POST["nombre"])) {
            $errores[] = "El nombre del viaje es obligatorio.";
        } elseif (!preg_match("/^[a-zA-ZÀ-ÿ\\s]{3,50}$/", $_POST["nombre"])) {
            $errores[] = "El nombre solo puede contener letras y espacios (mínimo 3 caracteres).";
        }

        // Validar fechas
        $fechaInicio = $_POST["fechaInicio"];
        $fechaFin = $_POST["fechaFin"];
        $fechaActual = date("Y-m-d");

        if (empty($fechaInicio) || empty($fechaFin)) {
            $errores[] = "Ambas fechas son obligatorias.";
        } elseif ($fechaInicio < $fechaActual) {
            $errores[] = "La fecha de inicio no puede ser anterior a la fecha actual.";
        } elseif ($fechaInicio > $fechaFin) {
            $errores[] = "La fecha de inicio no puede ser posterior a la fecha de finalizacion.";
        }elseif ((strtotime($fechaFin) - strtotime($fechaInicio)) / 86400 < 1) {
            $errores[] = "La fecha de finalizacion debe ser al menos 1 dia despues de la fecha de inicio.";
            $errores[] = "El numero de dias debe ser positivo";
        }

    // Validar numero de dias
        $dias = (strtotime($fechaFin) - strtotime($fechaInicio)) / 86400;

    // Validar descripcion y servicios excluidos (Si los valores estan vacios, los convierte en una cadena vacia (''))
        $descripcion = htmlspecialchars($_POST["descripcion"] ?? '');
        $serviciosExcluidos = htmlspecialchars($_POST["serviciosExcluidos"] ?? '');

    // Si hay errores, almacenarlos en sesion y redirigir de nuevo al formulario
        if (!empty($errores)) {
            $_SESSION['errores'] = $errores;
            header("Location: bidaia.php");
            exit();
        }

    // Si NO hay errores,Guardar datos en sesion para usarlos en bidaia_gorde.php
        $_SESSION["datos_validos"] = [
            "nombre" => $_POST["nombre"],
            "descripcion" => $descripcion,
            "fechaInicio" => $_POST["fechaInicio"],
            "fechaFin" => $_POST["fechaFin"],
            "serviciosExcluidos" => $serviciosExcluidos,
            "tipoViaje" => $_POST["tipoViaje"],
            "territorio" => $_POST["territorio"]
        ];

        header("Location: bidaia_gorde.php");
        exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Formulario para registrar viajes">
    <title>Registrar Viaje</title>
    <link rel="stylesheet" href="../css/styles.css">

<!--Calcular numero dias en el formulario -->
 <script>
    function calcularDias() {
        let fechaInicio = document.getElementById("fechaInicio").value;
        let fechaFin = document.getElementById("fechaFin").value;
        let diasInput = document.getElementById("dias");

        if (fechaInicio && fechaFin) {
            let inicio = new Date(fechaInicio);
            let fin = new Date(fechaFin);
            let diferencia = (fin - inicio) / (1000 * 60 * 60 * 24);

            if (diferencia < 0) {
                alert("La fecha de inicio no puede ser posterior a la fecha de finalizacion.");
                diasInput.value = "";
            } else {
                diasInput.value = diferencia;
            }
        }
    }
</script>
</head>
<body>
    <div class="background">
        <div class="cajaCentro">
            <h1>Registrar Viaje</h1>

                <!--mustrar errores en el formulario (lista en rojo)-->
                <?php if (!empty($errores)): ?>
                    <ul style="color: red;">
                        <?php foreach ($errores as $error): ?>
                            <li><?php echo $error; ?></li>
                        <?php endforeach; ?>
                    </ul>
                <?php endif; ?>

            <form action="bidaia.php" method="POST"> <!-- AHORA ENVIA A bidaia.php -->
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" placeholder="Nombre del viaje" required>

                <label for="tipoViaje">Tipo de viaje:</label>
                <select id="tipoViaje" name="tipoViaje" required>
                    <option value="">--Seleccionar--</option>
                    <?php
                    $sql = "SELECT codigo, descripcion FROM tipoviaje";
                    $result = $conn->query($sql);
                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<option value='" . $row['codigo'] . "'>" . $row['descripcion'] . "</option>";
                        }
                    }
                    ?>
                </select>

                    <label for="fechaInicio">Fecha de inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" required onchange="calcularDias()">

                    <label for="fechaFin">Fecha de finalización:</label>
                    <input type="date" id="fechaFin" name="fechaFin" required onchange="calcularDias()">

                    <label for="dias">Días:</label>
                    <input type="number" id="dias" name="dias" placeholder="Número de días" readonly>

                    
                    <label for="territorio">País:</label>
                    <select id="territorio" name="territorio" required>
                        <option value="">--Seleccionar--</option>
                        <?php
                        $sql = "SELECT codigo, nombre FROM pais";
                        $result = $conn->query($sql);
                        if ($result->num_rows > 0) {
                            while ($row = $result->fetch_assoc()) {
                                echo "<option value='" . $row['codigo'] . "'>" . $row['nombre'] . "</option>";
                            }
                        }
                        ?>
                </select>

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" rows="4"></textarea>

                <label>Servicios excluidos:</label>
                <textarea id="serviciosExcluidos" name="serviciosExcluidos" rows="4"></textarea>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </div>
</body>
</html>
<?php $conn->close(); ?>

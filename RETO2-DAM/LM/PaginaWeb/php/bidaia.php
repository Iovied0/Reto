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
    <meta name="description" content="Formulario para registrar viajes">
    <title>Registrar Viaje</title>
    <link rel="stylesheet" href="../css/styles.css">
    <script src="../js/script.js"></script>
</head>
<body>
    <div class="background">
        <div class="cajaCentro">
            <h1>Registrar Viaje</h1>
            <form action="../php/bidaia_gorde.php" method="POST">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" placeholder="Nombre del viaje" required>

                <label for="tipoViaje">Tipo de viaje:</label>
                <select id="tipoViaje" name="tipoViaje" required>
                    <option value="">--Seleccionar--</option>
                    <?php
                    $sql = "SELECT descripcion FROM tipoviaje";
                    $result = $conn->query($sql);
                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<option value='" . $row['id'] . "'>" . $row['descripcion'] . "</option>";
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
                    $sql = "SELECT nombre FROM pais";
                    $result = $conn->query($sql);
                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<option value='" . $row['id'] . "'>" . $row['nombre'] . "</option>";
                        }
                    }
                    ?>
                </select>

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" placeholder="Descripción del viaje" rows="4"></textarea>

                <label>Servicios excluidos:</label>
                <textarea id="serviciosExcluidos" name="serviciosExcluidos" placeholder="Servicios no incluidos" rows="4"></textarea>

                <button type="submit">Guardar</button>
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
</body>
</html>

<?php
$conn->close();
?>

<?php
session_start();

// Datos de conexión a la base de datos
$servername = "localhost:3307";
$username = "root";
$password = "";
$dbname = "reto2_g2_dam1";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar la conexión
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Verificar si los datos del formulario están definidos
if (!isset($_POST['izena']) || !isset($_POST['mota'])) {
    $_SESSION['mensaje'] = "Faltan datos en el formulario.";
    header("Location: ../php/menua.php");
    exit();
}

// Obtener los datos del formulario
$izena = $_POST['izena'];
$mota = $_POST['mota'];

// Preparar la consulta SQL según el tipo de servicio
if ($mota == 'vuelo') {
    // Verificar que los datos necesarios para el vuelo estén definidos
    if (!isset($_POST['tipo_vuelo'], $_POST['codigo_vuelo'], $_POST['fecha'], $_POST['horaVuelo'], $_POST['duracion'], $_POST['aerolinea'], $_POST['origen'], $_POST['destino'], $_POST['id_viaje'], $_POST['precioVuelo'], $_POST['codigoVuelo'])) {
        $_SESSION['mensaje'] = "Faltan datos para registrar el vuelo.";
        header("Location: ../php/menua.php");
        exit();
    }

    // Preparar la consulta SQL
    $sql = "INSERT INTO vuelo (tipo_vuelo, codigoVuelo, fecha, horaVuelo, duracion, aerolinea, origen, destino, id_viaje, precioVuelo, codigo_asociado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    if (!$stmt) {
        $_SESSION['mensaje'] = "Error al preparar la consulta: " . $conn->error;
        header("Location: ../php/menua.php");
        exit();
    }

    // Asignar los valores a los parámetros
    $stmt->bind_param(
        "ssssisssiis",
        $_POST['tipo_vuelo'],
        $_POST['codigo_vuelo'],
        $_POST['fecha'],
        $_POST['horaVuelo'],
        $_POST['duracion'],
        $_POST['aerolinea'],
        $_POST['origen'],
        $_POST['destino'],
        $_POST['id_viaje'],
        $_POST['precioVuelo'],
        $_POST['codigo_asociado']
    );

} elseif ($mota == 'alojamiento') {
    // Verificar que los datos necesarios para el alojamiento estén definidos
    if (!isset($_POST['hotel'], $_POST['ciudad'], $_POST['entrada'], $_POST['salida'], $_POST['precio'], $_POST['id_viaje'], $_POST['id_ciudad'], $_POST['tipoHabitacion'])) {
        $_SESSION['mensaje'] = "Faltan datos para registrar el alojamiento.";
        header("Location: ../php/menua.php");
        exit();
    }

    // Preparar la consulta SQL
    $sql = "INSERT INTO alojamiento (hotel, entrada, salida, precio, id_viaje, id_ciudad, tipoHabitacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    if (!$stmt) {
        $_SESSION['mensaje'] = "Error al preparar la consulta: " . $conn->error;
        header("Location: ../php/menua.php");
        exit();
    }

    // Asignar los valores a los parámetros
    $stmt->bind_param(
        "sssiiis",
        $_POST['hotel'],
        $_POST['entrada'],
        $_POST['salida'],
        $_POST['precio'],
        $_POST['id_viaje'],
        $_POST['id_ciudad'],
        $_POST['tipoHabitacion']
    );

} elseif ($mota == 'actividad') {
    // Verificar que los datos necesarios para la actividad estén definidos
    if (!isset($_POST['nombreServicio'], $_POST['descripcion'], $_POST['fechaServicio'], $_POST['precioServicio'], $_POST['id_viaje'])) {
        $_SESSION['mensaje'] = "Faltan datos para registrar la actividad.";
        header("Location: ../php/menua.php");
        exit();
    }

    // Preparar la consulta SQL
    $sql = "INSERT INTO actividad (nombreServicio, descripcion, fechaServicio, precioServicio, id_viaje) VALUES (?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    if (!$stmt) {
        $_SESSION['mensaje'] = "Error al preparar la consulta: " . $conn->error;
        header("Location: ../php/menua.php");
        exit();
    }

    // Asignar los valores a los parámetros
    $stmt->bind_param(
        "sssdi",
        $_POST['nombreServicio'],
        $_POST['descripcion'],
        $_POST['fechaServicio'],
        $_POST['precioServicio'],
        $_POST['id_viaje']
    );

} else {
    $_SESSION['mensaje'] = "Tipo de servicio no válido.";
    header("Location: ../php/menua.php");
    exit();
}

// Ejecutar la consulta y verificar el resultado
if ($stmt->execute()) {
    $_SESSION['mensaje'] = "El servicio se ha registrado correctamente!";
} else {
    $_SESSION['mensaje'] = "Error al registrar el servicio: " . $stmt->error;
}

// Cerrar la conexión y la declaración
$stmt->close();
$conn->close();

// Redirigir al menú
header("Location: ../php/menua.php");
exit();
?>
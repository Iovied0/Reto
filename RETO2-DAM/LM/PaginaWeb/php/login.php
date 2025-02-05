<?php
session_start();

// Datos de conexión a la base de datos
$servername = "localhost:3307";
$username = "root";
$password = "";
$dbname = "reto2_g2_dam1";

// Conectar con la base de datos
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die("Fallo en la conexión: " . $conn->connect_error);
}

// Recuperar datos del formulario
$nombre = $_POST['nombre'];
$contraseña = $_POST['contraseña'];

// Consulta SQL para verificar la agencia y obtener el logo
$sql = "SELECT nombre, logo FROM agencia WHERE nombre = '$nombre' AND Contraseña = '$contraseña'";
$result = $conn->query($sql);

// Verificar si el usuario existe
if ($result->num_rows > 0) {
    $row = $result->fetch_assoc(); // Obtener los datos en una sola llamada
    $_SESSION['agencia'] = $row['nombre'];
    $_SESSION['logo'] = $row['logo']; // Guardamos el logo en la sesión

    header("Location: ../php/menua.php");
    exit();
} else {
    header("Location: ../html/index.html?errorea=1");
    exit();
}

// Cerrar conexión
$conn->close();
?>

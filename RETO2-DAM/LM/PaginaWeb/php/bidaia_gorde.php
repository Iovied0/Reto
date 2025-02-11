<?php
session_start();

//Verificar que el usuario tenga una agencia asignada
if (!isset($_SESSION["id_agencia"]) || empty($_SESSION["id_agencia"])) {
    $_SESSION['mensaje'] = "Error: No tienes una agencia asignada. Inicia sesión nuevamente.";
    header("Location: login.php");  //redirige a login.php con un mensaje de error
    exit();
}
//Verificar que se hayan recibido datos validos
if (!isset($_SESSION["datos_validos"]) || empty($_SESSION["datos_validos"])) {
    $_SESSION['mensaje'] = "Error: No se han recibido datos válidos.";
    header("Location: bidaia.php"); //Redirige de vuelta a bidaia.php con un mensaje de error
    exit();
}
//Guarda en variables los datos almacenados en la sesion
$id_agencia = $_SESSION["id_agencia"]; //id de la agencia autenticada (login.php).
$datos = $_SESSION["datos_validos"]; //Array con los datos validados del viaje.

//Conectar a la base de datos
$servername = "localhost:3307";
$username = "root";
$password = "";
$dbname = "reto2_g2_dam1";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//Preparar la consulta SQL para insertar un viaje
$sql = "INSERT INTO viaje (nombreViaje, descViaje, inicioViaje, finViaje, servNoIncluidos, tipo_viaje, pais_destino, id_agencia) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

$stmt = $conn->prepare($sql);  //Prepara la consulta
$stmt->bind_param("sssssssi",  //bind_param() asocia valores a la consulta
    $datos["nombre"], 
    $datos["descripcion"], 
    $datos["fechaInicio"], 
    $datos["fechaFin"], 
    $datos["serviciosExcluidos"], 
    $datos["tipoViaje"], 
    $datos["territorio"], 
    $id_agencia
);

//Ejecutar la consulta y verificar el resultado
if ($stmt->execute()) {
    $_SESSION['mensaje'] = "El viaje se ha registrado correctamente!";
} else {
    $_SESSION['mensaje'] = "Error al registrar el viaje: " . $stmt->error;
}

//Cerrar la conexion
$stmt->close();
$conn->close();

//Eliminar los datos de la sesion
unset($_SESSION["datos_validos"]);

//Redirigir al menu principal
header("Location: ../php/menua.php");
exit();
?>

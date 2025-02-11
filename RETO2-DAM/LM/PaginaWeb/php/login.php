
<?php
session_start();

// Datos de conexion a la base de datos
$servername = "localhost:3307";
$username = "root";
$password = "";
$dbname = "reto2_g2_dam1";

// Conectar con la base de datos
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexion
if ($conn->connect_error) {
    die("Fallo en la conexión: " . $conn->connect_error);
}

// Recuperar datos del formulario
$nombre = $_POST['nombre'];
$contraseña = $_POST['contraseña'];

// Consulta SQL para verificar la agencia y obtener el logo
$sql = "SELECT id, nombre, logo FROM agencia WHERE nombre = ? AND Contraseña = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ss", $nombre, $contraseña);  //Vincula los valores de $nombre y $contrasena a los ? de la consulta.
$stmt->execute();
$result = $stmt->get_result();

// Verificar si el usuario existe
if ($result->num_rows > 0) {
    $row = $result->fetch_assoc(); // Obtener los datos en una sola llamada
        
    // Guardar datos en la sesion para usarlos en bidaia.php
    $_SESSION["id_agencia"] = $row["id"];  
    $_SESSION['agencia'] = $row['nombre'];
    $_SESSION['logo'] = $row['logo']; 

   
    header("Location: ../php/menua.php");
    exit();
} else {
    header("Location: ../html/index.html?errorea=1");
    exit();
}

// Cerrar conexion
$conn->close();
?>

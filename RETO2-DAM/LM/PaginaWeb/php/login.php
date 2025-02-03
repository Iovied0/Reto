<?php
// $_SESSION-ekin lan egiteko 
session_start();

// Datu baserako konexioko parametroak
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "reto2_g2_dam1";

// Konexioa egin
$conn = new mysqli($servername, $username, $password, $dbname);

// Konexioa egiaztatu
if ($conn->connect_error) {
    die("Fallo en la conexión: " . $conn->connect_error);
}

// Formularioko datuak berreskuratu
$nombre = $_POST['nombre'];
$contraseña = $_POST['contraseña'];

// SQL kontsulta
$sql = "SELECT * FROM agencia WHERE nombre = '$nombre' AND Contraseña = '$contraseña'";

// Kontsulta exekutatu
$result = $conn->query($sql);

// Erabiltzailea existitzen den egiaztatu
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $_SESSION['agencia'] = $row['nombre']; // Agentziaren izena gordeko dugu, gero goiburuan erakusteko 
    }
    // Aurkitu badugu erregistroa, logeatzen gara eta aurrera jo: menua.php orrira eraman
    header("Location: ../php/menua.php");
    exit();
} else {
    // Ez bada existitzen, errore mezua (url bidez resolbitzen dut)
    header("Location: ../html/index.html?errorea=1");
    exit();
}

// Konexioa itxi
if ($conn) {
    $conn->close();
}

?>
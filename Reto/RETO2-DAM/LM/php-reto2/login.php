<?php
// $_SESSION-ekin lan egiteko 
session_start();

// Datu baserako konexioko parametroak
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "db_turismo";

// Konexioa egin
$conn = new mysqli($servername, $username, $password, $dbname);

// Konexioa egiaztatu
if ($conn->connect_error) {
    die("Fallo en la conexión: " . $conn->connect_error);
}

// Formularioko datuak berreskuratu
$erabiltzailea = $_POST['erabiltzailea'];
$pasahitza = $_POST['pasahitza'];

// SQL kontsulta
$sql = "SELECT * FROM agentzia WHERE Erabiltzailea = '$erabiltzailea' AND Pasahitza = '$pasahitza'";

// Kontsulta exekutatu
$result = $conn->query($sql);

// Erabiltzailea existitzen den egiaztatu
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $_SESSION['agentzia'] = $row['Izena']; // Agentziaren izena gordeko dugu, gero goiburuan erakusteko 
    }
    // Aurkitu badugu erregistroa, logeatzen gara eta aurrera jo: menua.php orrira eraman
    header("Location: menua.php");
    exit();
} else {
    // Ez bada existitzen, errore mezua (url bidez resolbitzen dut)
    header("Location: ../index.html?errorea=1");
    exit();
}

// Konexioa itxi
$conn->close();
?>

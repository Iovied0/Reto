<?php
session_start();

$servername = "localhost";
$username = "root";        
$password = "";         
$dbname = "reto2_g2_dam1";    

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Formularioko datuak
$izena = $_POST['izena'];
$mota = $_POST['mota'];
<!-- etc etc -->


$sql = "INSERT INTO XXXXXXXXXXXXXXXXXXXXXX
        VALUES ('$XXXX', '$XXXX', '$XXXX', '$XXXX', '$XXXX', '$XXXX','$XXXX')";

// Kontsulta exekutatu
if ($conn->query($sql) === TRUE) {
    // Mezua baldin eta ondo erregistratu den datu basean
    $_SESSION['mensaje'] = "Bidaia ondo erregistratu da";
} else {
    // Mezua baldin eta txarto erregistratu den datu basean
    $_SESSION['mensaje'] = "Errorea suertatu da: " . $conn->error;
}

// KOnexioa itxi
$conn->close();

// Menua.php-ra itzuli (bai ondo ala txarto egin den erregistroa.. edo agian txarto egiten bada mantentzen gara orri berean?)
header("Location: ../php/menua.php");
exit();
?>


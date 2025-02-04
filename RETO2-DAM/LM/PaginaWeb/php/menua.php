<?php
session_start();
// Bidaia erregistratzen denean, ondo edo txarto sortu bada, _Session -en gorde dugu (bidaia_gorde.php)
if (isset($_SESSION['mensaje'])) {
    // Javascripten erabili ahal izateko
    $mensaje = $_SESSION['mensaje'];
    // Mezua ezabatzeko sesioarekin batera
    unset($_SESSION['mensaje']);
} else {
    $mensaje = '';
}
?>

<!DOCTYPE html>
<html lang="eu">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/styles.css">
    <title>Menua</title>
    <script>
        // Bidaia ondo erregistratu bada datu basean, berriro kargatzen da orri hau, eta mezu bat agertzen da horrela izan dela adieraziz
        <?php if ($mensaje != ''): ?>
            window.onload = function () {
                alert("<?php echo $mensaje; ?>");
            };
        <?php endif; ?>
    </script>
</head>

<body>
    <div class="background">
            <div class="cajaCentro">
                <main>
                    <h2>Bienvenido a la página principal de <?php echo $_SESSION['agencia']; ?>!</h2>
                    <p>Desde aquí puedes gestionar los viajes y otros datos importantes.</p>
                    <nav>
                        <a href="../php/bidaia.php"><button class=botonRegistro>Registrar Viaje</button></a>
                        <a href="../php/zerbitzuak.php"><button class=botonRegistro>Registrar Servicios</button></a>
                    </nav>
                </main>
            </div>
                <footer>
                    <ul>
                        <li><a href="#">Sobre nosotros</a></li>
                        <li><a href="#">Política de privacidad</a></li>
                        <li><a href="#">Contacto</a></li>
                    </ul>
                </footer>        
    </div>
</body>

</html>
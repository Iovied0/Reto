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
    <meta name="viewport" content="XXXXXXX">
    <link rel="stylesheet" href="XXXXXXX.css">
    <title>Menua</title>
    <script>
        // Bidaia ondo erregistratu bada datu basean, berriro kargatzen da orri hau, eta mezu bat agertzen da horrela izan dela adieraziz
        <?php if ($mensaje != ''): ?>
            window.onload = function() {
                alert("<?php echo $mensaje; ?>");
            };
        <?php endif; ?>
    </script>
  </head>
<body>
    <header>
        <h4><?php echo $_SESSION['agentzia']; ?></h4>

    </header>
    <main>
    <nav>
        <ul>
            <li><a href="XXXXXXX.php">XXXXXXX XXXXXXX</a></li>
            <li><a href="XXXXXXX.php">XXXXXXX XXXXXXX</a></li>
          </ul>
        </nav> 
        
    </main>

    XXXXXXX
    XXXXXXX
    XXXXXXX
    XXXXXXX
    XXXXXXX


    <footer>
         XXXXXXX
    </footer>
</body>
</html>
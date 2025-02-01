<?php
session_start();
?>


<!DOCTYPE html>
<html lang="eu">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Formulario para registrar viajes">
    <title>Registrar Viaje</title>
    <link rel="stylesheet" href="../css/styles.css">
    <script src=".../script.js"></script>
</head>
<body>
<div class="background">
    <header>
        <h4><?php echo $_SESSION['agentzia']; ?></h4>
    </header>
    <main>
    <nav>
        <ul>
            <li><a href="../php/menua.php">Menu</a></li>
        </ul>
    </nav> 
    <form id="XXXXXXX" XXXXXXX>
            <label for="XXXXXXX">Aukeratu bidaia</label>
            <select id="XXXXXXX" name="XXXXXXX" required>
                <option value="">--Aukeratu--</option>
                <?php
                //DATU BASETIK
                $conn = new mysqli("localhost", "root", "", "db_turismo");
                if ($conn->connect_error) {
                    die("Connection failed: " . $conn->connect_error);
                }
                $sql = "SELECT id, izena FROM bidaia";
                $result = $conn->query($sql);
                if ($result->num_rows > 0) {
                    while ($row = $result->fetch_assoc()) {
                        echo "<option value='" . $row['id'] . "'>" . $row['izena'] . "</option>";
                    }
                }
                $conn->close();
                ?>
            </select><br><br>
            
            <!-- Mas formulario -->
            XXXXXXX
            XXXXXXX
            XXXXXXX
            XXXXXXX
            XXXXXXX

            <!-- SECCIONES -->
            <div id="XXXXXXX" style="display:none;">
                <h5>Vuelo</h5>
                
            </div>

            <div id="ostatua-section" style="display:none;">
                <h5>Ostatua</h5>
                XXXXXXX
                XXXXXXX
                XXXXXXX
                XXXXXXX
                XXXXXXX

                <select id="logela_mota" name="logela_mota" required>
                    <option value="">--Aukeratu --</option>
                    <?php
                    // DATU BASETIK
                    $conn = new mysqli("localhost", "root", "", "db_turismo");
                    if ($conn->connect_error) {
                        die("Connection failed: " . $conn->connect_error);
                    }
                    $sql = "SELECT IdLogelaMota, LogelaMota FROM logelamota"; 
                    $result = $conn->query($sql);
                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<option value='" . $row['IdLogelaMota'] . "'>" . $row['LogelaMota'] . "</option>";
                        }
                    }
                    $conn->close();
                    ?>
                </select><br><br>

            </div>

            <div id="XXXXXXX" style="display:none;">
                <h5>Beste Batzuk</h5>


            </div>
<br>
            <input type="submit" value="Gorde Zerbitzua">
        </form>

        XXXXXXX
        XXXXXXX
        XXXXXXX
          
    </main>
    <footer>
        <ul>
            <li><a href="#">Sobre nosotros</a></li>
            <li><a href="#">Política de privacidad</a></li>
            <li><a href="#">Contacto</a></li>
        </ul>
    </footer>
<div>
</body>
</html>
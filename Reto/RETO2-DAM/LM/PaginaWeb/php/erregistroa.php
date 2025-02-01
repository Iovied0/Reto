<?php
session_start();
?>

<!DOCTYPE html>
<html lang="eu">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="XXXX">
    <link rel="stylesheet" href="XXXX.css">
    <script src="XXXX.js"></script>
    <title>Menua</title>
  </head>
<body>
    <header>
        <h4><?php echo $_SESSION['agentzia']; ?></h4>

    </header>
    <main>
    <nav>
        <ul>
            <li><a href="XXXX.php">XXXX erregistratu</a></li>
            <li><a href="XXXX.php">XXXX</a></li>
          </ul>
        </nav>
   
        <form XXXX">
            <label for="XXXX">Izena:</label>
            <input type="text" id="XXXX" name="XXXX" required><br><br>

            <label for="XXXX">XXXX:</label>
            <select id="XXXX" name="XXXX" required>

            <option value="">--Aukeratu--</option>
                <!-- DATU BASETIK -->
                <?php
                $conn = new mysqli("localhost", "root", "", "reto2_g2_dam1");
                if ($conn->connect_error) {
                    die("Conexión fallida: " . $conn->connect_error);
                }
                $sql = "SELECT IdBidaiaMota, Mota FROM bidaiamotak";
                $result = $conn->query($sql);
                if ($result->num_rows > 0) {
                    while ($row = $result->fetch_assoc()) {
                        echo "<option value='" . $row['IdBidaiaMota'] . "'>" . $row['Mota'] . "</option>";
                    }
                }
                $conn->close();
                ?>
            </select>            
            <br><br>

            XXXX
            XXXX
            <!-- Continua el formulario -->
            XXXX
            XXXX

            <label for="XXXX">XXXX:</label>
            <select id="XXXX" name="XXXX" required>
                <option value="">--Aukeratu--</option>
                <!-- DATU BASETIK -->
                <?php
                $conn = new mysqli("localhost", "root", "", "db_turismo");
                if ($conn->connect_error) {
                    die("Conexión fallida: " . $conn->connect_error);
                }
                $sql = "SELECT id, izena FROM herrialdeak";
                $result = $conn->query($sql);
                if ($result->num_rows > 0) {
                    while ($row = $result->fetch_assoc()) {
                        echo "<option value='" . $row['id'] . "'>" . $row['izena'] . "</option>";
                    }
                }
                $conn->close();
                ?>
            </select><br><br>

            XXXX
            <!-- Continua el formulario -->
            XXXX
            XXXX

            <input type="submit" value="Gorde">
        </form>
          
    </main>
    <footer>
    </footer>
</body>
</html>
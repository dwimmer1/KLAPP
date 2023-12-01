<?php

$servername = "xserv";
$port = 3306;
$username = "klapp";
$password = "superklapp";
$dbname = "klapp";
// Daten aus der Anfrage verarbeiten und in die Datenbank einfügen

$conn = new mysqli($servername, $username, $password, $dbname);

// Daten aus der POST-Anfrage verarbeiten und in die Datenbank einfügen
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST["username"];
    $description = $_POST["description"];
    $risetime = $_POST["risetime"];
    $descenttime = $_POST["descenttime"];
    $status = $_POST["status"];
    $startingpoint = $_POST["startingpoint"];
    $federalstate = $_POST["federalstate"];
    $difficulty = $_POST["difficulty"];
    $datecreated = $_POST["datecreated"];

    $sql = "INSERT INTO Klettersteig (name, description, risetime, descenttime, status, startingpoint, federalstate, difficulty, datecreated)
            VALUES ('$username', '$description', '$risetime', '$descenttime', '$status', '$startingpoint', '$federalstate', '$difficulty', '$datecreated')";

    if ($conn->query($sql) === TRUE) {
        echo "Daten erfolgreich eingefügt";
    } else {
        echo "Fehler beim Einfügen der Daten: " . $conn->error;
    }
}

$conn->close();
?>
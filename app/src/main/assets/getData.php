<?php
//Dy
/*
ALTE DATEN FÜR XAMPP
servername = "xserv";
port = 3306;
username = "klapp";
password = "superklapp";
dbname = "klapp";
*/

$servername = "d03f5d74";
$username = "d03f5d74";
$password = "superklapp";
$dbname = "d03f5d74";



// Verbindung herstellen
$conn = new mysqli($servername, $username, $password, $dbname);

// Prüfen der Verbindung
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// SQL-Abfrage ausführen
$sql = "SELECT * FROM Account";
$result = $conn->query($sql);

// Daten in ein Array konvertieren
$data = array();
while ($row = $result->fetch_assoc()) {
    $data[] = $row;
}

// Daten als JSON ausgeben
header('Content-Type: application/json');
echo json_encode($data);

// Verbindung schließen
$conn->close();

?>

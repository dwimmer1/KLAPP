<?php
$servername = "xserv";
$port = 3306;
$username = "klapp";
$password = "superklapp";
$dbname = "klapp";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $sender = $_POST["name"];
    $message = $_POST["message"];
    $timestamp = $_POST["timestamp"];

    $sql = "INSERT INTO Chat (name, message, timestamp) VALUES ('$sender', '$message', $timestamp)";
    $result = $conn->query($sql);

    if ($result) {
        echo "Nachricht erfolgreich gespeichert";
    } else {
        echo "Fehler beim Speichern der Nachricht: " . $conn->error;
    }
}

$conn->close();
?>

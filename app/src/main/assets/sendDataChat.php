<?php

$servername = "d03f5d74";
$username = "d03f5d74";
$password = "superklapp";
$dbname = "d03f5d74";

//sendet Daten an chat

$conn = new mysqli($servername, $username, $password, $dbname, $port);

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $sender = $_POST["name"];
    $message = $_POST["message"];
    $timestamp = $_POST["timestamp"];

    $sql = "INSERT INTO Chat (name, message, timestamp) VALUES ('$sender', '$message', STR_TO_DATE('$timestamp', '%Y-%m-%d %H:%i:%s'))";
    $result = $conn->query($sql);

    if ($result) {
        echo "Nachricht erfolgreich gespeichert";
    } else {
        echo "Fehler beim Speichern der Nachricht: " . $conn->error;
    }
}

$conn->close();
?>

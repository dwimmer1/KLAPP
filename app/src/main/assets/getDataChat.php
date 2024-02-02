<?php

$servername = "d03f5d74";
$username = "d03f5d74";
$password = "superklapp";
$dbname = "d03f5d74";


$conn = new mysqli($servername, $username, $password, $dbname, $port);

$sql = "SELECT name, message, timestamp FROM Chat;
$result = $conn->query($sql);

$messages = array();

while ($row = $result->fetch_assoc()) {
    $messages[] = $row;
}

echo json_encode($messages);

$conn->close();
?>

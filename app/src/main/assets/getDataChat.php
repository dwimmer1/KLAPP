<?php
$servername = "xserv";
$port = 3306;
$username = "klapp";
$password = "superklapp";
$dbname = "klapp";

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

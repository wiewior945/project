<?php
require_once("HomeController.php");
$user = $_GET['username'];
$password = $_GET['password'];
$result = HomeController::authoriseMobile($user, $password);

if($result == true) {
    echo "Kurwa";
} else {
    echo "Nie kurwa.";
}

?>
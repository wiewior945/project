<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$email=$_POST['username'];
$password=$_POST['password'];
$sql = "INSERT INTO users (email, Password) VALUES ('$email', '$password')";

mysqli_query($con, $sql);

?>
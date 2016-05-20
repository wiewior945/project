<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$username=$_POST['username'];
$password=$_POST['password'];
$sql = "INSERT INTO user (Username, Password) VALUES ('$username', '$password')";

mysqli_query($con, $sql);

?>
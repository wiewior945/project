<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$password=$_POST['password'];
$result = mysqli_query($con, "SELECT mobile_pass FROM users WHERE mobile_pass='$password'") or die('Query failed: ' . mysql_error());

$row = mysqli_fetch_array($result);
$data = $row[0];

echo $data;
?>
<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');

$email=$_POST['username'];
$result = mysqli_query($con, "SELECT email FROM users WHERE email='$email'") or die('Query failed: ' . mysql_error());

$row = mysqli_fetch_array($result);
$data = $row[0];

echo $data;
?>
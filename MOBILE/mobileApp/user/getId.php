<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$email=$_POST['username'];
$result = mysqli_query($con, "SELECT id FROM users WHERE email='$email'") or die('Query failed: ' . mysqli_error());

$row = mysqli_fetch_array($result);
$data = $row[0];

echo $data;
?>
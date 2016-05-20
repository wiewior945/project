<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$username=$_POST['username'];
$result = mysqli_query($con, "SELECT id FROM user WHERE Username='$username'") or die('Query failed: ' . mysqli_error());

$row = mysqli_fetch_array($result);
$data = $row[0];

echo $data;
?>
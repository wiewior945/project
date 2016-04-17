<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$username=$_POST['username'];
$result = mysqli_query($con, "SELECT Username FROM user WHERE Username='$username'") or die('Query failed: ' . mysql_error());

$row = mysqli_fetch_array($result);
$data = $row[0];

echo $data;
?>
<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$userId=$_POST['userId'];
$name=$_POST['name'];
$result = mysqli_query($con, "SELECT id FROM notes WHERE userId='$userId' AND name='$name'") or die('Query failed: ' . mysql_error());

$row = mysqli_fetch_array($result);
$data = $row[0];

echo $data;
?>
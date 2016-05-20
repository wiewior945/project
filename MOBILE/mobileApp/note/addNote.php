<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$userId=$_POST['userId'];
$name=$_POST['name'];
$note=$_POST['note'];

$sql = "INSERT INTO `notes` (userId, name, note) VALUES ('$userId', '$name', '$note')";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

?>
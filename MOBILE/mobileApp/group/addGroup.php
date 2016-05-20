<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$groupname=$_POST['groupname'];
$adminID=$_POST['adminID'];
$isPublic=$_POST['isPublic'];
$password=$_POST['password'];

$sql = "INSERT INTO `groups` (name, adminID, isPublic, password) VALUES ('$groupname', '$adminID', '$isPublic', '$password')";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

?>
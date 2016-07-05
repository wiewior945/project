<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$groupname=$_POST['groupname'];
$adminID=$_POST['adminID'];
$isPublic=$_POST['isPublic'];
$password=$_POST['password'];
$isPrivate=$_POST['isPrivate'];

$sql = "INSERT INTO `groups` (name, adminID, isPublic, password, isPrivate) VALUES ('$groupname', '$adminID', '$isPublic', '$password', '$isPrivate')";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

?>
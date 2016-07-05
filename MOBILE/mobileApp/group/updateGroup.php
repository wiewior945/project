<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$id=$_POST['groupId'];
$name=$_POST['name'];
$password=$_POST['password'];
$isPublic=$_POST['isPublic'];

$sql = "UPDATE groups SET name='$name', password='$password', isPublic='$isPublic' WHERE id='$id'";
mysqli_query($con, $sql) or die(mysqli_error($con));

?>
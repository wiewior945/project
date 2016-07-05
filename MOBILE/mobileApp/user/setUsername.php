<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$id=$_POST['id'];
$email=$_POST['username'];
$sql = "UPDATE users SET email='$email' WHERE id='$id'";

mysqli_query($con, $sql) or die(mysqli_error($con));

?>
<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$id=$_POST['id'];
$username=$_POST['username'];
$sql = "UPDATE user SET Username='$username' WHERE id='$id'";

mysqli_query($con, $sql) or die(mysqli_error($con));

?>
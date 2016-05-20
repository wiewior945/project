<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$id=$_POST['id'];

$sql = "SELECT name FROM `groups` WHERE(id='$id')";

$result = mysqli_query($con, $sql) or die('query error'. mysqli_error($con));
$row = mysqli_fetch_array($result);
echo $row[0];

?>
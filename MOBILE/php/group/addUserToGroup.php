<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$groupId=$_POST['groupId'];
$userId=$_POST['userId'];

$sql = "INSERT INTO `usergroup` (groupID, userID) VALUES ('$groupId', '$userId')";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));
?>
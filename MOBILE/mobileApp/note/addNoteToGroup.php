<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');


$noteId=$_POST['noteId'];
$groupId=$_POST['groupId'];

$sql = "INSERT INTO `notegroup` (noteId, groupId) VALUES ('$noteId', '$groupId')";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

?>
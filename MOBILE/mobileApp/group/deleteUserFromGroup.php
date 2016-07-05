<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');

$userId=$_POST['userId'];
$groupId=$_POST['groupId'];

$sql = "DELETE FROM usergroup WHERE userID='$userId' AND groupID='$groupId'";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

?>
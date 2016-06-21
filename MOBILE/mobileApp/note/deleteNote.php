<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$noteId=$_POST['noteId'];
$result = mysqli_query($con, "DELETE FROM notes WHERE id='$noteId'") or die('Query failed: ' . mysql_error());
?>
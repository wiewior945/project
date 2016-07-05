<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');

$note=$_POST['note'];
$name=$_POST['name'];
$noteId=$_POST['noteId'];

$sql = "UPDATE notes SET name='$name', note='$note' WHERE id=$noteId";

mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

?>
<?php
$link = mysql_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysql_select_db('aplikacja') or die('Could not select database');

$password=$_POST['password'];
$result = mysql_query("SELECT password FROM user WHERE Password='$password'") or die('Query failed: ' . mysql_error());

$row = mysql_fetch_array($result);
$data = $row[0];

echo $data;

mysql_free_result($result);
mysql_close($link);
?>
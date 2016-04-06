<?php
$link = mysql_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysql_select_db('aplikacja') or die('Could not select database');


$username=$_POST['username'];
$password=$_POST['password'];
$sql = "INSERT INTO user (Username, Password) VALUES ('$username', '$password')";

mysql_query($sql)

?>
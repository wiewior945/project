<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$a=$_GET['a'];
if($a==null) echo "pusto";
else echo $a;

?>
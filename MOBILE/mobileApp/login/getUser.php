<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$username=$_POST["username"];
$res = mysqli_query($con, "SELECT Username,id, privateGroup FROM user WHERE Username='$username'") or die('Query failed: ' . mysql_error());

$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('username'=>$row[0], 'id'=>$row[1], 'privateGroup'=>$row[2]));
}

echo json_encode(array("records"=>$result));
?>
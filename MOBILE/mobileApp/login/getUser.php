<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');

$email=$_POST["email"];
$res = mysqli_query($con, "SELECT email, id, privateGroup FROM users WHERE email='$email'") or die('Query failed: ' . mysqli_error($con));

$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('email'=>$row[0], 'id'=>$row[1], 'privateGroup'=>$row[2]));
}

echo json_encode(array("records"=>$result));
?>
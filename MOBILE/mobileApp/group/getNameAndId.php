<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error($con));
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$groupname=$_POST['groupname'];
$res = mysqli_query($con, "SELECT name,id FROM `groups` WHERE name='$groupname'") or die('Query failed: ' . mysqli_error($con));

$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('groupname'=>$row[0], 'id'=>$row[1]));
}

echo json_encode(array("records"=>$result));
?>
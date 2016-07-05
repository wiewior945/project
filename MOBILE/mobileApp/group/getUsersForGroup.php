<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$groupId=$_POST['groupId'];

$sql = "SELECT userID FROM usergroup WHERE(groupID='$groupId')";

$res = mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('userId'=>$row[0]));
}

echo json_encode(array("records"=>$result));
?>
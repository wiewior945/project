<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error($con));
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$userID=$_POST['userID'];

$sql = "SELECT groupID FROM usergroup WHERE(userID='$userID')";

$res = mysqli_query($con, $sql) or die('query error'. mysqli_error($con));
$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('groupID'=>$row[0]));
}

echo json_encode(array("records"=>$result));
?>
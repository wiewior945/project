<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysqli_error($con));
mysqli_select_db($con, 'notesdb') or die('Could not select database');


$groupId=$_POST['groupId'];

$sql = "SELECT id, name FROM notes LEFT JOIN notegroup ON notegroup.noteId = notes.id WHERE notegroup.groupId = '$groupId'";

$res = mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('noteId'=>$row[0], 'name'=>$row[1]));
}

echo json_encode(array("records"=>$result));
?>
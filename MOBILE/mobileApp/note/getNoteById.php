<?php
$con = mysqli_connect('localhost', 'root') or die('Could not connect: ' . mysql_error());
mysqli_select_db($con, 'aplikacja') or die('Could not select database');

$noteId=$_POST['noteId'];
$sql = "SELECT name, note, userId FROM notes WHERE id=$noteId";

$res = mysqli_query($con, $sql) or die('query error'. mysqli_error($con));

$result=array();
while($row=mysqli_fetch_array($res)){
array_push($result, array('name'=>$row[0], 'note'=>$row[1], 'userId'=>$row[2]));
}

echo json_encode(array("records"=>$result));
?>
?>
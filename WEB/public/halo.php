<?php

$user = $_GET['username'];
$password = $_GET['password'];
$url = '192.168.1.7:80/project/WEB/public/mobilelogin?username='.$user."&password".$password;
$data = array('username' => 'gleks@pwc.com', 'password' => 'test123');

$options = array(
    'http' => array(
        'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
        'method'  => 'POST',
        'content' => http_build_query($data)
    )
);
$context  = stream_context_create($options);
$result = file_get_contents($url, false, $context);
if ($result === FALSE) { /* Handle error */ }

echo var_dump($result);
//if(Auth::attempt(['email' => $user, 'password' => $password])) {
  //  echo Auth::user()->id;
//} else {
//    echo "Złe hasło.";
//}
?>
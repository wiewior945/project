<?php
    define('DB_HOST', '127.0.0.1');
    define('DB_USER', 'root');
    define('DB_PASS', '');
    define('DB_NAME', 'notesdbb');

    $mySQL = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME) or die("Aplikacja nie mogła połączyć się z bazą danych.");

?>
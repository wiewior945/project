<?php
    include("config.php");
?>

<!DOCTYPE html>
<html>
<head>
    <title>College Notes - Po co ci zeszyt?!</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="Content/bootstrap.min.css" rel="stylesheet" />
    <link href="Content/font-awesome.min.css" rel="stylesheet" />
    <link href="Content/style.css" rel="stylesheet" />
</head>
<body>
    <div class="container-fluid">

        <form method="post" action="process.php">

            <input style="display: block;" type="text" name="user" />
            <input style="display: block;" type="password" name="pass" />

        </form>

    </div>
</body>
</html>
<?php 

namespace App\Http\Controllers;

use DB;
use Auth;
use Hash;
use Illuminate\Http\Request;

class APIController extends Controller {
    
    public function authoriseMobile() {
        $user = $_GET["username"];
        $password = $_GET["password"];
        $users = DB::table("users")
                  ->where("email", $user)
                  ->get();
        if($users != null) {
            if($users[0]->password == Hash::make($password)) {
                echo "id=". $user->id;
            } else {
                echo "Błedne hasło";
            }
        } else {
            echo "Brak użytkownika";
        }
    }
}
?>
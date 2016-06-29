<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use Illuminate\Http\Request;
use DB;
use Auth;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('home');
    }

    public function authoriseMobile() {
        $user = $_POST['username'];
        $password = Hash::make($_POST['pasword']);

        if(Auth::atempt(['email' => $user, 'password' => $password])) {
            return true;
        }
    }
    function editUserForm() {
        $user = DB::table('users')
                    ->where('id', Auth::user()->id)
                    ->get();
        return view('home.edit')->with('user', $user);
    }
    function editUser() {
        if($_POST['password'] != "") {
            DB::table('users')
                ->where('id', Auth::user()->id)
                ->update(array('name' => $_POST['name'], 'password' => Hash::make($_POST['password']), 'updated_at' => date("Y-m-d H:i:s")));    
        } else {
            DB::table('users')
                ->where('id', Auth::user()->id)
                ->update(array('name' => $_POST['name'], 'updated_at' => date("Y-m-d H:i:s")));
        }
        return redirect()->intended('/');
    }
}

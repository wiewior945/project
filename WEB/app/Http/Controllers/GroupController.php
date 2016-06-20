<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

class GroupController extends Controller
{
    function userGroups() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        return View();
    }
}

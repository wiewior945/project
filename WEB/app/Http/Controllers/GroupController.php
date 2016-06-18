<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use DB;
use App\Http\Requests;
use Auth;

class GroupController extends Controller
{
    function myGroups() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        $groups = DB::table('groups')
                    ->join('usergroup', 'groups.id', '=', 'usergroup.groupID')
                    ->where('usergroup.userID', Auth::user()->id)
                    ->get();
        return View('group.list')->with('groups', $groups);
    }
    function createGroupForm() {
        return view('group.groupForm'); 
    }
    function createGroup() {
        $isPublic = $_POST['isPublic'] == true ? 1: 0;

        DB::table('groups')
          ->insert([[
                'name' => $_POST['groupName'],
                'adminID' => Auth::user()->id,
                'isPublic' => $isPublic,
                'password' => $_POST['password'],
          ]]);
        DB::table('userGroup')
            ->insert([[
                'userID' => Auth::user()->id,
                'groupID' => DB::getPdo()->lastInsertId()
            ]]);
        return redirect()->intended('mygroups');
    }
    function groupNotes() {
        $groupNotes = DB::table('')
        ->where('groupID', $_GET['groupID'])
        ->get();

        return view()->with('groupNotes', $groupNotes);
    }
    function addUserToGroup() {

    }
}

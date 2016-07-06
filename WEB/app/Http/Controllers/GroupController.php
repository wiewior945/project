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
                      ->distinct()
                      ->join('usergroup', 'groups.id', '=', 'usergroup.groupID')
                      ->where('usergroup.userID', Auth::user()->id)
                      ->where('usergroup.groupID', "!=", Auth::user()->privateGroup)
                      ->get();
        return View('group.list')->with('groups', $groups);
    }
    function createGroupForm() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        return view('group.groupForm'); 
    }
    function createGroup() {
        $isPublic = isset($_POST['isPublic']) ? 1: 0;

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
    function editGroupForm() {
         if(!Auth::check()) {
            return redirect()->intended('login');
        }
         $group= DB::table('groups')->where('id', $_GET['groupID'])->get();
        return view('group.edit')->with('group', $group[0]);
    }
    function editGroup() {
        $nazwa = $_POST['nazwa'];
        $password = $_POST['password'];
        $isPublic = $_POST['isPublic'] == true ? 1 : 0;
        if($password == ""){
            DB::table('groups')
            ->where('id', $_POST['id'])
            ->update(array('name' => $nazwa, 'isPublic' => $isPublic));    
        } else {
            DB::table('groups')
                ->where('id', $_POST['id'])
                ->update(array('name' => $nazwa, 'password' => $password, 'isPublic' => $isPublic));
        }
        return redirect()->intended('/mygroups');
    }
    function groupNotes() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        $groupNotes = DB::table('notegroup')
                        ->distinct()
                        ->join('notes', 'notegroup.noteId', '=', 'notes.id')
                        ->where('groupId', $_GET['groupID'])
                        ->get();

        return View('group.noteslist')->with('groupNotes', $groupNotes);
    }
    function shareNote() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        $noteID = $_GET['noteID'];
        $groups = DB::table('groups')
                      ->distinct()
                      ->join('usergroup', 'groups.id', '=', 'usergroup.groupID')
                      ->where('usergroup.userID', Auth::user()->id)
                      ->get();
        return View('group.sharenote')->with('noteID', $noteID)->with('groups', $groups);
    }
    function saveSharingNote() {
        $noteID = $_POST['noteID'];
        $groupID = $_POST['groupID'];

        DB::table('notegroup')
            ->insert([[
                'noteId' => $noteID,
                'groupId' => $groupID
            ]]);
        return redirect()->intended("/mygroups");
    }
    function addUserToGroupForm() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        $groupID = $_GET['groupID'];
        return view("group.addUser")->with('groupID', $groupID);
    }
    function addUserToGroup() {
        $userID = DB::table('users')
                      ->where('email', $_POST['email'])
                      ->get();
        $groupID = $_POST['groupID'];
        if($userID == null) {
           return view("group.addUser")->with('groupID', $groupID)->with('error', "true");
        }
        DB::table('userGroup')
            ->insert([[
                'userID' => $userID[0]->id,
                'groupID' => $groupID
            ]]);
        return redirect()->intended('mygroups');
    }
    function searchGroup() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        if(!isset($_POST['query'])) {
            return view('group.search');
        } else {
            $query = "%". $_POST['query']. "%";
            $groups = DB::table('groups')
                          ->where('name', 'like', $query)
                          ->get();
            return view('group.search')->with('groups', $groups);
        }
    }
    function joinGroup() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        if(isset($_GET['id'])) {
            $group = DB::table('groups')
                         ->where('id', $_GET['id'])
                         ->get();
        } else if(isset($_POST['id'])) {
            $group = DB::table('groups')
                         ->where('id', $_POST['id'])
                         ->get();
        }
        if($group[0]->isPublic == 1 && !isset($_POST['password'])) {
             DB::table('userGroup')
                 ->insert([[
                    'userID' => Auth::user()->id,
                    'groupID' => $_GET['id']
                 ]]);
             return redirect()->intended('mygroups'); 
        } else if(!isset($_POST['password'])) {
            return view('group.password')->with("groupID", $_GET['id']);
        }
        if(isset($_POST['password']) && $group[0]->password == $_POST['password']) {
            DB::table('userGroup')
                ->insert([[
                    'userID' => Auth::user()->id,
                    'groupID' => $_POST['id']
                ]]);
            return redirect()->intended('mygroups'); 
        } else {
            return view('group.password')->with("groupID", $_POST['id'])->with('password', "error");
        }
    }
}
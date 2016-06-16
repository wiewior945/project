<?php

namespace App\Http\Controllers;

use DB;
use Illuminate\Http\Request;
use App\Http\Requests;
use Auth;
use App\note;

class NoteController extends Controller
{
    function addForm() {
        return view('note.create');
    }
    function createNote() {
        if(Auth::check()) {
            DB::table('notes')->insert([
                [
                    'nazwa' => $_POST['nazwa'],
                    'Contents' => $_POST['Contents'],
                    'Created' => date("d.m.Y H:i:s"),
                    'userID' => Auth::user()->id
                ]
            ]);
        }
        return redirect()->intended('/mynotes');
    }
    function editForm() {
        $note = DB::table('notes')->where('id', $_GET['noteID'])->get();
        return view('note.edit')->with('note', $note[0]);
    }
    function editNote() {
        $nazwa = $_POST['nazwa'];
        $Contents = $_POST['Contents'];
        DB::table('notes')->where('id', $_POST['id'])->update(array('nazwa' => $nazwa, 'Contents' => $Contents));
        return redirect()->intended('/mynotes');
    }
    function showMyNotes() {
        if(!Auth::Check()) {
            return redirect()->intended('login');
        }
        $notes = DB::table('notes')->where('userID', Auth::user()->id)->get();
        return view('note.list')->with("notes", $notes);
    }
    function showNote() {
        if(!Auth::check()) {
            return redirect()->intended('login');
        }
        $noteID = $_GET['noteID'];
        $note = DB::table('notes')->where('id', $noteID)->get();
        return view('note.show')->with('note', $note[0]);
    }
}

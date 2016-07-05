<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::auth();
Route::get('/home', 'HomeController@index');
Route::auth();
Route::get('/home', 'HomeController@index');
Route::get('/mobilelogin', "APIController@authoriseMobile");
Route::group(['middleware' => ['web', 'auth:api']], function () {
    Route::get('/note', 'NoteController@showNote');
    Route::get('/mynotes', 'NoteController@showMyNotes');
    Route::get('/addnote', 'NoteController@addForm');
    Route::post('/addNote', 'NoteController@createNote');
    Route::get('/editnote', 'NoteController@editForm');
    Route::post('/editNote', 'NoteController@editNote');
    //Grupy
    Route::get('/createGroupForm', 'GroupController@createGroupForm');
    Route::post('/creategroup', 'GroupController@createGroup');
    Route::get('/mygroups', 'GroupController@myGroups');
    Route::get('/group', 'GroupController@groupNotes');
    Route::get('/sharenote', 'GroupController@shareNote');
    Route::post('/sharenote', 'GroupController@saveSharingNote');
    Route::get('/adduser', 'GroupController@addUserToGroupForm');
    Route::post('/addUserToGroup', "GroupController@addUserToGroup");
    Route::get('/editGroupForm', "GroupController@editGroupForm");
    Route::post('/editGroup', "GroupController@editGroup");
    Route::get('/searchGroup', "GroupController@searchGroup");
    Route::post('/searchGroup', "GroupController@searchGroup");
    Route::get('/joinGroup', "GroupController@joinGroup");
    Route::post('/joinGroup', "GroupController@joinGroup");
    //user
    Route::get('editUser', "HomeController@editUserForm");
    Route::post('editUser', "HomeController@editUser");
});
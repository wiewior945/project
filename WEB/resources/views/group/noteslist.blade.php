@extends('layouts.app')
@section('content')
    <div class="note container">
        <p class="note-title">
            Notatki groupowe:
        </p>
        <ul class="left-column">
            <?php
                foreach ($groupNotes as $key => $value) {
                    echo '<li>';
                    echo '<a href="'. url('/note'). '?noteID='. $value->noteId. '">'. $value->nazwa. '</a>';
                    echo '</li>'; 
                }
            ?>
        </ul> 
        <div class="buttons">
            <a href="{{ url('/adduser') }}?groupID={{ $_GET['groupID'] }}" class="btn btn-default" >Dodaj użytkownika do grupy</a>
            <a href="{{ url('/createGroupForm') }}" class="btn btn-default">Utwórz nową grupę</a>
            <a href="{{ url('/editGroupForm') }}?groupID={{ $_GET['groupID'] }}" class="btn btn-default">Edytuj dane grupy</a>
        </div>
    </div>
@endsection
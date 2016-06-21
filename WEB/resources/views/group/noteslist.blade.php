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
                    echo '<a href="/note?noteID='. $value->noteId. '">'. $value->nazwa. '</a>';
                    echo '</li>'; 
                }
            ?>
        </ul>
        <a href="/createGroupForm" class="btn btn-default">Utwórz grupę</a>
    </div>
@endsection
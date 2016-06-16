@extends('layouts.app')

@section('content')
<div class="note container">
    <p class="note-title">Moje notatki</p>
    <ul class="notes-list">
        <?php
            foreach ($notes as $key => $value) {
                echo '<li>';
                echo '<a href="/note?noteID='. $value->id. '">'. $value->nazwa. '</a>';
                echo '</li>'; 
            }
        ?>
    </ul>
</div>
@endsection
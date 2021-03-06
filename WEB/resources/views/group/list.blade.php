@extends('layouts.app')
@section('content')
    <div class="note container">
        <p class="note-title">
            Grupy, do których należysz:
        </p>
        <ul class="left-column">
            <?php
                foreach ($groups as $key => $value) {
                    echo '<li>';
                    echo '<a href="'. url('/group'). '?groupID='. $value->id. '">'. $value->name. '</a>';
                    echo '</li>'; 
                }
            ?>
        </ul>
        <div class="buttons">
            <a href="{{ url('/createGroupForm') }}" class="btn btn-default">Utwórz grupę</a>
            <a href="{{ url('/searchGroup') }}" class="btn btn-default">Szukaj grupy</a>
        </div>
    </div>
@endsection
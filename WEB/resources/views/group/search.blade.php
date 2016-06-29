@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Znajdź grupę!</div>
        <div class="panel-body">
            <form action="/searchGroup" method="POST">
                {{ csrf_field() }}
                <div class="form-group">
                    <label for="query">Nazwa grupy:</label>
                    <input type="text" class="form-control" id="query" name="query" placeholder="Wpisz nazwę szukanej grupy..." required>
                </div>
                <button type="submit" class="btn btn-primary">Szukaj</button>
            </form>
            <?php
                if(isset($groups)) {
                    if(count($groups) == 0) {
                        echo "<p>Brak grupy o takiej nazwie! Spróbuj wpisać inaczej.</p>";
                    } else {
                        echo "<ul>";
                        foreach($groups as $key => $value) {
                            echo "<li><a href=\"/joinGroup?id=".$value->id."\" >".$value->name."</a></li>";
                        }
                        echo "</ul>";
                    }
                }
            ?>
        </div>
    </div>
@endsection
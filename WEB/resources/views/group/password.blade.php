@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Znajdź grupę!</div>
        <div class="panel-body">
            <form action="/joinGroup" method="POST">
                {{ csrf_field() }}
                <input type="hidden" value="{{ $groupID }}" name="id" />
                <div class="form-group">
                    <label for="password">Aby dołączyć do tej grupy musisz podać hasło:</label>
                    <input type="password" class="form-control" id="query" name="password" required>
                </div>
                <?php 
                    if(isset($password)) {
                        echo "<p class=\"error\">Niestety, podane przez ciebie hasło jest błędne.</p>";
                    }
                ?>
                <button type="submit" class="btn btn-primary">Dołącz</button>
            </form>
        </div>
    </div>
@endsection
@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Utwórz nową grupę!</div>
        <div class="panel-body">
            <form action="/addUserToGroup" method="POST" role="form">
                {{ csrf_field() }}
                <input name="groupID" type="hidden" value="{{ $groupID }}" />
                <div class="form-group">
                    <label for="email">Podaj e-mail użytkownika</label>
                    <input type="email" name="email" class="form-control" />
                </div>
                <?php
                    if(isset($error)) {
                        echo "<p class=\"error\">Użytkownik z takim adresem e-mail nie istnieje.</p>";
                    }
                ?>
                <button type="submit" class="btn btn-primary">Dodaj użytkownika</button>
            </form>
        </div>
    </div>
@endsection
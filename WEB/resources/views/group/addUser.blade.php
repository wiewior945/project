@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Utwórz nową grupę!</div>
        <div class="panel-body">
            <form action="/addUserToGroup" method="POST">
                {{ csrf_field() }}
                <input name="groupID" type="hidden" value="{{ $groupID }}" />
                <label for="email">Podaj e-mail użytkownika</label>
                <input type="email" name="email" />
                <button type="submit" class="btn btn-primary">Dodaj użytkownika</button>
            </form>
        </div>
    </div>
@endsection
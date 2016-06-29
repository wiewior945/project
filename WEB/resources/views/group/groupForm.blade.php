@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Utwórz nową grupę!</div>
        <div class="panel-body">
            <form action="/creategroup" method="POST">
                {{ csrf_field() }}
                <div class="form-group">
                    <label for="groupName">Nazwa grupy:</label>
                    <input type="text" class="form-control" id="groupName" name="groupName" placeholder="Wpisz nazwę dla swojej grupy..." required>
                </div>
                <div class="form-group">
                    <label for="password">Hasło do grupy (niewymagane):</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Podaj hasło dla grupy...">
                </div>
                <div class="form-group">
                    <label for="isPublic">Grupa publiczna?</label>
                    <input type="checkbox" name="isPublic" checked />
                </div>
                <button type="submit" class="btn btn-primary">Utwórz grupę</button>
            </form>
        </div>
    </div>
@endsection
@extends('layouts.app')

@section('content')
<form class="note container" action="{{url('/addNote')}}" method="POST">
    {!! csrf_field() !!}
    <textarea type="text" name="nazwa" class="nazwa" placeholder="Podaj nazwę swojej notatki..." required></textarea>
    <textarea class="Contents" name="Contents" placeholder="Tutaj wpisz jej treść..." required></textarea>
    <input type="submit" value="Dodaj" class="btn btn-default" />
</form>
@endsection
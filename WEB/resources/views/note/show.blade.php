@extends('layouts.app')

@section('content')
<div class="note container">
    <p class="note-title">{{$note->nazwa}}</p>
    <p class="left-column">{{$note->Contents}}</p>
    <p class="right-column"></p>
    <a href="{{ url('/editnote') }}?noteID={{ $note->id }}" class="btn btn-default">Edytuj</a>
</div>
@endsection
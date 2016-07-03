@extends('layouts.app')

@section('content')
<div class="note container">
    <p class="note-title">{{$note->nazwa}}</p>
    <div class="left-column">{!! $note->Contents !!}</div>
    <div class="buttons">
        <a href="{{ url('/editnote') }}?noteID={{ $note->id }}" class="btn btn-default">Edytuj</a>
        <a href="{{ url('/sharenote') }}?noteID={{ $note->id }}" class="btn btn-default">Udostępnij w grupie</a>
    </div>
</div>
@endsection
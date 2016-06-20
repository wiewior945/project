@extends('layouts.app')

@section('content')
<form class="note container" action="{{url('/editNote')}}" method="POST">
    {!! csrf_field() !!}
    <input type="text" name="id" value="{{ $note->id }}" hidden />
    <textarea type="text" name="nazwa" class="nazwa" required>{{ $note->nazwa }}</textarea>
    <textarea class="Contents" name="Contents" required>{{ $note->Contents }}</textarea>
    <input type="submit" value="Zapisz" class="btn btn-default" />
</form>
@endsection
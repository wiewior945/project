@extends('layouts.app')

@section('content')
<div class="note container">
    <p class="note-title">
        Witaj, {{ Auth::user->name }}
    </p>
</div>
@endsection

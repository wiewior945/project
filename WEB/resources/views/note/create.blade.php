@extends('layouts.app')

@section('content')
<form class="note container" action="{{url('/addNote')}}" method="POST">
    {!! csrf_field() !!}
    <textarea type="text" name="nazwa" class="nazwa" placeholder="Podaj nazwę swojej notatki..." required></textarea>
    <textarea id="Content" class="Contents" name="Contents" placeholder="Tutaj wpisz jej treść..." novalidate required></textarea>
    <div class="buttons">
        <input type="submit" value="Dodaj" class="btn btn-default" />
    </div>
</form>
<script>
    tinymce.init({
        selector: '#Content',
        plugins: 'code',
        toolbar: 'undo redo bold italic alignleft aligncenter alignright numlist',
        menubar: false,
    });
</script>
@endsection
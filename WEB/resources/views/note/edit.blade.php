@extends('layouts.app')

@section('content')
<form class="note container" action="{{url('/editNote')}}" method="POST">
    {!! csrf_field() !!}
    <input type="text" name="id" value="{{ $note->id }}" hidden />
    <textarea type="text" name="nazwa" class="nazwa" required>{{ $note->nazwa }}</textarea>
    <textarea id="Content" class="Contents" name="Contents" >{{ $note->Contents }}</textarea>
    <div class="buttons">
        <input type="submit" value="Zapisz" class="btn btn-default" />
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
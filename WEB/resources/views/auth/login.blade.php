@extends('layouts.app')

@section('content')
<div class="login-note container">
    <form class="login-form" role="form" method="POST" action="{{ url('/login') }}">
        {!! csrf_field() !!}
        <label class="col-md-4 control-label">Adres e-mail:</label>
        <input type="email" class="form-control" name="email" value="{{ old('email') }}" required>
        <label class="col-md-4 control-label">Hasło:</label>
        <input type="password" class="form-control" name="password">
        <input type="checkbox" name="remember" class="col-sm-1">
        <label class="pull-left">&nbsp;Zapamiętaj</label>
        <br/>
        <button type="submit" class="btn btn-primary">
            <i class="fa fa-btn fa-sign-in"></i>Zaloguj
        </button>
        <a class="btn btn-info" href="{{ url('/password/reset') }}">Zapomniałeś hasła?</a>
    </form>
</div>
@endsection

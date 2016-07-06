@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Edycja danych grupy</div>
        <div class="panel-body">
            <form action="{{ url('/editGroup') }}" method="POST">
                {{ csrf_field() }}
                <input type="hidden" name="id" value="{{ $_GET['groupID'] }}"/>
                <div class="form-group">
                    <label for="nazwa">Nazwa grupy:</label>
                    <input type="text" class="form-control" id="nazwa" name="nazwa" value="{{ $group->name }}" required>
                </div>
                <div class="form-group">
                    <label for="password">Hasło do grupy (niewymagane):</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="form-group">
                    <label for="isPublic">Grupa publiczna?</label>
                    <?php 
                        if($group->isPublic = 0) {
                            echo "<input type=\"checkbox\" name=\"isPublic\"/>";
                        } else {
                            echo "<input type=\"checkbox\" name=\"isPublic\" checked/>";
                        }
                    ?>
                </div>
                <button type="submit" class="btn btn-primary">Zapisz</button>
            </form>
        </div>
    </div>
@endsection
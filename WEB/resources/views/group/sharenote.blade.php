@extends('layouts.app')
@section('content')
    <div class="panel panel-success4 container">
        <div class="panel-heading">Utwórz nową grupę!</div>
        <div class="panel-body">
            <form action="/sharenote" method="POST">
                {{ csrf_field() }}
                <input name="noteID" type="hidden" value="{{ $noteID }}" />
                <div class="form-group">
                    <label for="groupID">Nazwa grupy:</label>
                    <select name="groupID" required>
                        <option value="">Wybierz groupę</option>
                        <?php 
                            foreach ($groups as $key => $value) {
                                echo '<option value="'. $value->id. '">'. $value->name. '</option>'; 
                            }
                        ?>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Udostępnij notatkę</button>
            </form>
        </div>
    </div>
@endsection
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        .mt-30 {
            margin-top: 30px;
        }
    </style>
  </head>
  <body>

    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 mt-30">
                <div class="panel panel-default">
                    <div class="panel-heading">Авторизация</div>
                    <div class="panel-body">

						<p>${message}</p>

                        <form data-toggle="validator" method="POST">
                            <div class="form-group">
                                <label for="login" class="control-label">Логин</label>
                                <input type="text" name="login" id="login" class="form-control" required data-error="Поле обязательно для заполнения">
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label">Пароль</label>
                                <input type="password" name="password" id="password" class="form-control" required data-error="Поле обязательно для заполнения">
                                <div class="help-block with-errors"></div>
                            </div>
                            <button type="submit" class="btn btn-primary">Войти</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
  </body>
</html>

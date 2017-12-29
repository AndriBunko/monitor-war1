<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New URL config</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form role="form" class="form-horizontal" action="/urlConfig/add" method="post">
                        <h3>New URL configuration</h3>

                        <input class="form-control form-group" type="text" name="url" placeholder="Url">
                        <input class="form-control form-group" type="number" name="timeOk" placeholder="respons time Ok">
                        <input class="form-control form-group" type="number" name="timeWarning" placeholder="respons time Warning">
                        <input class="form-control form-group" type="text" name="substring" placeholder="substring">
                        <input class="form-control form-group" type="number" name="maxResponseLength" placeholder="max response length in byte">
                        <input class="form-control form-group" type="number" name="minResponseLength" placeholder="min response length in byte">
                        <input class="form-control form-group" type="number" name="responseCode" placeholder="expected response code">
                        <input class="form-control form-group" type="number" name="repeatFrequency" placeholder="repeat frequency in second">
                        <p>${string}</p>
                        <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>

        <script>
            $('.selectpicker').selectpicker();
        </script>
    </body>
</html>
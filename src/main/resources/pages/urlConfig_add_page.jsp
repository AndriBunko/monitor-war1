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
                        <%--<select class="selectpicker form-control form-group" name="group">--%>
                            <%--<option value="-1">Default</option>--%>
                            <%--<c:forEach items="${groups}" var="group">--%>
                                <%--<option value="${group.id}">${group.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <input class="form-control form-group" type="text" name="url" placeholder="Url">
                        <input class="form-control form-group" type="number" name="timeOk" placeholder="timeOk">
                        <input class="form-control form-group" type="number" name="timeWarning" placeholder="timeWarning">
                        <input class="form-control form-group" type="number" name="timeCritical" placeholder="timeCritical">
                        <input class="form-control form-group" type="text" name="substring" placeholder="substring">
                        <input class="form-control form-group" type="number" name="maxResponseLength" placeholder="maxResponseLength">
                        <input class="form-control form-group" type="number" name="minResponseLength" placeholder="minResponseLength">
                        <input class="form-control form-group" type="number" name="responseCode" placeholder="Response code">
                        <input class="form-control form-group" type="number" name="cooldown" placeholder="cooldown">
                    <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>

        <script>
            $('.selectpicker').selectpicker();
        </script>
    </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Monitor</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
           <h3><a href="/">URL List</a></h3>

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul id="groupList" class="nav navbar-nav">
                            <li><button type="button" id="add_url" class="btn btn-default navbar-btn">Add url</button></li>
                            <li><button type="button" id="delete_url" class="btn btn-default navbar-btn">Delete url</button></li>
                        </ul>
                        <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" name="pattern" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>

            <table class="table table-striped">
                <thead>
                <tr>
                    <td></td>
                    <td><b>URL</b></td>
                    <td><b>URL status</b></td>
                    <td><b>Description</b></td>
                    <td><b></b></td>
                </tr>
                </thead>
                <c:forEach items="${urlResults}" var="urlResult">
                    <tr>
                        <td><input type="checkbox" name="toDelete[]" value="${urlResult.url}" id="checkbox_${urlResult.url}"/></td>
                        <td>${urlResult.url}</td>
                        <td>${urlResult.status}</td>
                        <td>${urlResult.description}</td>
                        <td><button type="button" id="stop_${urlResult.url}" class="btn btn-default navbar-btn">Stop</button></td>
                    </tr>
                </c:forEach>
            </table>

            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach var="i" begin="1" end="${pages}">
                        <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </ul>
            </nav>
        </div>

        <script>
            $('.dropdown-toggle').dropdown();

            $('#add_contact').click(function(){
                window.location.href='/urlConfig_add_page';
            });

            // $('#add_group').click(function(){
            //     window.location.href='/group_add_page';
            // });

            $('#delete_url').click(function(){
                var data = { 'toDelete[]' : []};
                $(":checked").each(function() {
                    data['toDelete[]'].push($(this).val());
                });
                $.post("/url/delete", data, function(data, status) {
                    window.location.reload();
                });
            });
        </script>
    </body>
</html>
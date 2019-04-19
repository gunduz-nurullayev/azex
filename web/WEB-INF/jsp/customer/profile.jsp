<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <jsp:include page="../common/meta.jsp"/>
    <title>Azex Index Page</title>
    <jsp:include page="../common/css.jsp"/>
</head>

<body class="fixed-navbar">
<div class="page-wrapper">

    <jsp:include page="header.jsp"/>

    <jsp:include page="menu.jsp"/>

    <div class="content-wrapper">
        <!-- START PAGE CONTENT-->
        <div class="page-content fade-in-up">
            <div class="row">
                <div class="col-lg-8">
                    <div class="ibox-title">Basic form</div>
                    <div class="ibox-body">
                        <form>
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label>First Name</label>
                                    <input class="form-control" type="text" placeholder="First Name">
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label>Last Name</label>
                                    <input class="form-control" type="text" placeholder="First Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input class="form-control" type="text" placeholder="Email address">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input class="form-control" type="password" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label class="ui-checkbox">
                                    <input type="checkbox">
                                    <span class="input-span"></span>Remamber me</label>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-default" type="submit">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <style>
                .visitors-table tbody tr td:last-child {
                    display: flex;
                    align-items: center;
                }

                .visitors-table .progress {
                    flex: 1;
                }

                .visitors-table .progress-parcent {
                    text-align: right;
                    margin-left: 10px;
                }
            </style>
        </div>
        <!-- END PAGE CONTENT-->
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>

<jsp:include page="../common/paga.jsp"/>
<!-- END PAGA BACKDROPS-->
<!-- CORE PLUGINS-->
<jsp:include page="../common/script.jsp"/>

</body>

</html>
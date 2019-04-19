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
                <div class="col-lg-16">
                    <div class="ibox">
                        <div class="ibox-head">
                            <div class="ibox-title">Inline Form</div>
                        </div>
                        <div class="ibox-body">
                            <form class="form-inline">
                                <label class="sr-only" for="ex-email">Full Name</label>
                                <br>
                                <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="ex-email" type="text" placeholder="Email address">
                                <label class="sr-only" for="ex-pass">Email</label>
                                <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="ex-pass" type="password" placeholder="Password">
                                <div class="form-check mb-2 mr-sm-2 mb-sm-0">
                                    <label class="ui-checkbox ui-checkbox-inline">
                                        <input type="checkbox">
                                        <span class="input-span"></span>Remamber me</label>
                                </div>
                                <button class="btn btn-success" type="submit">Login</button>
                            </form>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <!-- END PAGE CONTENT-->
<jsp:include page="../common/footer.jsp"/>
    </div>
</div>

<jsp:include page="../common/paga.jsp"/>
<!-- END PAGA BACKDROPS-->
<!-- CORE PLUGINS-->
<jsp:include page="../common/script.jsp"/>

<!-- CORE SCRIPTS-->

<!-- PAGE LEVEL SCRIPTS-->
</body>

</html>
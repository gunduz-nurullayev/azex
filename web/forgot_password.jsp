<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="WEB-INF/jsp/common/meta.jsp"/>
    <title>Admincast bootstrap 4 &amp; angular 5 admin template, Шаблон админки | Login</title>
    <!-- GLOBAL MAINLY STYLES-->
    <jsp:include page="WEB-INF/jsp/common/css.jsp"/>
    <!-- THEME STYLES-->
    <link href="assets/css/main.css" rel="stylesheet" />
    <!-- PAGE LEVEL STYLES-->
    <link href="assets/css/pages/auth-light.css" rel="stylesheet" />

</head>

<body class="bg-silver-300">
    <div class="content">
        <div class="brand">
            <a class="link" href="index.jsp">Azex</a>
        </div>
        <form id="forgot-form" action="forget-password" method="post">
            <h3 class="m-t-10 m-b-10">Forgot password</h3>
            <p class="m-b-20">Enter your email address below and we'll send you password reset instructions.</p>

            <h5 style="color:red">${sessionScope.errorMessage}</h5>

            <h5 style="color:blue">${sessionScope.succesMessage}</h5>

            <div class="form-group">
                <input class="form-control" type="email" name="email" placeholder="Email" autocomplete="off">
            </div>
            <div class="form-group">
                <button class="btn btn-info btn-block" type="submit">Submit</button>
            </div>
        </form>
    </div>
    <!-- BEGIN PAGA BACKDROPS-->
    <div class="sidenav-backdrop backdrop"></div>
    <div class="preloader-backdrop">
        <div class="page-preloader">Loading</div>
    </div>
    <!-- END PAGA BACKDROPS-->
    <!-- CORE PLUGINS -->
    <jsp:include page="WEB-INF/jsp/common/paga.jsp"/>
    <!-- END PAGA BACKDROPS-->
    <!-- CORE PLUGINS -->
    <jsp:include page="WEB-INF/jsp/common/script.jsp"/>
    <!-- PAGE LEVEL SCRIPTS-->
    <script type="text/javascript">
        $(function() {
            $('#forgot-form').validate({
                errorClass: "help-block",
                rules: {
                    email: {
                        required: true,
                        email: true
                    },
                },
                highlight: function(e) {
                    $(e).closest(".form-group").addClass("has-error")
                },
                unhighlight: function(e) {
                    $(e).closest(".form-group").removeClass("has-error")
                },
            });
        });
    </script>
</body>

</html>
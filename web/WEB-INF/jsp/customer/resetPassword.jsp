<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Qeydiyyat</title>
    <link rel="stylesheet" type="text/css" href="./assets/css/view.css" media="all">
    <script type="text/javascript" src="./assets/js/view.js"></script>
    <script type="text/javascript" src="./assets/js/calendar.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</head>
<body id="main_body">

<script type="text/javascript">
    $('#password, #confirm_password').on('keyup', function () {
        if ($('#password').val() == $('#confirm_password').val()) {
            $('#message').html('Şifrə eyni olmalıdır').css('color', 'green');
        } else
            $('#message').html('Təsdiq et').css('color', 'red');
    });
</script>
<script type="text/javascript">
    function check_pass() {

        if ((document.getElementById('password').value == document.getElementById('confirm_password').value)) {
            document.getElementById('submit').disabled = false;
        } else {
            document.getElementById('submit').disabled = true;
        }

    }
</script>
<div id="form_container">

    <h1><a>Şifrənin bərpası</a></h1>
    <form id="form_50997" class="appnitro" method="post" action="pswd-changed">
        <div class="form_description">
            <h2>Şifrənin bərpası</h2>
            <p></p>
        </div>
        <ul>

            <li id="li_1">
                <label style="color: red;">${sessionScope.pswdCheck}</label>
                <label class="description">Şifrə </label>
                <div>
                    <input id="password" name="pswd1" class="element text medium" type="password"
                           onchange='check_pass();' maxlength="255" value="" required/>
                </div>
            </li>
            <li id="li_2">
                <label class="description">Təkrar şifrə </label>
                <div>
                    <input id="confirm_password" name="pswd2" class="element text medium" type="password"
                           onchange='check_pass();' maxlength="255" value="" required/>
                    <span id='message'></span>
                    <input type="hidden" name="email" value="${sessionScope.mail}"/>

                </div>

            </li>

            <li class="buttons">
                <input type="submit" id="submit" name="submit" onClick="return empty()" value="Təsdiq et"/> &nbsp;
                &nbsp; &nbsp; <input type="reset"/>
            </li>
        </ul>
    </form>

</div>
</body>
</html>
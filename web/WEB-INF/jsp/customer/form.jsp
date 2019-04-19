<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#channel").change(function () {
                // foo is the id of the other select box
                if ($(this).val() == "1") {
                    $("#code").show();
                } else {
                    $("#code").hide();
                }
            });
        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Qeydiyyat</title>
    <link rel="stylesheet" type="text/css" href="./assets/css/view.css" media="all">
    <script type="text/javascript" src="./assets/js/view.js"></script>
    <script type="text/javascript" src="./assets/js/calendar.js"></script>
</head>
<body id="main_body">
<div id="form_container">
    <h1><a>Qeydiyyat</a></h1>
    <form class="appnitro" method="post" action="activate">
        <div class="form_description">
            <h2>Qeydiyyat</h2>
        </div>
        <ul>
            <li>
                <span>
			<input value="${sessionScope.name.name}" name="name" type="text" readonly/>
		        </span>
                <span>
			<input value="${sessionScope.name.surname}" name="surname" type="text" readonly/>
		        </span>
                <span>
			<input value="${sessionScope.name.email}" name="email" type="text" readonly/>
		        </span>
            </li>
            <label style="color: red">${requestScope.birthDateError}</label>
            <div>
                <span>
			<input id="element_2_1" name="month" class="element text" size="2" placeholder="ay" maxlength="2"
                   value="${requestScope.month}"
                   type="text"> /
		        </span>
            <span>
			<input id="element_2_2" name="day" class="element text" size="2" placeholder="gün" maxlength="2"
                   value="${requestScope.day}"
                   type="text"> /
		        </span>
            <span>
	 		<input id="element_2_3" name="year" class="element text" size="4" placeholder="il" maxlength="4"
                   value="${requestScope.year}"
                   type="text">
		        </span>

            <span id="calendar_1">
			<img id="cal_img_1" class="datepicker" src="./assets/img/calendar.gif" alt="Pick a date.">
	        	</span>
            </div>
            <script type="text/javascript">
                Calendar.setup({
                    inputField: "element_2_3",
                    baseField: "element_2",
                    displayArea: "calendar_1",
                    button: "cal_img_1",
                    ifFormat: "%B %e, %Y",
                    onSelect: selectDate
                });
            </script>
            </li>
            <li>
                <div>
                    <label style="color: red">${requestScope.idCardPrefixError}</label>
                    <input id="element_3" name="id_card_prefix" type="text" placeholder="Ş/V seriyası" maxlength="10"
                           value="${requestScope.idCardPrefix}"/>
                </div>
            </li>
            <li>
                <div>
                    <label style="color: red">${requestScope.idCardNumberError}</label>
                    <input id="element_4" name="id_card_number" type="number" value="${requestScope.idCardNumber}"
                           placeholder="Ş/V seriya nömrəsi"
                           maxlength="8"/>
                </div>
            </li>
            <li>
                <div>
                    <label style="color: red">${requestScope.pinCodeError}</label>
                    <input id="element_5" name="pin_code" type="text" placeholder="Pin kod" maxlength="7"
                           value="${requestScope.pinCode}"/>
                </div>
            </li>
            <li>
                <div>
                    <label style="color: red">${requestScope.cinsError}</label>
                    <select id="element_6" name="gender">
                        <option value="" selected="" hidden="">Cins</option>
                        <option value="1">Qadın</option>
                        <option value="2">Kişi</option>
                    </select>
                </div>
            </li>
            <label style="color: red">${requestScope.numberError}</label>
            <div>
                <span>
                    <select name="phone_prefix1">
                        <option value="050">050</option>
                        <option value="051">051</option>
                        <option value="055">055</option>
                        <option value="070">070</option>
                        <option value="077">077</option>
                    </select>
                <input name="phone1" size="3" maxlength="7" type="number" value="${requestScope.number1}">
                   </span> &nbsp;
                     <span>
			         <select name="phone_prefix2">
                         <option value="050">050</option>
                         <option value="051">051</option>
                         <option value="055">055</option>
                         <option value="070">070</option>
                         <option value="077">077</option>
                    </select>
                <input id="element_8_1" name="phone2" class="element text" size="4" maxlength="7" type="number"
                       value="${requestScope.number2}">
                </span>
            </div>
            </li>
            <li>
                <div>
                    <label style="color: red">${requestScope.seherError}</label>
                    <select class="element select medium" id="element_9" name="city">
                        <option value="" selected="" hidden="">Şəhər</option>
                        <c:forEach var="j" begin="0" end="${sessionScope.city.size()-1}">
                            <option value="${sessionScope.city.get(j).valueId}"><c:out
                                    value="${sessionScope.city.get(j).valueName}"/></option>
                        </c:forEach>

                    </select>
                </div>
            </li>
            <li>
                <div>
                    <label style="color: red">${requestScope.addressError}</label>
                    <input id="element_10" name="address" class="element text medium" placeholder="Ünvan"
                           value="${requestScope.address}"
                           type="text" maxlength="255"/>
                </div>
            </li>
            <li>
                <div>
                    <input id="element_11" name="district" class="element text medium" placeholder="Küçə nömrəsi"
                           value="${requestScope.kuce}" type="number" maxlength="255"/>
                </div>
            </li>
            <li>
                <div>
                    <select class="element select medium" id="channel" name="channel">
                        <option value="" selected="" hidden="">Bizi hardan eşitmisiniz?</option>
                        <c:forEach var="j" begin="0" end="${sessionScope.channel.size()-1}">
                            <option value="${sessionScope.channel.get(j).valueId}"><c:out
                                    value="${sessionScope.channel.get(j).valueName}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </li>
            <li>
                <div>
                    <input id="code" type="text" name="referal_code" style="display:none;" value="" placeholder="Dostunun istifadəçi kodu"/>
                </div>
            </li>
            <li class="buttons"><input type="submit" value="Təsdiq et"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="Təmizlə"/></li>
        </ul>
    </form>

</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 25.03.2019
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form name="customerData">
<div class="form-group">
    <label for="id">No</label>
    <input type="text" id="id" value="${sessionScope.customer.id}">
</div>
<div class="form-group"><label>Ad</label>
    <input type="text" id="name" value="${sessionScope.customer.name}">

</div>
<div class="form-group"><label>Soyad</label>
    <input type="text" id="surname" value="${sessionScope.customer.surname}">

</div>
<div class="form-group"><label>Email</label>
    <input type="email" id="email" value="${sessionScope.customer.email}">

</div>
<div class="form-group"><label>Doğum tarixi</label>
    <input type="date" id="birth-date" value="${sessionScope.customer.birthDate}">

</div>
<div class="form-group"><label>ŞV/Seriyası</label>
    <input type="text" id="id-card-prefix" value="${sessionScope.customer.idCardPrefix}">

</div>
<div class="form-group"><label>ŞV/Seriya nömrəsi</label>
    <input type="text" id="id-card-number" value="${sessionScope.customer.idCardNumber}">

</div>
<div class="form-group"><label>Pin kod</label>
    <input type="text" id="pin-code" value="${sessionScope.customer.pinCode}">

</div>
<div class="form-group"><label>Müştəri kodu</label>
    <input type="text" id="customer-code" value="${sessionScope.customer.customerCode}">

</div>
<div class="form-group"><label>Şəhər</label>
    <select>
        <option value="${sessionScope.city.get(sessionScope.customer.city-1).valueId}">${sessionScope.city.get(sessionScope.customer.city-1).valueName}</option>
        <c:forEach var="j" begin="0" end="${sessionScope.city.size()-1}">
            <option value="${sessionScope.city.get(j).valueId}">
                <c:out value="${sessionScope.city.get(j).valueName}"></c:out>
            </option>
        </c:forEach>
    </select>
</div>
</form>
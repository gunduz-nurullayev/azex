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
    <script type="text/javascript" src="./resource/js/customer.js"></script>
    <div class="content-wrapper">
        <!-- START PAGE CONTENT-->
        <div class="page-heading">
            <h1 class="page-title">Yeni Sifaris</h1>
        </div>
        <div class="page-content fade-in-up">
            <div class="ibox">
                <div class="ibox-head">
                    <div class="ibox-title">Sifaris melumatlarini daxil edin!</div>
                </div>
                <div class="ibox-body">
                    <form class="form-inline">
                        <label class="sr-only" for="link1">Mehsulun link</label>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="link1" oninput="orderSubmit()" type="text"
                               placeholder="Mehsulun linki">
                        <label class="sr-only" for="link1">Ad</label>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="name1" type="text"
                               placeholder="Ad">

                        <label class="sr-only" for="qty1">Say</label>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="qty1" type="number" placeholder="Say">

                        <label class="sr-only" for="price1">Qiymet</label>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="price1" type="number" placeholder="Qiymet">

                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="service1" type="text" placeholder="+5%"
                               value="+5%" readonly="readonly">

                        <label class="sr-only" for="total1">Cem</label>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="total1" type="number" placeholder="Cem"
                               readonly="readonly">


                        <button class="btn btn-success" onclick="" type="submit">Sifaris ver</button>
                    </form>
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
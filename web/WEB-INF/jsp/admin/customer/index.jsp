<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <jsp:include page="../../common/meta.jsp"/>
    <title>Azex Index Page</title>
    <jsp:include page="../../common/css.jsp"/>
    <link href="./assets/vendors/DataTables/datatables.min.css" rel="stylesheet"/>
</head>

<body class="fixed-navbar">
<div class="page-wrapper">

    <jsp:include page="../header.jsp"/>

    <jsp:include page="../menu.jsp"/>

    <div class="content-wrapper">
        <!-- START PAGE CONTENT-->
        <div class="page-content fade-in-up">
            <div class="ibox">
                <div class="ibox-head">
                    <div class="ibox-title">Müştərilər</div>
                </div>
                <div class="ibox-body">
                    <div>
                        <form action="#">
                            <label for="query">Müştəri</label>
                            <input type="text" id="query" name="query">
                            <input type="submit" value="Axtar">
                        </form>
                    </div>
                </div>
                <div class="ibox-body">
                    <table class="table table-striped table-bordered table-hover" id="customer-table" cellspacing="0"
                           width="100%">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>Ad</th>
                            <th>Email</th>
                            <th>Doğum tarixi</th>
                            <th>ŞV/Seriya Nömrəsi</th>
                            <th>Pin kod</th>
                            <th>Müştəri kodu</th>
                            <th>Şəhər</th>
                            <th>Edit/Delete</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>No</th>
                            <th>Ad</th>
                            <th>Email</th>
                            <th>Doğum tarixi</th>
                            <th>ŞV/Seriya Nömrəsi</th>
                            <th>Pin kod</th>
                            <th>Müştəri kodu</th>
                            <th>Şəhər</th>
                            <th>Edit/Delete</th>
                        </tr>
                        </tfoot>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
            <div id="viewCustomer" style="display: none">

            </div>
        </div>
        <!-- END PAGE CONTENT-->
        <jsp:include page="../../common/footer.jsp"/>
    </div>
</div>
<!-- BEGIN PAGA BACKDROPS-->
<jsp:include page="../../common/paga.jsp"/>
<!-- END PAGA BACKDROPS-->
<!-- CORE PLUGINS-->
<jsp:include page="../../common/script.jsp"/>
<script src="./assets/vendors/DataTables/datatables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="./resource/js/customer.js"></script>
<script type="text/javascript">
    $(function () {
        $('#customer-table').DataTable({
            "lengthMenu": [[1, 2, 3, 4, 5, 10, 25, 50, -1], [1, 2, 3, 4, 5, 10, 25, 50, "All"]],
            "serverSide": "true",
            "ajax": 'admin?action=showCustomer',
            "columns": [
                {data: "id"},
                {
                    data: null, render: function (data) {
                        return data.name + ' ' + data.surname;
                    }
                },
                {data: "email"},
                {data: "birthDate"},
                {
                    data: null, render: function (data) {
                        return data.idCardPrefix + '' + data.idCardNumber;
                    }
                },
                {data: "pinCode"},
                {data: "customerCode"},
                {data: "cityName"},
            ],
            "columnDefs": [
                {
                    "render": function (data, type, row) {
                        return '<a href="javascript: editCustomerData(' + row.id + ')"><i class="fa fa-edit" style="color: green"></i></a>'
                    }
                    , "targets": 8
                }
            ],
        });
    })
</script>
</body>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Müştəri</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="bodyID">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Bağla</button>
                <button type="button" id="edit-customer"  onclick="editCustomer()" class="btn btn-primary">Təsdiqlə</button>
            </div>
        </div>
    </div>
</div>

</html>
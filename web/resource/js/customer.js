globCustomerId = 0;

function editCustomerData(customerId) {
    console.log("salam " + customerId)
    globCustomerId = customerId;
    var mydata = {
        customerId: customerId
    };
    $.ajax({
        url: 'admin?action=getUserByCustomerId',
        data: mydata,
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $("#bodyID").html(data);
            $("#exampleModal").modal();
        }
    })

};


function editCustomer(){
    var form=$('#customerData');
    var mydata = {
        customerId: globCustomerId,
    };
    form.submit(function () {
        $.ajax({
            url: 'admin?action=updateCustomer',
            data: form.serialize()+mydata,
            type: 'POST',
            dataType: 'html',
            success : function (data) {
                editCustomerData(globCustomerId)
            }
        })
    })
      /*ixp2480289
      eq900049*/
};
function orderSubmit() {
  var link = document.getElementById("link1").value;
    var mydata = {
        orderLink: link
    };
    //document.getElementById("name1").value="Test1";
  $.ajax({
      url: 'customer?action=order',
      data: mydata,
      type: 'GET',
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      success : function (data) {
          console.log(data.name);
          document.getElementById("name1").value=data.name;
          document.getElementById("price1").value=data.price+" "+data.currency[0];
          document.getElementById("qty1").value=1;
          document.getElementById("total1").value=parseFloat(data.price)*1.05;
      }
  })
 
};

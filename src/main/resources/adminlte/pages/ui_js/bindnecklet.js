$(function () {
    //注册相关
    var datares = {};
    // $.ajax({
    //     url: "/aidog/api/register/4",
    //     async:false,
    //     type: "GET",
    //     success: function (data) {
    //         datares = eval("(" + data + ")");
    //     }
    // });
    $.getJSON ("/aidog/adminlte/pages/ui_js/district.json", function (data)
    {
        datares = data;
    });

    $("#select_province").on('change', function () {
        var selectvalue = $(this).find('option:selected').val();
        selectvalue = selectvalue.substring(0,2);
        $("#select_city").find("option").remove();
        var select_city = document.getElementById("select_city");
        select_city.options.add(new Option("请选择", "-1"));
        if ($('#modalselect_adminlevel').find('option:selected').text()!="省级") {
            for (var i = 0; i < datares.data1.length; i++) {
                if (datares.data1[i].districtcode.toString().substring(0, 2) == selectvalue) {
                    //遍历后台传回的结果，一项项往select中添加option
                    select_city.options.add(new Option(datares.data1[i].districtname, datares.data1[i].districtcode));
                }
            }
        }
        $("#select_county").find("option").remove();
        var select_county = document.getElementById("select_county");
        select_county.options.add(new Option("请选择", "-1"));

        $("#select_village").find("option").remove();
        var select_village = document.getElementById("select_village");
        select_village.options.add(new Option("请选择", "-1"));

        $("#select_hamlet").find("option").remove();
        var select_hamlet = document.getElementById("select_hamlet");
        select_hamlet.options.add(new Option("请选择", "-1"));
    });
    $("#select_city").on('change', function () {
        var selectvalue = $(this).find('option:selected').val();
        $("#select_county").find("option").remove();
        selectvalue = selectvalue.substring(0, 4);
        var select_county = document.getElementById("select_county");
        select_county.options.add(new Option("请选择", "-1"));
        if ($('#modalselect_adminlevel').find('option:selected').text() != "市级") {
            for (var i = 0; i < datares.data2.length; i++) {
                if (datares.data2[i].districtcode.toString().substring(0, 4) == selectvalue) {
                    //遍历后台传回的结果，一项项往select中添加option
                    select_county.options.add(new Option(datares.data2[i].districtname, datares.data2[i].districtcode));
                }
            }
        }
        $("#select_village").find("option").remove();
        var select_village = document.getElementById("select_village");
        select_village.options.add(new Option("请选择", "-1"));

        $("#select_hamlet").find("option").remove();
        var select_hamlet = document.getElementById("select_hamlet");
        select_hamlet.options.add(new Option("请选择", "-1"));
    });
    $("#select_county").on('change', function () {
        var selectvalue = $(this).find('option:selected').val();
        $("#select_village").find("option").remove();
        selectvalue = selectvalue.substring(0, 6);
        var select_village = document.getElementById("select_village");
        select_village.options.add(new Option("请选择", "-1"));
        if ($('#modalselect_adminlevel').find('option:selected').text() != "县级") {
            for (var i = 0; i < datares.data3.length; i++) {
                if (datares.data3[i].districtcode.toString().substring(0, 6) == selectvalue) {
                    //遍历后台传回的结果，一项项往select中添加option
                    select_village.options.add(new Option(datares.data3[i].districtname, datares.data3[i].districtcode));
                }
            }
        }
        $("#select_hamlet").find("option").remove();
        var select_hamlet = document.getElementById("select_hamlet");
        select_hamlet.options.add(new Option("请选择", "-1"));
    });
    $("#select_village").on('change', function () {
        var selectvalue = $(this).find('option:selected').val();
        $("#select_hamlet").find("option").remove();
        selectvalue = selectvalue.substring(0, 9);
        var select_hamlet = document.getElementById("select_hamlet");
        select_hamlet.options.add(new Option("请选择", "-1"));
        if ($('#modalselect_adminlevel').find('option:selected').text() != "乡级") {
            for (var i = 0; i < datares.data4.length; i++) {
                if (datares.data4[i].districtcode.toString().substring(0, 9) == selectvalue) {
                    //遍历后台传回的结果，一项项往select中添加option
                    select_hamlet.options.add(new Option(datares.data4[i].districtname, datares.data4[i].districtcode));
                }
            }
        }
    });
    var hamletcode = "";
    $("#select_hamlet").on('change', function () {
        hamletcode = $(this).find('option:selected').val();
        var selectvalue = $(this).find('option:selected').text();
        $("#input_dogbelonghamlet").val(selectvalue);
    });


    $("#a_checkowner").click(function () {
        if(hamletcode == ""){
            alert("请先选定行政村！");
            return;
        }
        var clicktype = "ownercheck";
        var ownername = $("#input_ownername").val()==null?"":$("#input_ownername").val();
        var owneridentity = $("#input_owneridentity").val()==null?"":$("#input_owneridentity").val();
        var telphone = $("#input_telphone").val()==null?"":$("#input_telphone").val();
        var checkdata = {};
        checkdata.clicktype = clicktype;
        checkdata.ownername = ownername;
        checkdata.owneridentity = owneridentity;
        checkdata.ownerhamletcode = hamletcode;
        checkdata.telphone = telphone;
        $.ajax({
            url: "/aidog/api/operateapi",
            type: "POST",
            data: JSON.stringify(checkdata),
            contentType: "application/json",
            // dataType: "text",    // 控制回来的数据类型
            beforeSend: function (request) {
                request.setRequestHeader("token", window.localStorage.getItem("aidog_token"));
            },
            success: function (data) {
                if(data.data == null){
                    alert("信息填写有误或无此人！");
                    return;
                }
                alert(data.msg);
                if(data.success == true){
                    $("#input_ownerid").val(data.data.dogowner.ownerId);
                    $("#input_ownername").val(data.data.dogowner.ownerName);
                    document.getElementById("input_ownername").readOnly = true;
                    $("#input_owneridentity").val(data.data.dogowner.ownerIdentity);
                    document.getElementById("input_owneridentity").readOnly = true;
                    $("#input_telphone").val(data.data.dogowner.ownerTel);
                    document.getElementById("input_telphone").readOnly = true;

                    var senddata = {};
                    senddata.hamletcode = hamletcode;
                    $.ajax({
                        url:  "/aidog/api/getunbinddogandnec",
                        type: "POST",
                        data:  senddata,
                        beforeSend: function (request) {
                            request.setRequestHeader("token", window.localStorage.getItem("aidog_token"));
                        },
                        success: function (data) {
                            var select_doggovcode = document.getElementById("select_doggovcode");
                            // data.data = objToArray(data.data);
                            for (var i = 0; i < data.data.govcodelist.length; i++) {
                                if(data.data.govcodelist[i].ownerName == $("#input_ownername").val()){
                                    //遍历后台传回的结果，一项项往select中添加option
                                    select_doggovcode.options.add(new Option(data.data.govcodelist[i].dogGovcode, data.data.govcodelist[i].dogId));
                                }
                             }

                            var select_dognecid = document.getElementById("select_dognecid");
                            // data.data = objToArray(data.data);
                            for (var i = 0; i < data.data.neclist.length; i++) {
                                //遍历后台传回的结果，一项项往select中添加option
                                select_dognecid.options.add(new Option(data.data.neclist[i], data.data.neclist[i]));
                            }
                            //
                            // $("#input_dogownername").val(data.data.govcodelist[0].ownerName);
                            $("#input_dogname").val(data.data.govcodelist[0].dogName);
                            $("#select_doggovcode").on('change', function () {
                                var selectvalue = $(this).find('option:selected').val();
                                var index = $(this).find('option:selected').index();
                                // $("#input_dogownername").val(data.data.govcodelist[index].ownerName);
                                $("#input_dogname").val(data.data.govcodelist[index].dogName);
                            });
                        }
                    })

                }else{
                    return;
                }
            }
        })
    });

    $("#a_reset").click(function () {
        $("#input_ownerid").val("");
        $("#input_ownername").val("");
        document.getElementById("input_ownername").readOnly = false;
        $("#input_owneridentity").val("");
        document.getElementById("input_owneridentity").readOnly = false;
        $("#input_telphone").val("");
        document.getElementById("input_telphone").readOnly = false;
        var select_doggovcode = document.getElementById("select_doggovcode");
        select_doggovcode.options.length = 0;
        var select_dognecid = document.getElementById("select_dognecid");
        select_dognecid.options.length = 0;
        $("#input_dogname").val("");
    });



    //项圈配对激活
    $("#a_bindnec").click(function () {
        if(hamletcode == ""){
            alert("请先选择主人所属行政村！");
            return;
        }
        var dogid = $("#select_doggovcode").find('option:selected').val();
        var necid = $("#select_dognecid").find('option:selected').text();
        var senddata = {};
        senddata.dogid = dogid;
        senddata.necid = necid;
        $.ajax({
            url: "/aidog/api/bindnecklet",
            type: "POST",
            data:  senddata,
            beforeSend: function (request) {
                request.setRequestHeader("token", window.localStorage.getItem("aidog_token"));
            },
            success: function (data) {
                alert(data.msg);
                window.location.reload();
            }
        })
    });

})


function objToArray(array) {
    var arr = []
    for (var i in array) {
        arr.push(array[i]);
    }
    return arr;
}
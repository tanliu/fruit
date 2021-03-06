/**
 * Created by TanLiu on 2017/3/3.
 */
var saveUrl=baseUrl+"/employee/addFarmer";
var detailUrl=baseUrl+"/employee/getFarmerDetail";
var updataUrl=baseUrl+"/employee/updateFarmer";
var changPwdUrl=baseUrl+"/employee/resetFarmerPassword";
var listUrl=baseUrl+"/employee/showFarmers";
var deleteUrl=baseUrl+"/employee/farmerChangeStatus";
var deleteAllUrl=baseUrl+"/employee/deleteRfids";
var isBindUserIDUrl = baseUrl + "/user/isBindUserID";
var addHtml="add.html";
var editHtml="edit.html";
var pwdHtml="changpassword.html";
var picturesHtml="pictures.html";
var editTile="修改果树信息";
var addTitle="增加果树信息";


function setMySeletor(){
    addInfoToSelect(baseUrl+"/region/getAllRegionWithAll","regionsSelect",{});
    $("#regionsSelect").change(function(){
        var id=$("#regionsSelect").find("option:selected").attr("id").trim();
        if(id>-1){
            var param={"regionId":id}
            addInfoToSelect(baseUrl+"/public/getVillagesByRegionWithAll","villageSelect",param);
        }else{
            removeInfoToSelect("villageSelect");
        }

    });
    $("#regionsSelect").change(function(){
        var id=$("#regionsSelect").find("option:selected").attr("id").trim();
        if(id>-1){
            var param={"regionId":id}
            addInfoToSelect(baseUrl+"/public/getVillagesByRegion","villageSelect",param);
        }else{
            removeInfoToSelect("villageSelect");
        }

    });

}


//设置列对应的信息
function setColumns(){
    return  [
        {data: 'id'},
        {data: 'username'},
        {data: 'name'},
        {data: 'phone'},
        {data: 'qq'},
        {data: 'email'},
        {data: 'contractStart'},
        {data: 'contractEnd'},
        {data: 'address'},
        {data: 'createTime'},
        {
            "render": function (data, type, full, meta) {
                operation =                 "                    <a href='javascript:revise(" + full.id + ");'><span title='修改' class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>&nbsp;&nbsp;";

                return operation;
            },

        },

    ];
}

//设置按钮的信息
function setButtons(){
    return  [
        {
            text: "增加",
            className: "btn btn-default",
            action: function (e, dt, node, config) {
                opneModel({url: addHtml, onOK: mySave, title: addTitle});
                $('#myModal').on('shown.bs.modal', function () {
                    init_validator();
                    init_daterangepicker_single_call();
                    if(typeof (setMySeletor) =="function"){
                        setMySeletor();
                    }
                    $("#form_username").blur(function(){
                        if($(this).val()==null)return ;
                        $.ajax({
                            type: "post",
                            url: isBindUserIDUrl,
                            data: {"employNo": $(this).val()},
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if(data.status==400){
                                    alert(data.meg);
                                }else if(data.status==200){
                                    alert(data.meg);
                                }


                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                alert("打开失败，认重新打开！");
                            }
                        });
                    });


                });

            }
        }

    ];
}


function mySave(){
    selectInfoToInput("regionsSelect","regionsIdSelect");
    selectInfoToInput("villageSelect","villageIdSelect");
    //判断合同时间
    var canSave=$("#time_begin").val()<$("#time_end").val();
    if(canSave){
        save();
    }else{
        alert("合同时间不正确！")
    }
}



//设置查询的请求信息
function setParam(param){
    var times=$("#reportrange").val().split(" - ");
    param.select_time_begin = times[0];
    param.select_time_end = times[1];
    param.search_key = $("#search_key").val().trim();
/*    param.select_village=$("#list_select_village").find("option:selected").attr("id");
    param.select_orchard=$("#list_select_orchard").find("option:selected").attr("id");
    param.select_product=$("#list_select_productinformation").find("option:selected").attr("id");*/
    return param;
}


//设置删除的信息
function setDeleteInfo(id){
    return {"id":id};
}


//对信息的设置
function setReviseInfo(id){
    return {"id":id};
}
function setFormInfo(){
    return {
        id:"editform",
       /* ignore:["pictures"]*/
    };
}

function setEditTime(data){
    $('#time_begin').daterangepicker({
        autoUpdateInput:false,
        singleDatePicker: true,
        singleClasses: "picker_1",
        defaultDate:"",
        locale: {
            format: 'YYYY-MM-DD'
        }
    }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
    $('#time_end').daterangepicker({
        singleDatePicker: true,
        singleClasses: "picker_1",
        locale: {
            format: 'YYYY-MM-DD'
        }
    }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
    $("#time_begin").val(data.contractStart);
    $("#time_end").val(data.contractEnd);
}


function changStatus(id){
    var deleteinfo=setDeleteInfo(id);
    MyDialog({
        title:"提示信息",
        content:"确定更新状态吗？",
        onOK:function(){

            $.ajax({
                url:deleteUrl,
                type:"post",
                data: deleteinfo,
                dataType: "json",
                async:false,
                success: function(data){
                    closeMyDialog();
                    alert(data.meg);
                    table.ajax.reload();

                },
                error : function (XMLHttpRequest, textStatus, errorThrown){
                    closeMyDialog();
                    alert("更新失败，请重新更新！");
                }

            });

        }
    })
}

function changPassword(id){
    opneModel({url:pwdHtml,onOK:changpwd,title:editTile});
    $('#myModal').on('shown.bs.modal', function () {
        init_validator ();
    });
    $("#editpwdform input[name='id']").val(id);
}
function changpwd(){
    var options={
        url:changPwdUrl,
        type:"POST",
        dataType:"json",
        success: function(data){
            var status=data.status;
            if(status==200){
                alert(data.meg);
                closeMyDialog();
                //table.ajax.reload();
            }
            else if(status==400){
                alert(data.meg);
            }
        },
        error:function(){alert("修改失败！");}

    };

    $("#editpwdform").ajaxSubmit(options);
}
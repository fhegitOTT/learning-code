 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html >
<head>
	<title>出售合同模板浏览</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="cache-control" content="no-store" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<%-- <script type="text/javascript" src="<%=basePath%>/js/application.js"></script>  --%>
   <link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css" media="all"/> 
</head>
<body>

<div >
   		<%-- <button id="btn1" onclick="go3(${contractID})">编辑乙方</button>
   	   	<button id="btn2" onclick="go5(${contractID})">编辑合同</button> --%>
</div>

<%--  <jsp: include file="SellContractSign.jsp" flush="true"/> --%>


<div class="layui-tab layui-tab-card" lay-filter="tab-all">
  <ul class="layui-tab-title">
    <li class="layui-this"  lay-filter="tab1" data-status="1">编辑乙方</li>
    <li data-status="2">编辑合同</li>
  </ul>
  <div class="layui-tab-content" style="height: 720px">
    <div class="layui-tab-item layui-show" style="height: 700px">
    


	<table class="layui-hide" id="test" lay-filter="test"></table>
	<div id="add-main" style="display: none;">
	    <div class="my-form">
	        <form id="FormData" class="layui-form" action="" method="POST" lay-skin="line" >
	          	<div class="layui-form-item my-form-item">
	          		<label class="layui-form-label layui-icon">&#xe770;</label>
	                <div class="layui-input-inline">
	                    <input type="text" name="contractID" value="${requestScope.contractID}" disabled readonly class="layui-input layui-disabled">
	                </div>
	            </div> 
	            <div class="layui-form-item my-form-item">
	                <label class="layui-form-label layui-icon">&#xe770;</label>
	                <div class="layui-input-inline">
	                    <input type="text" name="buyerName" lay-verify="required" placeholder="受让方(乙方)" autocomplete="off"
	                           class="layui-input">
	                </div>
	            </div>
	            <div class="layui-form-item my-form-item">
	                <label class="layui-form-label layui-icon">&#xe673;</label>
	                <div class="layui-input-inline">
	                    <input type="text" name="buyerSex" lay-verify="required" placeholder="性别" autocomplete="off"
	                           class="layui-input">
	                </div>
	            </div>
	            <div class="layui-form-item my-form-item">
	                <label class="layui-form-label layui-icon">&#xe673;</label>
	                <div class="layui-input-inline">
	                    <input type="text" name="buyerAddress" lay-verify="required" placeholder="住所(址)" autocomplete="off"
	                          class="layui-input">
	                </div>
	            </div>
	            <div class="layui-form-item my-form-item">
	                <label class="layui-form-label layui-icon">&#xe673;</label>
	                <div class="layui-input-inline">
	                    <input type="text" name="buyerPostcode" lay-verify="required" placeholder="邮箱 " autocomplete="off"
	                           class="layui-input">
	                </div>
	            </div>
	            <div class="layui-form-item my-form-item">
	                <label class="layui-form-label layui-icon">&#xe673;</label>
	                <div class="layui-input-inline">
	                    <input type="text" name="buyerCall" lay-verify="required" placeholder="联系电话" autocomplete="off"
	                           class="layui-input">
	                </div>
	            </div>
	            <div class="layui-form-item my-form-item">
	                <div class="layui-input-block item">
	                	
	                    <button type="button" class="layui-btn" lay-submit="" lay-filter="saveYF" id="submit1" >保存</button>
	                    <!-- onclick ="saveInfo()" -->
	                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	                </div>
	            </div>
	        </form>
	    </div>
	</div>

<script type="text/html" id="toolbarDemo">

    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button class="layui-btn layui-btn-sm layui-icon" lay-event="add">&#xe654;</button>
        <button class="layui-btn layui-btn-sm layui-icon" lay-event="batchDel">&#xe640;</button>
        <button class="layui-btn layui-btn-sm layui-icon" lay-event="flush">&#xe669;</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-icon" lay-event="del">&#xe640;</a>
</script>


		
		<script type="text/javascript">
			var isCheckAll = false;
			//var allBox = $("#allBox");
			//var boxes =$('input:checkbox[name="box"]');
			//var trs=$("#search_list_content").find("tr");
			
			function checkAll(){
			    if(allBox.is(':checked'))
			    {
				      for(var i=0; i<boxes.length; i++)
				      {
				        boxes.eq(i).prop('checked', true);
				      }
			    }else{
				      for(var i=0; i<boxes.length; i++)
				      {
				        boxes.eq(i).removeAttr('checked');
				      }
		    	}
			}
			
				
			function goBack(contractID){
				var url = "<%=basePath%>/outer/signcontractFHE/createContractFHEBack.action?contractID="+contractID;
				parent.parent.openDialog("乙方新增4",url,700,400);
			}	
				
				
		 	function doAdd(contractID){
				var url = "<%=basePath%>/outer/signcontractFHE/operaBuyerInfoFHEAdd.action?contractID="+contractID;
				parent.parent.openDialog("乙方新增4",url,700,400);
				//$('#fheDialog').dialog('refresh', url);
			}
			function doModify(contractID,serial){
				var url = "<%=basePath%>/outer/signcontractFHE/operaBuyerInfoFHEADD.action?contractID="+contractID+"&serial="+serial;
				parent.openDialog("乙方修改",url,900,600);
			}
			function delUserConfirm(){
				var length=$('input[name=box]:checked').length;
				alert("delUserConfirm length="+length)
				if(length==0){
					if(confirm("请选择要删除的用户！")){
					}
				}else{
					//执行删除方法
					doDelete(${contractID});
				  }
			 }
			function doDelete(contractID){
				var chk_value =[];  
			   $('input[name=box]:checked').each(function(){  
				   chk_value.push($(this).val());
			   });
 				alert("doDelete length="+chk_value.length)
				/*if(serial==1){
					alert('请勿删除第一顺序的乙方！请修改！');
					return false;
				}*/
				//if(confirm('确定删除'+buyerName+'吗?')){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontractFHE/deleteBuyerFHE.action?contractID='+contractID,  
						data : {chk_value:chk_value},
						dataType : "json",
						success : function(data){
							parent.go1(contractID);
							//location.reload();
							//window.location.href='<%=basePath%>/outer/signcontractFHE/deleteBuyerFHE.action?contractID='+contractID+"&chk_value="+chk_value; 
							/* if(data[0].result=='success'){
								alert(data[0].msg);
								location.reload();
								parent.doUpdate('乙方编辑');
							}else{
							
								alert(data[0].msg1);
							} */
						},
						error : function(){
							alert("删除乙方出错！");
						}
					});
				//}
			}
		</script>
		
    
   	</div>
    <div class="layui-tab-item" style="height: 700px">
   		<%--  <jsp: include file="SellContractSign.jsp" flush="true"/> --%>
   		  <iframe align="middle" style="height: 100%;width:100%;" src="<%=basePath%>/outer/signcontractFHE/gotoSignContractFHE.action?contractID=${requestScope.contractID}&projectID=${requestScope.projectID}&startID=${requestScope.startID}&houseID=${requestScope.houseId}&showFlag=0"></iframe>
		 <%-- <%@ include file='SellContractSign.jsp" flush="true" %> --%>

	</div>
  </div>
</div>

	
<script type="text/javascript">
function go3(contractID){

$.ajax({
   		type:"post",
   		url:"<%=basePath%>/outer/signcontractFHE/editBuyer1FHE.action",
   		async:false,
   		data:{contractID:contractID},
   		success:function(data){
			var url = "<%=basePath%>/outer/signcontractFHE/editBuyer1FHE.action?contractID="+contractID;
			parent.openDialog("乙方新增32",url,700,400);
			//${'#openDLFHE'}.dialog('refresh', url);

   		}
   	})
}
</script>
<script type="text/javascript">
function go5(contractID){
		var projectID=${projectID};
		var startID=${startID};
		var houseId=${houseId};
			
		
		var url = "<%=basePath%>/outer/signcontractFHE/gotoSignContractFHE.action?contractID="+contractID+"&projectID="+projectID+"&houseId="+houseId+"&startID="+startID;
		parent.openDialog("合同编辑",url,900,600);

}
</script>
<script type="text/javascript">
function go1(contractID){
alert(contractID)
	$.ajax({
   		type:"post",
   		url:"<%=basePath%>/outer/signcontractFHE/editBuyer1FHE.action",
   		async:false,
   		data:{contractID:contractID},
   		success:function(data){
			var url = "<%=basePath%>/outer/signcontractFHE/editBuyer1FHE.action?contractID="+contractID;
			parent.openDialog("乙方新增3",url,700,400);
   		}
   	})
}
</script>
<script type="text/javascript">
function go2(contractID){//contractID,projectID,startID,houseId
		var projectID=${projectID};
		var startID=${startID};
		var houseId=${houseId};
		if (${requestScope.buyerList}!=""){
		var url = "<%=basePath%>/outer/signcontractFHE/gotoSignContractFHE.action?contractID="+contractID+"&projectID="+projectID+"&houseId="+houseId+"&startID="+startID;
		parent.openDialog("合同编辑",url,900,600);
		}else{
			alert("请先添加乙方信息！");
		}
}
</script>
<script type="text/javascript" src="<%=basePath%>/layui/layui.js" ></script>
<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>

<script>
    layui.use(['table', 'layer','form'], function () {
        var table = layui.table,
            layer = layui.layer;
            form = layui.form;
        var $ = layui.$;
        var houseId=${houseId};

        table.render({
            url: "<%=basePath%>/outer/signcontractFHE/findBuyerList.action?contractID="+${requestScope.contractID}   //换成自己的url
            , method: "GET"
            , elem: '#test'

            , toolbar: '#toolbarDemo'
            , title: '乙方用户列表'
            , cols: [
                [
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'serial', title: '序号', width: 80, fixed: 'left', unresize: true, sort: true}
                    , {field: 'contractID', title: '合同号', width: 120, fixed: 'left', unresize: true, sort: true}
                    , {field: 'buyerName', title: '用户名', width: 120, edit: 'text'}
                    , {field: 'buyerSex', title: '性别', width: 80}
                    , {field: 'buyerAddress', title: '住所(址)', width: 120, edit: 'text'}
                    , {field: 'buyerPostcode', title: '邮箱', width: 120}
                    , {field: 'buyerCall', title: '联系电话', width: 120}
                    , {field: 'updateBy', title: '更新人', width: 120, edit: 'text'}
                    , {field: 'updateTime', title: '更新时间', minwidth: 60, sort: true, templet: function (d) {
                        return layui.util.toDateString(d.updateTime);
                    }

                }
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
                ]
            ]
            , page: {
			     limit: 10,
			     limits: [5, 10, 15, 20],
			
			 }
            , parseData: function (res) {
                      var result;

                      if (this.page.curr) {
                          result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                      } else {
                          result = res.data.slice(0, this.limit);
                      }
                      console.log(result)
                      return {
                          "code": res.code, //解析接口状态
                          "msg": res.msg, //解析提示文本
                          "count": res.count, //解析数据长度
                          "data": result //解析数据列表
                      };
                  }
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;
                case 'add':
                    var index = layer.open({
                            title: '增加用户',
                            /*如果是外部的html，type2，内部，type1*/
                            type: 1,
                            btnAlign: 'c',
                            area: ['400px', '340px'],
                            content: $("#add-main").html()
                            //未做的是去监听表单提交，给后台发送ajax请求
                        });
                    layui.use(['form','layer'],function(){
                    var form = layui.form;
                    	//监听表单提交
						form.on('submit(saveYF)', function(data){
						console.log(data.filed); 
		                $.ajax({
		                    url: "<%=basePath%>/outer/signcontractFHE/saveBuyerInfoFHE.action?contractID=" + ${requestScope.contractID},      //这里换成自己的url即可  ?contractID=${requestScope.contractID}
		                    method: "POST",
		                    dataType: "json",
		                    data: data.field,
		                    success: function (data) {
		                    	console.log("data="+data);
		                       if(data.result=='success'){
		                      	 	table.reload('test', {})
	                        		layer.msg(data.msg);
									layer.close(index);
								}else{
									alert(data.msg);
								} 
		                    },
		                    error: function (data) {
		                    	console.log(data);
		                        layer.msg(data.msg);
		                     
		                    }
		                });
		               //这个return非常重要，防止ajax跳转，去掉的话就不经过ajax了
		                return false;
        				});
                    });
                    break;
                case
                'batchDel'
                :
                    /*发送ajax请求到后台执行批量删除*/
                    break;
                case
                'flush'
                :
			       	table.reload('test', {})
                    break;
            }
            ;
        });

		

        //监听单元格编辑
        table.on('edit(test)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段

            $.ajax({
                url: '/user/update',      //换成自己的url
                type: 'POST',
                dataType: 'json',
                data: data,
                success: function (e) {
                    if (e.code == 0) {
                        layer.msg(e.msg);
                    } else {
                        layer.msg(e.msg);
                    }
                },
                error: function (e) {
                    layer.msg(e);
                }
            })
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                    $.ajax({
                        url: "<%=basePath%>/outer/signcontractFHE/deleteBuyerFHE2.action",    //换成自己的url
                        type: "POST",
                        dataType: "json",
                        data: {
                            serial: data.serial,
                            contractID:data.contractID
                        },
                        success: function (data) {
                           	if(data[0].result=='success'){
                        		layer.msg(data[0].msg);
								layer.close(index);
							}else{
							 	alert("error1"+data.msg);
							 	layer.msg(data[0].msg);
							} 
							table.reload('test', {})
		                    },
	                    error: function (data) {
	                    	alert("error2");
	                        layer.msg(data[0].msg);
	                     
	                    }
                    })
                });
            } else if (obj.event == 'add') {
            alert("obj.event == 'add'!")
                $.ajax({
                    url: "<%=basePath%>/outer/signcontractFHE/saveBuyerInfoFHE.action",      //这里换成自己的url即可
                    type: "POST",
                    dataType: "json",
                    data: data,
                    success: function (e) {
                        if (e.code == 0) {
                            layer.msg(e.msg);
                        } else {
                            layer.msg(e.msg);
                        }
                    },
                    error: function (e) {
                        layer.msg(e);
                    }
                })
            }
        });
        
 
        
        
    });  //layui
    
    //加载element模块
    layui.use('element', function () {
        var $=layui.jquery,  element = layui.element;
        //监听事件
        element.on('tab(tab-all)',function(e){
            var status2=$(this).attr('data-status');
			console.log(status2);
			//alert(status2);
        });
    });
        
    
    
</script>
</body>
</html>
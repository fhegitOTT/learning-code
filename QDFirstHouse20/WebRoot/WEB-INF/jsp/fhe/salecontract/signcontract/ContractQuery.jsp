 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<head>
    <meta charset="utf-8">
    <title>草签合同列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
	<%-- <script type="text/javascript" src="<%=basePath%>/js/application.js"></script>  --%>
   <link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all"/> 
</head>
<body style="background-color:#fff">
             <div class="layui-col-md16" align="center">
                 <form class="layui-form layui-form-pane" action="apllyForm" id="" name="apllyForm" align="center">
	                    <div class="layui-form-item" align="center">
	                         <label class="layui-form-label">合同编号</label>
	                         <div class="layui-input-inline">
	                             <input type="text" id="contractID" name="contractID" placeholder="请输入测绘申请编号"
	                                    autocomplete="off" class="layui-input" features="bmust=1,datatype=1,maxlength=12,showtitle=测绘申请号">
	                         </div>
	                         <label class="layui-form-label">项目编号</label>
	                         <div class="layui-input-inline">
	                             <input type="text" id="projectID" name="projectID" placeholder="请输入测绘项目编号<"
	                                    autocomplete="off" class="layui-input" > 
	                         </div>
	        
	                         <label class="layui-form-label">甲方</label>
	                         <div class="layui-input-inline">
	                             <input type="text" id="sellerName" name="sellerName" placeholder="请输入甲方"
	                                    autocomplete="off" class="layui-input" >
	                         </div>     
	                         <label class="layui-form-label">乙方</label>
	                         <div class="layui-input-inline">
	                             <input type="text" id="buyerName" name="buyerName" placeholder="请输入乙方"
	                                    autocomplete="off" class="layui-input" >
	                         </div>                            
	                    </div>
						<div class="layui-form-item">
                             <a class="layui-btn layui-btn-sm layui-bg-blue" data-type="reload" id="queryBtn">
                                 <i class="layui-icon">&#xe615;</i>查询</a>
                             <a class="layui-btn layui-btn-sm layui-bg-blue"  data-type="reset" id="reset">
                                 <i class="layui-icon">&#xe669;</i>重置</a>
                    	</div>
		                 </form>
		                 <hr/>
		             </div>
		             <div class="layui-col-md12" style="text-align: right">
		                 <button class="layui-btn layui-btn-sm layui-bg-red" id="delBtn"><i class="layui-icon">&#xe640;</i>删  除</button>     
		             	 <button class="layui-btn layui-btn-sm layui-bg-red" id="contractPrint"><i class="layui-icon">&#xe642;</i>合同打印预览</button>   
		             </div>
         		
	         <div class="layui-col-md12" style="text-align: right">
	              <table class="layui-hide"  id="targetTable" lay-filter="target"></table>
	         </div>

	<script type="text/javascript" src="<%=path%>/layui/layui.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/application.js"></script>
    <script>
        layui.use(['table', 'laydate', 'form'], function () {
            var table = layui.table;
            var form = layui.form;
            var $ = layui.$;
            table.render({
                elem: '#targetTable' //指定原始表格元素选择器 id的值
                // ,height: 'full-200' //容器高度
                , url: '<%=path%>/outer/signcontractFHE/queryContractListFHE.action' //数据接口
                , page: true //开启分页
                , cols: [
                    [ //表头
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'contractID', title: '合同号', width: 150, fixed: 'left', unresize: true, sort: true}
                    , {field: 'projectID', title: '项目编号'}
                    , {field: 'sellerName', title: '甲方'}     
                    , {field: 'buyerName', title: '乙方'}
                    , {field: 'location', title: '坐落'}     
                    , {field: 'status', title: '状态',templet: '<div><span title="{{d.statusStr}}";display:block;>{{d.statusStr}}</span></div>'}                          
                   ]
                ]
                , id: 'infoTable'
	            , parseData: function (res) {
	            		console.log(res);
                      if (this.page.curr) {
                          result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                      } else {
                      	result = res.data.slice(0,this.limit);
                      }
                      return {
                          "code": res.code, //解析接口状态
                          "msg": res.msg, //解析提示文本
                          "count": res.count, //解析数据长度
                          "data": result //解析数据列表
                      };
                  }
            });
            //reload表格，绑定传递额外参数作为查询条件
            var active = {
                reload: function () {
                    table.reload('infoTable', {
                       	page: {
                            curr: 1
                        }
                        , where: {
						contractID : $('#contractID').val(),
						projectID : $('#projectID').val(),
						sellerName : $('#sellerName').val(),
						buyerName : $('#buyerName').val(),
                        }
                    });
                     clearText()
                }
            };

            //查询按钮点击事件
            $('#queryBtn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
                
                    
            });
            
            function clearText(){
            alert("clearText")
             	$("#contractID").val("");
                $("#projectID").val("");
                $("#sellerName").val("");
                $("#buyerName").val("");
            }
            
            $('#reset').on('click', function () {
               clearText()
                table.reload('infoTable', {});
            });

            //确认签约按钮点击事件
            $('#editBtn').on('click', function () {
                // 监听表格单选框选择
                var checkStatus = table.checkStatus('infoTable');
                if (checkStatus.data.length == 0) {
                    layer.msg("请选择要签约的合同");
                    return;
                }
                var contractID=JSON.stringify(checkStatus.data[0].contractID);
                // alert(checkStatus.data)
                //alert("contractID="+JSON.stringify(checkStatus.data[0].contractID))
                layer.open({
                    type: 2,
                    area: ['100%', '100%'],
                    title: '确认签约',
                    skin: 'layui-layer-lan',
                    shadeClose: true,
                    content: '<%=path%>/outer/signcontractFHE/gotoSignContractFHE.action?contractID='+contractID
                });
            });

			//查询按钮点击事件
            $('#btShowSubmit').on('click', function () {
                // 监听表格单选框选择
                var checkStatus = table.checkStatus('infoTable');
                if (checkStatus.data.length == 0) {
                    layer.msg("请选择查询的申请案件");
                    return;
                }
                var applyID = checkStatus.data[0].applyID;
                //alert(id);

                layer.open({
                    type: 2,
                    area: ['75%', '85%'],
                    title: '案件信息   案件编号: ['+applyID+' ]',
                    skin: 'layui-layer-lan',
                    shadeClose: true, 
                    content: '../../survery/audit/gotoApplyAuditShow?curState=1&applyID='+applyID
                });
            });

            //删除按钮点击事件 tab页面样例
            $('#delBtn').on('click', function () {
               delUserConfirm()
            });
            

              $('#contractPrint').on('click', function () {
				disabledBt();	
   				
				// 监听表格单选框选择
				var checkStatus = table.checkStatus('infoTable');
				if (checkStatus.data.length == 0) {
					layer.msg("请选择要打印的合同");
					return;
				}
				var contractID=JSON.stringify(checkStatus.data[0].contractID);
				window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+contractID);

			 });
	
            //删除确认
			function delUserConfirm(){
				disabledBt();	
   				
				// 监听表格单选框选择
				var checkStatus = table.checkStatus('infoTable');
				if (checkStatus.data.length == 0) {
					layer.msg("请选择要删除的合同");
					return;
				}
				var contractID=JSON.stringify(checkStatus.data[0].contractID);
				layer.confirm("确定要删除吗？删除后不能恢复", { title: "删除确认" }, function (index) {
					$.ajax({
							url: '<%=path%>/outer/signcontractFHE/delContractIDFHE.action?contractID='+contractID,
							method: 'POST',
							data: {'applyID': checkStatus.data[0].applyID},
							dataType: "json",
							success: function (obj) {
								layer.msg(obj.msg, {time: 800}, function () {
									table.reload('infoTable', {
										page: {
											curr: 1
										}
										, where: {
											applyID: ''
										}
									});
								});
							}
						});
				},
				function(){
					enabledBt();
				});
			}
			
			
            //提交按钮点击事件
            $('#subBtn').on('click', function () {
            	disabledBt();
        	
                // 监听表格单选框选择
                var checkStatus = table.checkStatus('infoTable');
                if (checkStatus.data.length == 0) {
                    layer.msg("请选择要提交的申请");
                    return;
                }
                layer.confirm("确定要提交编号为 ["+checkStatus.data[0].applyID+"] 的测绘申请吗？", { title: "提交确认" }, function (index) {
	 				$.ajax({
	                        url: "../../survery/audit/saveApplyAudit?curState=0&auditResult=1&applyID="+checkStatus.data[0].applyID,
	                        type: "POST",
	                        success: function (res) {
	                            layer.msg(res.msg, {time: 800}, function () {
		                            table.reload('infoTable', {
		                                page: {
		                                    curr: 1
		                                }
		                                , where: {
		                                  applyID: ''
		                                }
		                            });
	                        	});
	                        }
	 				});
                 },
                 function(){
		    		enabledBt();
		    	});                    
            });
            
            //上传按钮点击事件 tab页面样例
            $('#uploadBtn').on('click', function () {
                // 监听表格单选框选择
                var checkStatus = table.checkStatus('infoTable');
                if (checkStatus.data.length == 0) {
                    layer.msg("请选择上传图片的用户");
                    return;
                }
                layer.open({
                    type: 2,
                    area: ['70%', '70%'],
                    title: '用户上传',
                    skin: 'layui-layer-lan',
                    shadeClose: true,
                    content: '../../demo/user/userIframe?userID='+checkStatus.data[0].userID
                });
            });

            table.on('tool(target)', function (obj) {
                var data = obj.data;
                if (obj.event === 'preview') {
                    $.ajax({
                        url: "../../demo/user/isExist?filename=" + encodeURI(data.description),
                        type: "POST",
                        success: function (res) {
                            if (res.code == 0) {
                                $('#preChart').attr('src', res.preChart);
                                index = layer.open({
                                    title: '图片预览',
                                    skin: 'layui-layer-lan',
                                    type: 1,
                                    content: $('#preDiv'),
                                    area: ['800px', '600px']
                                })
                                form.render();
                            } else {
                                layer.msg("文件不存在，请重新上传文件", {icon: 5});
                            }
                        }
                    });
                }
            });

            // 测绘收件申请点击按钮
            $('#applyFileBtn').on('click', function () {
                // 监听表格单选框选择
                var checkStatus = table.checkStatus('infoTable');
                if (checkStatus.data.length == 0) {
                    layer.msg("请选择要收件的测绘申请！");
                    return;
                }
                layer.open({
                    type: 2,
                    area: ['90%', '90%'],
                    title: '测绘收件',
                    skin: 'layui-layer-lan',
                    shadeClose: true,
                    content: '../../survery/apply/applyFileList?applyId='+checkStatus.data[0].applyID
                });
            });
            // 查看测绘收件点击按钮
            $('#applyFileCheckBtn').on('click', function () {
                // 监听表格单选框选择
                var checkStatus = table.checkStatus('infoTable');
                if (checkStatus.data.length == 0) {
                    layer.msg("请选择要查看的测绘申请！");
                    return;
                }
                layer.open({
                    type: 2,
                    area: ['90%', '90%'],
                    title: '测绘收件查看',
                    skin: 'layui-layer-lan',
                    shadeClose: true,
                    content: '../../survery/apply/checkApplyFileList?applyId='+checkStatus.data[0].applyID
                });
            });
        });
        
        
        
            //按钮无效
		    function disabledBt(){
		    	$('#btPass').addClass("layui-btn-disabled").attr("disabled",true);
		    	$('#btBack').addClass("layui-btn-disabled").attr("disabled",true);
		    }
		    
		    //按钮有效
		    function enabledBt(){
		    	$('#btPass').removeClass("layui-btn-disabled").attr("disabled",false);
		    	$('#btBack').removeClass("layui-btn-disabled").attr("disabled",false);
		    }
    </script>
</body>
<div id='preDiv' class="layui-card-body" style='display:none'>
    <form class="layui-form layui-form-pane" id="preForm" lay-filter="preForm" action="">
        <div class="layui-card" style="margin-bottom: 20px">
            <div class="layui-card-body">
                <div class="layui-row layui-col-space20" style="text-align: center">
                    <p style="padding-bottom: 10px"><img src="" id="preChart" style="max-width:100%"/></p>
                </div>
            </div>
        </div>
    </form>
</div>
</html>

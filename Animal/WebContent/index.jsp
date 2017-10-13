<%@page import="com.haha.model.Rule"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%
	String cp = request.getContextPath();
%>

<meta charset="utf-8">
<title>动物识别</title>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<%=cp%>/js/echarts.js"></script>
<script src="<%=cp%>/js/layer.js"></script>

<link rel="stylesheet" type="text/css" href="<%=cp%>/css/layer.css" />
		
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/homepage.css" />
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	
	
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button"
				data-toggle="collapse" data-target="#navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="#">动物识别</a>
		</div>

		<div id="navbar" class="navbar-collapse collapse navbar-left">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">管理 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#" id="addrule_btn">增加规则</a></li>
						<li><a href="#" id="delete_btn">删除规则</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="" id="addrule" style="display:none;">
		<form id="addrule_par" action="">
				<div style="padding-left:20px">
				<label>特征：</label><input style="width:300px;padding-left:20px" type="text" name="" id="conditions" value="" />
				</div>
				<div style="padding-left:20px">
				<label>结果：</label><input style="width:300px;padding-left:20px" type="text" name="" id="result" value="" />
				</div>
				
				<div style="padding-left:20px">
				<label>是否可以推出动物：</label>
				<select id="can_end">
				<option value="1">是</option>
				<option value="2">否</option>
				</select>
				</div>
		</form>
	</div>
	
	<div id="deleterule" style="display:none;">
		<div style="overflow:scroll;" id="features">
								<%
								ArrayList<Rule> rules = (ArrayList<Rule>)request.getAttribute("rules");
								//ArrayList<String> rules = (ArrayList<String>)request.getAttribute("rules"); %>
								<%
								for(Rule rule:rules){
									if(rule!=null){
										
									String rule_beshow = rule.getmConditionsStr()+"=>"+rule.getmResult();
								%>
									<div class="rule_beshow">
										<input name="rule_beshow" type="checkbox" value=<%=rule.getmId()%> class="delete_row"><%=rule_beshow%> 
									</div>
								<%
									}
								}
								%>
							
		</div>
					
	
	</div>
	
	<div class="container">

		<div class="row task-panel">

			<div class="col-sm-5 tasklist">
				<div class="task">
					<h3>动物特征</h3>
					<div style="overflow:scroll;" id="features">
								<%
								ArrayList<String> features = 
										(ArrayList<String>)request.getAttribute("features"); %>
								<%
								for(String feature:features){
									if(feature!=null){
								%>
									<div class="featurerow">
										<input name="feature" type="checkbox" value=<%=feature%> class="feature"><%=feature%> 
									</div>
								<%
									}
								}
								%>
							
					</div>
					
					
				</div>
			</div>

			<div class="col-sm-2 tasklist">
				<div class="inf">
					<a href="#" class="btn btn-success btn-lg btn-block button_add" id="inf">
							推理<span class="glyphicon glyphicon-arrow-right"></span>
					</a>
				</div>
			</div>
			
			<div class="col-sm-5 tasklist">
				<div class="task">
					<h3>结论</h3>
					
					<div id="min">
					<ol></ol>
					</div>
				</div>
			</div>
			
		<script>	
		
		
			$(document).ready(function() {
				
				$("#inf").click(function() {
					
					var obj=document.getElementsByName('feature');      
					  //取到对象数组后，我们来循环检测它是不是被选中    
					  var s='';    
					  for(var i=0; i<obj.length; i++){    
					    if(obj[i].checked)
					    		s+=obj[i].value+'+';
					  }    
					  //那么现在来检测s的值就知道选中的复选框的值了    
					 // alert(s==''?'你还没有选择任何内容！':s);   
					
					  $.post(
					    		"<%=cp%>/InferenceController",
					    		{"rules":s},
					    		function(msg){
					    			///alert(msg);
					    			//location.reload(); 
					    			// 这里是请求发送成功后的回调函数。
					    			// msg是返回的数据，数据类型在type参数里定义！
					    			//alert(msg);
					    			if(msg=="0")
					    				alert("特征不足，无法推理");
					    			else if(msg=="2"){
					    				$("ol").empty();
					    				alert("特征冲突，请检查所选特征");
					    			}
					    			else{
					    			$("ol").empty();
					    			var objj = eval(msg);
					    			for(var i=0;i<objj.length;i++){
					    				var conditions = objj[i].conditions;
					    				var result=objj[i].result;
					    				//alert(conditions);
					    				$("ol").append("<li>"+conditions+"=>"+result+"</li>");
					    			}
					    			}
					    		}
					    		// 默认返回字符串，设置值等于json则返回json数据
					);
					  
				});
				
				
				
				
			});
			  ///////////////////////////////////////////////////////////////////////////
			$(document).ready(function() {
				$("#addrule_btn").click(function() {
					layer.open({
						title:"增加规则",
						shade: 0,
						type: 1,
						closeBtn: 1,
						
						area: '450px',
						//shadeClose: true,
						shadeClose: true,
						content: $("#addrule"),
						btn: ['确定','取消'],
						yes: function(index, layero){
							//alert("click");
							var conditions_obj = document.getElementById("conditions");
							var result_obj = document.getElementById("result");
							var can_end = $('#can_end option:selected').val();//选中的值
							//var options=$("#test option:selected");
							
							var conditions = conditions_obj.value;
							var result = result_obj.value;
							alert("get "+can_end + " conditions=" + conditions_obj.value + " result=" + result_obj.value);
							
							 $.post(
							    		"<%=cp%>/ManagerController",
							    		{"conditions":conditions,
							    			"result":result,
							    			"can_end":can_end,
							    			"operation":"add"
							    				},
							    		function(msg){
							    			//alert(msg);
							    			location.reload(); 
							    		}
							    		// 默认返回字符串，设置值等于json则返回json数据
							);
							
							
							
  						},
  						no: function(index, layero){
    						layer.close(index); //如果设定了yes回调，需进行手工关闭
  						}
					});
				})
				
			});
			
			$(document).ready(function() {
				$("#delete_btn").click(function() {
					layer.open({
						title:"规则库",
						shade: 0,
						type: 1,
						closeBtn: 1,
						area: '450px',
						//shadeClose: true,
						shadeClose: true,
						content: $("#deleterule"),
						btn: ['删除','取消'],
						yes: function(index, layero){
							var obj=document.getElementsByName('rule_beshow');      
							  //取到对象数组后，我们来循环检测它是不是被选中    
							  var idstr='';    
							  for(var i=0; i<obj.length; i++){    
							    if(obj[i].checked)
							    		idstr+=obj[i].value+'+';
							  }  
							  alert(idstr);
							  $.post(
							    		"<%=cp%>/ManagerController",
							    		{
							    			"idstr":idstr,
							    			"operation":"delete"
							    				},
							    		function(msg){
							    			//alert(msg);
							    			location.reload(); 
							    		}
							    		// 默认返回字符串，设置值等于json则返回json数据
							);
							
							//$("").submit();
    						//layer.close(index); //如果设定了yes回调，需进行手工关闭
  						},
  						no: function(index, layero){
    						layer.close(index); //如果设定了yes回调，需进行手工关闭
  						}
					});
				})
				
			});
			
			
			
			
			
			
			  
			///////////////////////////////////////////////////////////////////
			
			
			
		</script>
			
		</div>

	</div>

	
</body>
</html>

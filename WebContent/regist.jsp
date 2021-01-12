<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>优就业-线医疗管理系统</title>
	<meta charset="UTF-8">
	<link rel="icon" href="Images/logo_favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="Js/jquery.validate.js"></script>
    <script type="text/javascript" src="Js/messages_zh.js"></script>
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-family: "微软雅黑";
            background-color: buttonhighlight;
        }

        .form-signin {
            max-width: 600px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            background: rgba(255,255,255,0.5);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
            font-size: 24px;
            margin-left: 90px;
        }
        
        .form-signin .form-signin-heading{
        	margin-bottom: 10px;
            font-size: 24px;
            margin-left: 200px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
		
		
		#message{
			font-size: 14px;
			color:red;
			margin-left: 40px;
		}
		
		.input-block-level{
			width: 300px;
			margin-left: 40px;
		}
		.input-medium{
			margin-left: 40px;
		}
		.code_images{
			width: 115px;
			height: 35px;
			margin-top: -15px;
			margin-left: 10px;
		}
		.error{
			color: red;
			font-size: 14px;
		}
		
    </style>  
<script type="text/javascript">
	$(function(){
		//利用validate插件，进行数据验证
		$(".form-signin").validate({
			rules:{
				"uname":{
					//必填字符，不能为空
					required:true,
					//字段范围
					rangelength:[6,12],
					//配合ajax验证，检验账号是否在数据库中已经存在
					remote:{
							url:"UserServlet",
							type:"post",
							//由于返回的是Boolean类型，所以用json类型
							dataType:"json",
							//返回的数据
							data:{
								//调用checkUname方法
								"method":"checkUname",
								//前面是key，后面是value，这里得到的值是uname的值而且必须用function函数
								"uname":function(){
									return $("input[name=uname]").val();
								}
							}
					} 
				},
				"pwd":{
					required:true,
					rangelength:[8,14]
				},
				"pwd2":{
					required:true,
					//与前面的password相等
					equalTo:"#password"
				},
				"username":{
					required:true,
				},
				"uemail":{
					required:true,
					email:"email",
					remote:{
						url:"UserServlet",
						type:"post",
						dataType:"json",
						data:{
							"method":"checkUemail",
							"uemail":function(){
								return $("input[name=uemail]").val();
							}
						}
					} 
				}
			},
			messages:{
				"uname":{
					required:"账号不能为空",
					rangelength:"用户名是6到12个字符",
					remote:"账号已被占用"
				},
				"pwd":{
					required:"密码不能为空",
					rangelength:"密码是8到14个字符"
				},
				"pwd2":{
					required:"确认密码不能为空",
					equalTo:"两次输入密码必须一致"
				},
				"username":{
					required:"用户名不能为空",
				},
				"uemail":{
					required:"邮箱不能为空",
					email:"邮箱格式不正确",
					remote:"邮箱已被占用"
				}
			},
			//数据验证成功之后自动执行的方法
			submitHandler:function(){
				//表单序列化获取表单中的所有数据
				var data = $(".form-signin").serialize();
				//利用ajax进行成功或失败提示，并且进行页面跳转
				$.post("UserServlet",data,function(obj){
					if(obj){
						alert("注册成功，点击后跳转至登录页面");
						//window是BOM（浏览器对象模型）的核心对象，使用时也可以省略，location是其地址对象，调用href方法可以进行页面的跳转
						window.location.href = "login.jsp";
					}else{
						alert("注册失败，请稍后再试");
					}
				},"json");
			}
		});
	});
</script>
</head>
<body>
<div class="container">	
    <form class="form-signin">
    	<input type="hidden" name="method" value="regist"/>
        <h2 class="form-signin-heading" >管理员注册</h2>
                        帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
		<input type="text" name="uname" class="input-block-level" placeholder="账号" />
		<br/>
                        密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
        <input id="password" type="password" name="pwd" class="input-block-level" placeholder="密码" />
        <br/>
                       确认密码：<input type="password" name="pwd2" class="input-block-level" placeholder="确认密码" />
        <br/>
                        用&nbsp;&nbsp;户&nbsp;&nbsp;名：<input type="text" id="username" name="username" class="input-block-level" placeholder="用户名" />
        <span id="username_msg"></span><br/>
                        邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" id="email" name="uemail" class="input-block-level" placeholder="邮箱" />
        <span id="email_msg"></span><br/>               
        <p style="text-align: center;">
        <input id="login" type="submit" value="注册" name="login" class="btn btn-large btn-info" style="width: 100px;"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="reset" type="reset" value="清空" name="login" class="btn btn-large btn-info" style="width: 100px;"/>
        </p>
    </form>
</div>

<script type="text/javascript">
	
</script>
</body>
</html>
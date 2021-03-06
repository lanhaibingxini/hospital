<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=this.getServletContext().getContextPath() %>/register/">
    <title>挂号</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="../Js/jquery-3.4.1.js"></script>
 
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
    <script type="text/javascript">
    	$(function(){
    		//保存
    		$("#save").click(function(){
    			var data = $("form").serialize();
    			$.post("../RegisterServlet",data,function(obj){
    				if(obj){
    					alert("修改成功");
    					window.location.href = "../RegisterServlet?method=getRegisterByCurrentPage";
    				}else{
    					alert("修改失败");
    				}
    			},"json");
    		});
    		//自动获取默认的急诊科的医生的信息
        	$.post("../DoctorServlet",{"method":"getDoctorByDepartment","department":"1"},function(obj){
        		var doctor = $("#doctor");
        		$(obj).each(function(index,content){
        			var option = "<option value="+content.did+">"+content.dname+"</option>";
        			doctor.append(option);
        		});
        	},"json");
        	//让医生随着部门的变化而变化
    		$("#department").change(function(){
    			var doctor = $("#doctor");
    			doctor.html("");
    			//获取选择的科室
        		var department = $(this).val();
    			$.post("../DoctorServlet",{"method":"getDoctorByDepartment","department":department},function(obj){
    				$(obj).each(function(index,content){
    					var option = "<option value="+content.did+">"+content.dname+"</option>";
    					doctor.append(option);
    				});
    			},"json");
    		});
    	});
    </script>
</head>
<body>
<form class="definewidth m20">
<input name="method" value="updateRegister" type="hidden">
<input name="rid" value="${r.rid }" type="hidden">

<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="rname" value="${r.rname }"/></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" name="idCard" value="${r.idCard }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td><input type="text" name="siNumber" value="${r.siNumber }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td><input type="text" name="registerMoney" value="${r.registerMoney }"/>元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td><input type="text" name="phone" value="${r.phone }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td>
        	<input type="radio" name="isPay" value="1" ${r.isPay==0?'checked':'' }/>否&nbsp;&nbsp;&nbsp;
            <input type="radio" name="isPay" value="0" ${r.isPay==1?'checked':'' }/>是</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="sex" value="0" ${r.sex==0?'checked':'' }/>男&nbsp;&nbsp;&nbsp;
            <input type="radio" name="sex" value="1" ${r.sex==1?'checked':'' }/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="age" value="${r.age }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td>
        	<input type="radio" name="consultation" value="0" ${r.consultation==0?'checked':'' }/>初诊&nbsp;&nbsp;&nbsp;
            <input type="radio" name="consultation" value="1" ${r.consultation==1?'checked':'' }/>复诊
         </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>
        	<select name="department" id="department">
        		<option value="1" ${r.doctor.department==1?'selected':'' }>急诊科</option>
        		<option value="2" ${r.doctor.department==2?'selected':'' }>儿科</option>
        		<option value="3" ${r.doctor.department==3?'selected':'' }>妇科</option>
        		<option value="4" ${r.doctor.department==4?'selected':'' }>皮肤科</option>
        		<option value="5" ${r.doctor.department==5?'selected':'' }>内分泌科</option>
        		<option value="6" ${r.doctor.department==6?'selected':'' }>牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>
        	<select name="did" id="doctor">
	        	<c:forEach var="d" items="${list }">
					<option value="${d.did }" ${r.doctor.did==d.did?'selected':'' }>${d.dname }</option>
				</c:forEach>
	        </select>
	     </td>  
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark">${r.remark }</textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button class="btn btn-primary" type="button" id="save">保存</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>
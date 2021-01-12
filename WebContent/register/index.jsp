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
    <title>门诊查询</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

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
			 $("#newNav").click(function(){
				window.location.href = "add.jsp";
			 });
		});
    </script>
</head>
<body>

<form action="../RegisterServlet" method="post" class="definewidth m20">
<input name="method" value="getRegisterByCurrentPage" type="hidden"/>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" id="rid" name="rid" value=""/></td>
		
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" id="name" name="rname" value=""/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td>
        	<select name="department" id="department">
	        	<option value="0" >==请选择==</option>
	        	<option value="1" >急诊科</option>
	        	<option value="2" >儿科</option>
	        	<option value="3" >妇科</option>
	        	<option value="4" >皮肤科</option>
	        	<option value="5" >内分泌科</option>
	        	<option value="6" >牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
		  <td colspan="6">
		  <center>
            <input id="find" name="find" type="submit" class="btn btn-primary" value="查询"/>
			<input name="ret" id="ret" type="button" class="btn btn-primary" value="清空"/>
            </center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>病例号</th>
        <th>病人姓名</th>
        <th>主治医生</th>
        <th>挂号时间</th>
        <th>挂号科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach var="r" items="${list }">
    		<tr>
	    		<td><input type="checkbox" id="checkone" value="${r.rid }"></td>
	    		<td>${r.rid }</td>
	    		<td>${r.rname }</td>
	    		<td>${r.doctor.dname }</td>
	    		<td>${r.rtime }</td>
	    		<td>
	    			<c:if test="${r.doctor.department==1 }">急诊科</c:if>
	    			<c:if test="${r.doctor.department==2 }">儿科</c:if>
	    			<c:if test="${r.doctor.department==3 }">妇科</c:if>
	    			<c:if test="${r.doctor.department==4 }">皮肤科</c:if>
	    			<c:if test="${r.doctor.department==5 }">内分泌科</c:if>
	    			<c:if test="${r.doctor.department==6 }">牙科</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${r.statu==0 }">已挂号</c:if>
	    			<c:if test="${r.statu==1 }">已就诊</c:if>
	    			<c:if test="${r.statu==2 }">已出院</c:if>
	    		</td>
	    		<td>
	    			<a href="">删除</a>
	    			<a href="../RegisterServlet?method=getRegisterByRid&rid=${r.rid }&type=edit">编辑</a>
	    			<a href="../RegisterServlet?method=getRegisterByRid&rid=${r.rid }&type=look">详情</a>
	    		</td>
    		</tr>
    	</c:forEach>
     </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  		<div class="inline pull-right page">
	          <a href="../RegisterServlet?method=getRegisterByCurrentPage&currentPage=1" >首页</a> 
	          <a href="../RegisterServlet?method=getRegisterByCurrentPage&currentPage=${pt.prePage }">上一页</a>     
	          <a href="../RegisterServlet?method=getRegisterByCurrentPage&currentPage=${pt.nextPage }" >下一页</a> 
	          <a href="../RegisterServlet?method=getRegisterByCurrentPage&currentPage=${pt.totalPages }" >尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'> ${pt.totalCount } </span>条记录
			     <span class='current'> ${pt.currentPage }/${pt.totalPages } </span>页
		</div>
		<div>
		   <button type="button" class="btn btn-success" id="newNav">门诊挂号</button>&nbsp;&nbsp;&nbsp;
		   <button type="button" class="btn btn-success" id="delRegister" onclick="delAll()">批量删除</button>
		</div>
	</th></tr>
  </table>
  
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
	String imgPath=request.getScheme()+"://"+request.getServerName()+":"+
			request.getServerPort()+"/Hospital-pic/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>修改药品</title>
    <base href="<%=this.getServletContext().getContextPath() %>/medicine/">
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
    $(function () {       
		$('#backid').click(function(){
				window.location.href="${path}medicine?method=findMedicineByPage";
		 });
		
		//保存
		$("#save").click(function(){
			var data = new FormData($("form")[0]);
			$.ajax({
				url:"../medicineServlet",
				type:"post",
				data:data,
				cache:false,
				processData:false,
				contentType:false,
				dataType:"json",
				success:function(obj){
					if(obj){
						alert("修改成功");
						window.location.href = "../medicineServlet?method=getMedicineByCurrentPage";
					}else{
						alert("修改失败");
					}
				}
			});
		});
    });
    </script>
</head>
<body>
<form class="definewidth m20" enctype="multipart/form-data">
<input type="hidden" name="method" value="updateMedicine">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td><input type="text" name="mid" value="${medicine.mid }" readonly="readonly"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">图片</td>
        <td>
        <input type="hidden" name="oldpicture" value="${medicine.picture }"/>
        <input type="file" name="picture"/>
        <img  width="80px" height="50px" src="http://localhost:8080/medicine/${medicine.picture }">
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">进价</td>
        <td><input type="text" name="inPrice" value="${medicine.inPrice }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">售价</td>
        <td><input type="text" name="salPrice" value="${medicine.salPrice }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td><input type="text" name="mname" value="${medicine.mname }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品类型</td>
        <td>
        	<input type="radio" name="type" value="1" ${medicine.type==1?'checked':'' }/>处方药&nbsp;&nbsp;&nbsp;
        	<input type="radio" name="type" value="2" ${medicine.type==2?'checked':'' }/>中药&nbsp;&nbsp;&nbsp;
        	<input type="radio" name="type" value="3" ${medicine.type==3?'checked':'' }/>西药
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">简单描述</td>
        <td><input type="text" name="descs" value="${medicine.descs }"/></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">保质期</td>
        <td><input type="text" name="qualityDate" value="${medicine.qualityDate }"/>月</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">详细描述</td>
        <td><textarea name="description">${medicine.description }</textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产厂商</td>
        <td><textarea name="produceFirm">${medicine.produceFirm }</textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服用说明</td>
        <td><input type="text" name="readme" value="${medicine.readme }"/></td>
    </tr>
   
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark">${medicine.remark }</textarea></td>
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
<%@page import="com.gtc.dao.CURD"%>
<%@page import="com.gtc.factory.CURDFactory"%>
<%@page import="com.gtc.domain.Contact"%>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>通讯录详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
   
  <body>
  <%

  		String html="";
  		Contact contact=(Contact)session.getAttribute("detail");
  		if (contact==null){
  			html+="非法数据";
  			out.write(html);
  			}

	
		out.print(html);
	 %>
		<div id="continer">

	 	<form action="/2018Day11_Contact/UpdateContact"  method="post" >
	 	
  		<table  align='center' id='contactTable' border='1'  width='400px' style='border-collapse: collapse;margin-top: 150px' > 
  		<tr><td width='30%'>姓名</td><td  width='700%' id="tdName" ondblclick="changeCurDis('nameInput','tdName')"><input class="tdInput" id="nameInput"  name="name" type="text" value="${detail.name}" >${detail.name}</td></tr>
  		<tr><td>性别</td><td id="tdGender" ondblclick="changeCurDis('genderInput','tdGender')"><input class="tdInput"  id="genderInput" name="gender" type="text" value="${detail.gender}" >${detail.gender}</td></tr>
  		<tr><td>电话</td><td id="tdPhone" ondblclick="changeCurDis('phoneInput','tdPhone')"><input  class="tdInput" id="phoneInput"  name="phone" type="text" value="${detail.phone}" >${detail.phone}</td></tr>
  		<tr><td>Email</td><td id="tdEmail" ondblclick="changeCurDis('emailInput','tdEmail')"><input  class="tdInput" id="emailInput" name="email" type="text" value="${detail.email}" >${detail.email}</td></tr>
  		<tr><td>地址</td><td id="tdAddr" ondblclick="changeCurDis('addrInput','tdAddr')"><input  class="tdInput" id="addrInput" name="address" type="text" value="${detail.address}" >${detail.address}</td></tr>
  		</table>
	 	<input type="hidden" name="id" value="${detail.id}">
			
		<div id="buttonDiv"><input type="submit" class="btn"  value="保存修改"><input type="button" id="del" class="btn" value="删除" onclick="deleteContact()">
		<input type="button" id="cancel"  class="btn" value="放弃修改" onclick="cancelFunc()"><input type="button" id="back"  class="btn" value="返回" onclick="goBack()">
		</div>
	    </form>
	       	 <jsp:useBean id="con2" class="com.gtc.domain.Contact">
    	 </jsp:useBean>
    	 <jsp:setProperty property="name" name="con2" value="abc" />

  
 	</div>
  
    
  </body>
  
  <style type="text/css">
body {
	background-color: #949494;
	text-align:center;
	line-height: 20px;
	font-weight: bold;
	font-family: "Courier New", Courier, monospace;
	font-size: 14px;
}



.tableRow{

		color: #0000ff;
	
}

.tableRow:hover{
	color:#6F0;
	text-decoration:none;			

}


.tdInput{
	background-color: #949494;
	border: 0px;					
	outline:none;						/**去除input边框 **/

}

.btn{
	width:100px;
	float: left;

}

#continer{
	width:400px;
	position: relative;
	margin-left: 30%;
	margin-top: 15%;
	
	
	}


#buttonDiv{
	width:400px;
	float:inherit;
	padding: 0;

	}

</style>
<script type="text/javascript">

	initialTable();
	
//使用javaScript进行跳转	
function update(id){

	location.href="/2018Day11_Contact/detail.html?id="+id;
	
}

function goBack(){

	location.href="/2018Day11_Contact/contact.html";
}

function deleteContact(){
	
	var result=window.confirm("确定要删除这条记录吗?");
	if(result){		
		var param="/2018Day11_Contact/RemoveContact?"+"id="+${detail.id};
		location.href=param;
	}
}


function initialTable(){

	
	hiddenWidget("nameInput");
	hiddenWidget("genderInput");
	hiddenWidget("phoneInput");
	hiddenWidget("emailInput");
	hiddenWidget("addrInput");
}

//把td中的显示text内容变成可编辑内容
function changeCurDis(DisplayId,hidderId){
	var tdText=document.getElementById(hidderId);
	tdText.innerHTML=tdText.innerHTML.toString().split(">")[0]+">";
	dispalyWidget(DisplayId);
	
}

function hiddenWidget(id){
	document.getElementById(id).style.display="none";
}
function dispalyWidget(id){
	document.getElementById(id).style.display="";
}


function saveUpdate(){
	
	
	//把数据通过get方式回传
	var param="/2018Day11_Contact/UpdateContact?name="+document.getElementById("nameInput").value+"&id="+${detail.id}+"&phone="+document.getElementById("phoneInput").value+"&email="+document.getElementById("emailInput").value+"&address="+document.getElementById("addrInput").value+"&gender="+document.getElementById("genderInput").value;
	location.href=param;
}

function cancelFunc(){
	location.reload();
}

</script>
  
</html>

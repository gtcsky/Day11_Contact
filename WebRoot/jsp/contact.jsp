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
    
    
    <title>通讯录</title>
    
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
  		CURD curd=CURDFactory.newInstace();
		Contact  contact=new Contact();
		List<Contact> info=curd.getAllInfo(Contact.class);
  
  		String html="";
  		html+="<div id='continer'><div><form action='/2018Day11_Contact/AddContact'method='post' onsubmit='return addButtonFunc()' >";
  		html+="<table  align='center' id='contactTable' border='1'   width=800px style='border-collapse: collapse;margin-top: 1px' >";
  		info=(List<Contact>)session.getAttribute("contactInfo");
  		html+="<tr><th width='15%'>姓名</th><th  width='10%'>性别</th><th  width='15%'>电话</th><th  width='30%'>地址</th><th  width='30%'>邮箱</th></tr>";
		for(Contact con:info){
  		html+="<tr class='tableRow' onclick='update("+con.getId()+")'><td>"+con.getName()+"</td><td>"+con.getGender()+"</td><td>"+con.getPhone()+"</td><td>"+con.getAddress()+"</td><td>"+con.getEmail()+"</td></td>";
  		}
		html+="</table>";
		html+="<span id='warningSpan'></span>";
  		html+="</div><div><input type='submit' id='addBtn' value='添加'></div>";
		
		html+="</form></div>";
		out.print(html);
	 %>
  		
  </body>
  
  <style type="text/css">
body {
	background-color: #949494;
	line-height: 20px;
	font-weight: bold;
	font-family: "Courier New", Courier, monospace;
	font-size: 14px;
}

td{
	height: 20px;
}

.tableRow{

		color: #0000ff;
	
}

.tableRow:hover{
	color:#6F0;
	text-decoration:none;	

}


#continer{
	width:400px;
	position: relative;
	margin-left: 30%;
	margin-top: 15%;
	
	
	}

input[type='text']{
	background: #949494;
	border: 0px;
	outline: none;
	

}

#addBtn{
	width: 60px;

	position: relative;
	
}

#s_Gender{
	color: #0000ff;
	background-color:#949494;
	width: 100%;

}

</style>
<script type="text/javascript">

function update(id){

	location.href="/2018Day11_Contact/detail.html?id="+id;
	
}


function addButtonFunc(){
	var btn=document.getElementById("addBtn");
	if("添加"==btn.value){
		btn.value="提交";
		var tr=document.createElement("tr");
		var td=document.createElement("td");
		td.innerHTML="<input type='text' id='addName' name='name' onblur=' nameCheck(this)'  maxlength='15'>";
		td.setAttribute("width",'15%' );
		var td2=document.createElement("td");
		td2.setAttribute("width",'5%' );
		td2.innerHTML="<select name='gender' id='s_Gender'><option value='男'>男</option><option value='女'>女</option></select>";
		var td3=document.createElement("td");
		td3.setAttribute("width","15%");
		td3.innerHTML="<input type='text'id='addPhone' name='phone'  onblur='phoneCheck(this)'>";
		var td4=document.createElement("td");
		td4.setAttribute("width","30%");
		td4.innerHTML="<input type='text' id='addAddress' name='address' onblur='addressCheck(this)'>";
		var td5=document.createElement("td");
		td5.setAttribute("width","30%");
		td5.innerHTML="<input type='text'id='addEmail' name='email' onblur='emailCheck(this)'>";
		tr.appendChild(td);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		
		document.getElementById("contactTable").appendChild(tr);
		return false;
		}

	else{
		
		//nameCheckById("addName");
		//phoneCheckById("addPhone");
		//addressCheckById("addAddress");
		//emailCheckById("addEmail");
	//	alert(nameCheckById("addName"));
	//	alert(phoneCheckById("addPhone"));
	//	alert(addressCheckById("addAddress"));
	//	alert(emailCheckById("addEmail"));



		return (nameCheckById("addName")&&phoneCheckById("addPhone")&&addressCheckById("addAddress")&&emailCheckById("addEmail"));
	}
	alert("come end")
	return false;
}

</script>
<script src="/2018Day11_Contact/js/inputCheck.js" type="text/javascript"></script>  
</html>

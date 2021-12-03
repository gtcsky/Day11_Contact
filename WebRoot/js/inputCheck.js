

function nameCheckById(id){
	
	warning("warningSpan","");
	o=document.getElementById(id);
	return nameCheck(o);
}
function nameCheck(o){
	var input=o.value;
	/* var regex=/[\u4e00-\u9fa5]/g */
	var regex=/[^\x00-\xff]/g;				/*^表示'非',即0x00~0xff之外的字符,如中文字符. /g表示全文匹配 */
	
	//alert(regex.test(input))
	var nameTransfered=input.replace(regex, 'aa');//把字符串input的0x00~0xff之外的字符替换成两个a(中文字当两个字符占位),在获取得到的字符串的长度
	var len=nameTransfered.length;	
	if(len>=4&& len<16){
		regex=/\W+/;								//0~9 a~z A~Z之外的字符一个字符
		if(regex.test(nameTransfered))
			{
			warning("warningSpan","包含汉字和字母,数字之外的字符");
			return false;
			}
		
		//warning("warningSpan","姓名输入有效");
		return true;
		
	}
	else{
		if(len<4)
			warning("warningSpan","用户名小于4位");
		else
			warning("warningSpan","用户名大于15位");
		return false;
	}
	

}

//校验电话号码的合法性
function phoneCheckById(id){
	
	o=document.getElementById(id);
	
	return phoneCheck(o);
}

//校验电话号码的合法性
function phoneCheck(o){
	
	warning("warningSpan","");
	var regex=/^\d{6,15}$/;
	var input=o.value;
	input=input.replace(/-/g, "");			//消除电话号码中的分隔符'-'
	
	if(!regex.test(input)){
		warning("warningSpan","电话号码只能包含6~15的数字");
		return false
	}
	return true
}


//校验地址的合法性
function addressCheckById(id){
	
	o=document.getElementById(id);
	
	return addressCheck(o);
}

function addressCheck(o){
		warning("warningSpan","");
		var input=o.value;
		var input=input.replace(/[^\x00-\xff]/g,'**');
		var len=input.length; 
		if(len<4||len>150){
			warning("warningSpan","地址过长或过短");
			return false;
		}
		return true;
}


//校验地址的合法性
function emailCheckById(id){
	
	o=document.getElementById(id);
	
	return emailCheck(o);
}

function emailCheck(o){
	
	warning("warningSpan","");
	var email=o.value;
	var regex=/^\w{3,12}@\w{1,15}[\.\w{2,10}]+$/;
	if(!regex.test(email)){
		
		
		warning("warningSpan","不合法的邮件地址");
		return false;
	}
	return true;
}

function warning(id,msg){
	
	document.getElementById(id).innerHTML=msg.fontcolor('red');
	
}	
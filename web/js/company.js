var contextPath = "";

/**
 * 装修公司表t_company	的注册			
 * @returns
 */
function company_Reg(){
	var compname = $("#compname").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var passwords = $("#passwords").val();
	var name = $("#name").val();
	var phone = $("#phone").val();
	var error = $("#error");
	if(compname == null || compname.trim() == ""){
		error.text("公司名称不能为空");
		return;
	}
	if(email == null || email.trim()== ""){
		error.text("公司邮箱不能为空");
		return;
	}
	var check = checkEmail(email);
	if(check == false) {
		error.text("邮箱格式错误");
		return;
	}
	if(password == null || password.trim()== ""){
		error.text("密码不能为空");
		return;
	}
	if(password.toString().length < 6 || password.toString().length > 16 ){
		error.text("密码必须在6-16之间");
		return;
	}
	if(passwords == null || passwords.trim()== ""  ){
		error.text("确认密码不能为空");
		return;
	}
	if( name == null || name.trim()==""  ){
		error.text("负责人名字不能为空");
		return;
	}
	if( phone == null || phone.trim()== ""){
		error.text("电话不能为空");
		return;
	}
	if(isNaN(phone)) {
		error.text("电话只能输入数字");
		return;
	}
	if(phone.toString().length != 11) {
		error.text("电话号码只能输入11位数字");
		return;
	}
	if(password != passwords){
		error.text("您输入的密码不一致");
		return;
	}
	$.post(contextPath + "/company/register", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "注册成功") {
					window.location = contextPath + "/company/loginpage";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
	
}

/**
 * 装修公司表t_company	的登入			
 */
 
function company_login(){
	var email = $("#email").val();
	var password = $("#password").val();
	var error = $("#error");
	if(email == null || email.trim()== ""){
		error.text("公司邮箱不能为空");
		return;
	}
	var check = checkEmail(email);
	if(check == false) {
		error.text("邮箱格式错误");
		return;
	}
	if(password == null || password.trim()== ""){
		error.text("密码不能为空");
		return;
	}
	$.post(contextPath + "/company/login", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "登入成功") {
					window.location = contextPath + "/company/home";
					return;
				} else if(data.error == "等待审核") {
					window.location = contextPath + "/company/wait";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
	
}

/**
 * 装修公司表t_company 的信息修改
 */
function company_update1(){
	var name = $("#name").val();
	var email = $("#email").val();
	var address = $("#address").val();
	var phone = $("#phone").val();
	var principal = $("#principal").val();
	var longitude = $("#longitude").val();
	var latitude = $("#latitude").val();
	var openTime = $("#opendate").val();
	var tel = $("#tel").val();
	var error = $("#error");
	if(name == null || name.trim()== ""){
		error.text("公司名称不能为空");
		return;
	}
	if(email == null || email.trim()== ""){
		error.text("公司邮箱不能为空");
		return;
	}
	var check = checkEmail(email);
	if(check == false) {
		error.text("邮箱格式错误");
		return;
	}
	if(phone == null || phone.trim()== ""){
		error.text("手机号码不能为空");
		return;
	}
	if(isNaN(phone)) {
		error.text("电话只能输入数字");
		return;
	}
	if(phone.toString().length != 11) {
		error.text("电话号码只能输入11位数字");
		return;
	}
	if(tel == null || tel.trim() == "") {
		
	} else {
		if(isNaN(tel)) {
			error.text("电话只能输入数字");
			return;
		}
		if(tel.toString().length != 11) {
			error.text("电话号码只能输入11位数字");
			return;
		}
	}
	if(principal == null || principal.trim()== ""){
		error.text("负责人不能为空");
		return;
	}
	if(openTime == null || openTime.trim()== ""){
		error.text("成立日期不能为空");
		return;
	}
	if (isNaN(longitude))
    {
    	error.text("经度应该输入数字");
        return;
    }
    if (isNaN(latitude))
    {
    	error.text("纬度应该输入数字");
        return;
    }
    if(longitude<0 || longitude>180)
    {
    	error.text("经度范围输入错误");
        return;
    }
    if(latitude<0 || latitude>90)
    {
    	error.text("纬度范围输入错误");
        return;
    }
	$.post(contextPath + "/company/update", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "更改成功") {
					window.location = contextPath + "/company/home";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
	
}

/**
 * 装修公司表t_company 的密码修改
 *
 */

function company_pwd(){
	var password = $("#password").val(); 
	var passwords = $("#passwords").val();
	var passwordes = $("#passwordes").val();
	var error = $("#error");
	if(password == null || password.trim()== ""){
		error.text("原密码不能为空");
		return;
	}
	if(passwords == null || passwords.trim()== ""){
		error.text("新密码不能为空");
		return;
	}
	if(passwordes == null || passwordes.trim() == "") {
		error.text("确认密码不能为空");
		return;
	}
	if(passwords.toString().length < 6 || passwords.toString().length > 16){
		error.text("密码范围只能在6-16之间");
		return;
	}
	if(password == passwords) {
		error.text("新密码不能与原密码一致");
		return;
	}
	if(passwordes != passwords) {
		error.text("新密码与确认密码不一致");
		return;
	}
	$.post(contextPath + "/company/pwd", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "密码更改成功") {
					window.location = contextPath + "/company/home";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
	
}


/**
* 判断邮箱格式是否正确
* @param strEmail
* @returns
*/
function checkEmail(strEmail) {
var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
if(emailReg.test(strEmail)){
	return true;
} else {
	return false;
}
}
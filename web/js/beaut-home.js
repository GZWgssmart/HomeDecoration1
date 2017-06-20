var contextPath = "";

/**
 * 设计师注册前后台验证
 * @returns
 */
function des_reg() {
		var email = $("#email").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var passwords = $("#passwords").val();
		var phone = $("#phone").val();
		var error = $("#error");
		var check = checkEmail(email);
		if(email == null || email.trim() == "") {
			error.text("邮箱不能为空");
			return;
		}
		if(check == false) {
			error.text("邮箱格式错误");
			return;
		}
		if(name == null || name.trim() == "")　{
			error.text("昵称不能为空");
			return;
		}
		if(password == null || password.trim() == "") {
			error.text("密码不能为空");
			return;
		}
		if(password.toString().length < 6 || password.toString().length > 16){
			error.text("密码范围只能在6-16位之间");
			return;
		}
		if(passwords == null || passwords.trim() == "") {
			error.text("确认密码不能为空");
			return;
		}
		if(phone == null || phone.trim() == "") {
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
		if(password != passwords) {
			error.text("两次密码输入不一致");
			return;
		}
		$.post(contextPath + "/des/reg",  
			$("#form").serialize(),
			function (data) {
				if(data.error == "注册成功") {
					window.location = contextPath + "/des/loginpage";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
}
/**
 * 设计师登录前后台验证
 * @returns
 */
function des_login() {
	var email = $("#email").val();
	var password = $("#password").val();
	var error = $("#error");
	var check = checkEmail(email);
	if(email == null || email.trim() == "") {
		error.text("邮箱不能为空");
		return;
	}
	if(check == false) {
		error.text("邮箱格式错误");
		return;
	}
	if(password == null || password.trim() == "") {
		error.text("密码不能为空");
		return;
	}
	$.post(contextPath + "/des/login", 
		$("#form").serialize(),
		function (data) {
			if(data.error == "登录成功") {
				window.location = contextPath + "/des/home";
				return;
			} else if(data.error == "等待审核") {
				window.location = contextPath + "/des/wait";
				return;
			}
			$("#error").text(data.error);
		}, "JSON");
}

/**
 * 管理员创建前后台验证
 * @returns
 */
function adm_reg() {
		var email = $("#email").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var passwords = $("#passwords").val();
		var phone = $("#phone").val();
		var error = $("#error");
		var check = checkEmail(email);
		if(email == null || email.trim() == "") {
			error.text("邮箱不能为空");
			return;
		}
		if(check == false) {
			error.text("邮箱格式错误");
			return;
		}
		if(name == null || name.trim() == "")　{
			error.text("名字不能为空");
			return;
		}
		if(password == null || password.trim() == "") {
			error.text("密码不能为空");
			return;
		}
		if(password.toString().length < 6 || password.toString().length > 16){
			error.text("密码范围只能在6-16位之间");
			return;
		}
		if(passwords == null || passwords.trim() == "") {
			error.text("确认密码不能为空");
			return;
		}
		if(phone == null || phone.trim() == "") {
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
		if(password != passwords) {
			error.text("两次密码输入不一致");
			return;
		}
		$.post(contextPath + "/adm/reg", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "注册成功") {
					window.location = contextPath + "/adm/home";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
}
/**
 * 管理员登录前后台验证
 * @returns
 */
function adm_login() {
	var email = $("#email").val();
	var password = $("#password").val();
	var error = $("#error");
	var check = checkEmail(email);
	if(email == null || email.trim() == "") {
		error.text("邮箱不能为空");
		return;
	}
	if(check == false) {
		error.text("邮箱格式错误");
		return;
	}
	if(password == null || password.trim() == "") {
		error.text("密码不能为空");
		return;
	}
	$.post(contextPath + "/adm/login", 
		$("#form").serialize(),
		function (data) {
			if(data.error == "登录成功") {
				window.location = contextPath + "/adm/home";
				return;
			}
			$("#error").text(data.error);
		}, "JSON");
}

/**
 * 业主注册前后台验证
 * @returns
 */
function cus_reg() {
		var email = $("#email").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var passwords = $("#passwords").val();
		var phone = $("#phone").val();
		var error = $("#error");
		var check = checkEmail(email);
		if(email == null || email.trim() == "") {
			error.text("邮箱不能为空");
			return;
		}
		if(check == false) {
			error.text("邮箱格式错误");
			return;
		}
		if(name == null || name.trim() == "")　{
			error.text("昵称不能为空");
			return;
		}
		if(password == null || password.trim() == "") {
			error.text("密码不能为空");
			return;
		}
		if(password.toString().length < 6 || password.toString().length > 16){
			error.text("密码范围只能在6-16之间");
			return;
		}
		if(passwords == null || passwords.trim() == "") {
			error.text("确认密码不能为空");
			return;
		}
		if(phone == null || phone.trim() == "") {
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
		if(password != passwords) {
			error.text("两次密码输入不一致");
			return;
		}
		$.post(contextPath + "/cus/reg", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "注册成功") {
					window.location = contextPath + "/cus/loginpage";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
}
/**
 * 业主登录前后台验证
 * @returns
 */
function cus_login() {
	var email = $("#email").val();
	var password = $("#password").val();
	var error = $("#error");
	var check = checkEmail(email);
	if(email == null || email.trim() == "") {
		error.text("邮箱不能为空");
		return;
	}
	if(check == false) {
		error.text("邮箱格式错误");
		return;
	}
	if(password == null || password.trim() == "") {
		error.text("密码不能为空");
		return;
	}
	$.post(contextPath + "/cus/login", 
		$("#form").serialize(),
		function (data) {
			if(data.error == "登录成功") {
				window.location = contextPath + "/cus/home";
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
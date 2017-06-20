var contextPath = "";

/**
 * 设计师修改个人信息
 * @returns
 */
function des_update() {
		var email = $("#email").val();
		var name = $("#name").val();
		var address = $("#address").val();
		var exp = $("#work_exp").val();
		var phone = $("#phone").val();
		var style = $("#style").val();
		var error = $("#error");
		if(email == null || email.trim() == "") {
			error.text("邮箱不能为空");
			return;
		}
		var check = checkEmail(email);
		if(check == false) {
			error.text("邮箱格式错误");
			return;
		}
		if(name == null || name.trim() == "")　{
			error.text("昵称不能为空");
			return;
		}
		if(style == null || style.trim() == "") {
			error.text("风格不能为空");
			return;
		}
		if(address == null || address.trim() == "") {
			error.text("地址不能为空");
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
		$.post(contextPath + "/des/update", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "修改成功") {
					window.location = contextPath + "/des/home";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
}
/**
 * 设计师修改密码
 * @returns
 */
function des_pwd() {
	var email = $("#email").val();
	var password = $("#password").val();
	var passwords = $("#passwords").val();
	var passwordes = $("#passwordes").val();
	var error = $("#error");
	if(password == null || password.trim() == "")　{
		error.text("原密码不能为空");
		return;
	}
	if(passwords == null || passwords.trim() == "") {
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
	$.post(contextPath + "/des/update_pwd", 
		$("#form").serialize(),
		function (data) {
			if(data.error == "修改成功") {
				window.location = contextPath + "/des/home";
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

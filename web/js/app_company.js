var contextPath = "";

/**
 * 预约装修公司
 * @returns
 */
function appointment(){
	var name = $("#name").val();
	var phone = $("#phone").val();
	var plot_name = $("#plot_name").val();
	var area = $("#area").val();
	var error = $("#error");
	if(name == null || name.trim() == "")　{
		error.text("您的名称不能为空");
		return;
	}
	if(phone == null || phone.trim() == "") {
		error.text("您的电话不能为空");
		return;
	}
	if(isNaN(phone)) {
		error.text("电话只能输入数字");
		return;
	}
	if(phone.toString().length != 11) {
		error.text("电话号码只能为11位数字");
		return;
	}
	if(plot_name == null || plot_name.trim() == "") {
		error.text("小区名称不能为空");
		return;
	}
	if(area == null || area.trim() == "") {
		error.text("建筑面积不能为空");
		return;
	}
	if(isNaN(area)) {
		error.text("建筑面积格式只能输入数字");
		return;
	}	
	$.post(contextPath + "/BHcompany/app", 
			$("#form").serialize(),
			function (data) {
				if(data.error == "预约成功") {
					window.wxc.xcConfirm("预约成功！", window.wxc.xcConfirm.typeEnum.info);
					window.location = contextPath + "/index/home";
					return;
				}
				$("#error").text(data.error);
			}, "JSON");
	
}



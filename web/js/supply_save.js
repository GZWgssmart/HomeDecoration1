var contextPath = "";

/**
 * 收藏建材商的方法
 * @returns
 */
function save_sup() {
	var id = $("#id").val();
	var cus_id = $("#cus_id").val();
	var error = $("#error");
	if(cus_id == null || cus_id.trim() == "") {
		window.wxc.xcConfirm("您没有收藏权限！请登录用户账号！", window.wxc.xcConfirm.typeEnum.info);
		return;
	}
	$.ajax({  
		type:"post", 
		url:contextPath + "/BHsup/save_sup", 
		data:{"id" : id, "cus_id":cus_id}, 
		dataType:"json", 
		cache:false, 
		success:function(data){ 
			if(data.save == "成功") {
				$("#save").attr("class", "am-btn am-btn-default am-round am-fr");
				$("#save").attr("disabled", "disabled");
				$("#save").text("已收藏");
			} else {
				error.text(data.error);
			}
		}
	}); 
}
/**
 * 收藏建材的方法
 * @returns
 */
function save_pro() {
	var id = $("#id").val();
	var cus_id = $("#cus_id").val();
	var error = $("#error");
	if(cus_id == null || cus_id.trim() == "") {
		window.wxc.xcConfirm("您没有收藏权限！请登录用户账号！", window.wxc.xcConfirm.typeEnum.info);
		return;
	}
	$.ajax({  
		type:"post", 
		url:contextPath + "/BHsup/save_pro", 
		data:{"id" : id, "cus_id":cus_id}, 
		dataType:"json", 
		cache:false, 
		success:function(data){ 
			if(data.save == "成功") {
				$("#save").attr("class", "am-btn am-btn-default am-round am-fr");
				$("#save").attr("disabled", "disabled");
				$("#save").text("已收藏");
			} else {
				error.text(data.error);
			}
		}
	}); 
}
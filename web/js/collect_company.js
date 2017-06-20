var contextPath = "";

/**
 * 收藏公司
 * @returns
 */
function collect_company(){
	var id = $("#company_id").val();
	var cus_id = $("#customer_id").val();
	var error = $("#error");
	if(cus_id == null || cus_id.trim() == "") {
		window.wxc.xcConfirm("您没有收藏权限！请登录用户账号！", window.wxc.xcConfirm.typeEnum.info);
		return;
	}
	$.ajax({  
		type:"post", 
		url:contextPath + "/BHcompany/collect_comp", 
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
 * 收藏公司案例
 * @returns
 */
function collect_company_case(){
	var id = $("#companyCase_id").val();
	var cus_id = $("#customer_id").val();
	var error = $("#error");
	if(cus_id == null || cus_id.trim() == "") {
		window.wxc.xcConfirm("您没有收藏权限！请登录用户账号！", window.wxc.xcConfirm.typeEnum.info);
		return;
	}
	$.ajax({  
		type:"post", 
		url:contextPath + "/BHcompany/collect_case", 
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

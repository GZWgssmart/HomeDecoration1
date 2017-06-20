var contextPath = "";

/**
 * 选中商品进行结算
 * @returns
 */
function proChecked() {
	var cost = 0.0;
	var count = 1;
	var idStr = "";
	$("input:checkbox[name='checked']:checked").each(function() {
		cost += parseFloat($(this).val());
		idStr += $(this).attr("id")+",";
		count++;
    });
	if((count - 1) == 0) { 
		window.wxc.xcConfirm("请选中商品进行结算", window.wxc.xcConfirm.typeEnum.info);
		return;
	}
	$.ajax({  
		type:"post", 
		url:contextPath + "/cart/addOrder", 
		data:{"cost" : cost,"ids":idStr}, 
		dataType:"json", 
		cache:false, 
		success:function(data){ 
			if(data.error == "成功") {
				window.wxc.xcConfirm("结算成功！", window.wxc.xcConfirm.typeEnum.info);
				$("#flashes").trigger("click");
			} else {
				error.text(data.error);
			}
		}
	}); 
}

/**
 * 
 * @returns
 * 加入购物车
 */
function addCart() {
	var id = $("#id").val();
	var cus_id = $("#cus_id").val();
	var error = $("#error");
	if(cus_id == null || cus_id.trim() == "") {
		window.wxc.xcConfirm("请先登录用户账号", window.wxc.xcConfirm.typeEnum.info);;
		return;
	}
	$.ajax({  
		type:"post", 
		url:contextPath + "/cart/addCart", 
		data:{"id" : id, "cus_id":cus_id}, 
		dataType:"json", 
		cache:false, 
		success:function(data){ 
			if(data.error == "成功") {
				// TODO　弹出提示
				window.wxc.xcConfirm("添加成功", window.wxc.xcConfirm.typeEnum.info);
			} else {
				error.text(data.error);
			}
		}
	}); 
}
/**
 * 增加数量
 * @param count
 * @returns
 */
function addTotal(count) {
	var cartId = "#cart_id"+count;
	var id = $(cartId).val();
	var errors = $("#error");
	var totalStr = "#total"+count;
	var span = $("#span"+count);
	var total = $(totalStr);
	var cost = $("#cost"+count);
	$.ajax({  
		type:"post", 
		url:contextPath + "/cart/add", 
		data:{"id" : id}, 
		dataType:"json", 
		cache:false, 
		success:function(data){ 
			if(data.error == "成功") {
				span.text(data.total);
				cost.text(data.cost);
			} else {
				alert(data.error);
				error.text(data.error);
			}
		},
		error: function(data) {
		}
	}); 
}
/**
 * 减少数量
 * @param count
 * @returns
 */
function lowTotal(count) {
	var cartId = "#cart_id"+count;
	var id = $(cartId).val();
	var error = $("#error");
	var total = $("#total"+count);
	var span = $("#span"+count);
	var cost = $("#cost"+count);
	$.ajax({  
		type:"post", 
		url:contextPath + "/cart/lower", 
		data:{"id" : id}, 
		dataType:"json", 
		cache:false, 
		success:function(data){ 
			if(data.error == "成功") {
				span.text(data.total);
				cost.text(data.cost);
			} else if(data.error == "删除成功") {
				$("#flashes").trigger("click");
			} else {
				error.text(data.error);
			}
		},
		error: function(data) {
		}
	}); 
}
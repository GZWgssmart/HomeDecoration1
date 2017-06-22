var contextPath = "";

/**
 * 添加商品前台验证
 * @returns
 */
function add_pro() {
		var name = $("#name").val();
		var price = $("#price").val();
		var prices = $("#sale_price").val();
		var des = $("#des").val();
		var error = $("#error");
		if(name == null || name.trim() == "")　{
			error.text("名称不能为空");
			return;
		}
		if(price == null || price.trim() == "") {
			error.text("价格不能为空");
			return;
		}
		if(isNaN(price)){
			error.text("价格只能输入数字");
			return;
		}
		if(prices == null || prices.trim() == "") {
			error.text("折后价不能为空");
			return;
		}
		if(isNaN(prices)){
			error.text("价格只能输入数字");
			return;
		}
		$("#form").on('click', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: contextPath+'/pro/add', // 需要提交的 url
	            dataType : 'json',
	            data:$("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	            	if(data.error == "添加成功") {
	            		window.location = contextPath + "/pro/pros";
	            	} else {
	            		$("#error").text("添加失败");
	            	}
	            },
	            error: function(data) {
	            	$("#error").text("error");
	            }
	        });
	        return false; // 阻止表单自动提交事件
		});
}

/**
 * 修改商品前台验证
 * @returns
 */
function update_pro() {
		var name = $("#name").val();
		var price = $("#price").val();
		var prices = $("#sale_price").val();
		var des = $("#des").val();
		var id = $("#id").val();
		var error = $("#error");
		if(name == null || name.trim() == "")　{
			error.text("名称不能为空");
			return;
		}
		if(price == null || price.trim() == "") {
			error.text("价格不能为空");
			return;
		}
		if(isNaN(price)){
			error.text("价格只能输入数字");
			return;
		}
		if(prices == null || prices.trim() == "") {
			error.text("折后价不能为空");
			return;
		}
		if(isNaN(prices)){
			error.text("价格只能输入数字");
			return;
		}
		$("#form").on('click', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: contextPath+'/pro/update', // 需要提交的 url
	            dataType : 'json',
	            data:$("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "修改成功") {
	            		window.location = contextPath + "/pro/pros";
	            	} else {
	            		$("#error").text("添加失败");
	            	}
	            },
	            error: function(data) {
	            	$("#error").text("error");
	            }
	            // $(this).resetForm(); // 提交后重置表单
	        });
	        return false; // 阻止表单自动提交事件
		});
}
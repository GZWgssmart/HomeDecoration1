var contextPath = "";

/**
 * 建材商添加活动前台验证
 * @returns
 */
function add_act() {
		var name = $("#name").val();
		var des = $("#des").val();
		var error = $("#error");
		if(name == null || name.trim() == "")　{
			error.text("名称不能为空");
			return;
		}
		$("#form").on('click', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: contextPath+'/supAct/add', // 需要提交的 url
	            dataType : 'json',
	            data:$("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "添加成功") {
	            		window.location = contextPath + "/supAct/acts";
	            	} else {
	            		$("#error").text("添加失败");
	            	}
	            },
	            error: function(data) {
	            	// window.location = contextPath + "/error";
	            	$("#error").text("error");
	            }
	            // $(this).resetForm(); // 提交后重置表单
	        });
	        return false; // 阻止表单自动提交事件
		});
}

/**
 * 修改建材商活动前台验证
 * @returns
 */
function update_act() {
		var name = $("#name").val();
		var des = $("#des").val();
		var id = $("#id").val();
		var error = $("#error");
		if(name == null || name.trim() == "")　{
			error.text("名称不能为空");
			return;
		}
		$("#form").on('click', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: contextPath+'/supAct/update', // 需要提交的 url
	            dataType : 'json',
	            data:$("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "修改成功") {
	            		window.location = contextPath + "/supAct/acts";
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
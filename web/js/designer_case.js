var contextPath = "";

/**
 *设计师案例表t_designer_case的添加案例							
 * @returns
 */
function designer_case_add(){
	var case_name = $("#name").val();
	var polt_name = $("#polt_name").val();
	var style = $("#style").val();
	var des = $("#des").val();
	var error = $("#error");
	if(case_name == null || case_name.trim() == ""){
		error.text("案例名称不能为空");
		return;
	}
	if(polt_name == null || polt_name.trim()== ""){
		error.text("小区不能为空");
		return;
	}
	if(style == null || style.trim()== ""){
		error.text("装修风格不能为空");
		return;
	}
	$("#form").on('click', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: '/BeautHome/des/add_des', // 需要提交的 url
	            dataType : 'json',
	            data: $("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "添加成功") {
	            		window.location = contextPath + "/des/des_casespage";
	            	} else {
	            		$("#error").text(data.error);
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
/**
 *设计师案例表t_designer_case的编辑案例							
 * @returns
 */
function designer_case_edit(){
	var case_name = $("#name").val();
	var polt_name = $("#polt_name").val();
	var style = $("#style").val();
	var des = $("#des").val();
	var error = $("#error");
	if(case_name == null || case_name.trim() == ""){
		error.text("案例名称不能为空");
		return;
	}
	if(polt_name == null || polt_name.trim()== ""){
		error.text("小区不能为空");
		return;
	}
	if(style == null || style.trim()== ""){
		error.text("装修风格不能为空");
		return;
	}
	$("#form").on('click', function() {
        $("#form").ajaxSubmit({
            type: 'post', // 提交方式 get/post
            url: '/BeautHome/des/edit', // 需要提交的 url
            dataType : 'json',
            data: $("#form").serialize(),
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                // 此处可对 data 作相关处理
            	if(data.error == "编辑成功") {
            		window.location = contextPath + "/des/des_casespage";
            	} else {
            		$("#error").text(data.error);
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

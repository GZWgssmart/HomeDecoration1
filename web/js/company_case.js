var contextPath = "";

/**
 *装修公司案例表t_company_case的添加案例							
 * @returns
 */
function company_case_add(){
	var case_name = $("#name").val();
	var polt_name = $("#polt_name").val();
	var style = $("#style").val();
	var des = $("#des").val();
	var error = $("#error");
	$("#form").on('click', function() {
	    $("#form").on('submit', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: '/BeautHome/company_case/add_comp', // 需要提交的 url
	            dataType : 'json',
	            data: $("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "添加成功") {
	            		window.location = contextPath + "/company_case/comp_casepage";
	            	} else {
	            		$("#error").text(data.error);
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
	});
}
/**
 *装修公司案例表t_company_case的编辑案例							
 * @returns
 */
function company_case_edit(){
	var case_name = $("#name").val();
	var polt_name = $("#polt_name").val();
	var style = $("#style").val();
	var des = $("#des").val();
	var error = $("#error");
	$("#form").on('click', function() {
	    $("#form").on('submit', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: '/BeautHome/company_case/edit', // 需要提交的 url
	            dataType : 'json',
	            data: $("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "编辑成功") {
	            		window.location = contextPath + "/company_case/comp_casepage";
	            	} else {
	            		$("#error").text(data.error);
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
	});
}

/**
 *装修公司案例表t_company_case的添加活动案例							
 * @returns
 */
function company_case_add_active(){
	var case_name = $("#name").val();
	var des = $("#des").val();
	var error = $("#error");
	$("#form").on('click', function() {
	    $("#form").on('submit', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: '/BeautHome/company_case/add_active', // 需要提交的 url
	            dataType : 'json',
	            data: $("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "添加成功") {
	            		window.location = contextPath + "/company_case/activepage";
	            	} else {
	            		$("#error").text(data.error);
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
	});
}

/**
 *装修公司案例表t_company_case的编辑活动							
 * @returns
 */
function company_case_edit_active(){
	var case_name = $("#name").val();
	var des = $("#des").val();
	var error = $("#error");
	$("#form").on('click', function() {
	    $("#form").on('submit', function() {
	        $("#form").ajaxSubmit({
	            type: 'post', // 提交方式 get/post
	            url: '/BeautHome/company_case/editactive', // 需要提交的 url
	            dataType : 'json',
	            data: $("#form").serialize(),
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	            	if(data.error == "编辑成功") {
	            		window.location = contextPath + "/company_case/activepage";
	            	} else {
	            		$("#error").text(data.error);
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
	});
}

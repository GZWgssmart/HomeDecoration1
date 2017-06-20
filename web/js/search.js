var contextPath = "";

//初始化XMLHttpRequest对象
function getXMLHttpRequest(){
	var xmlHttpRequest;
	if(window.XMLHttpRequest){
		xmlHttpRequest = new XMLHttpRequest();
	}else {
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlHttpRequest;
}

//搜索框传递数据到后台
function getCreat(){
	var myselect=document.getElementById("selectid");
	var index=myselect.selectedIndex;
	var value=myselect.options[index].value
	if(value == '搜索类型'){
		
	}
	if(value == '装修公司'){
		var content = document.getElementById("search");
		if(content.value==""){
			clearContent();
			return;
		}
		var xmlHttpRequest = getXMLHttpRequest();
		var url=contextPath + "/search/com?search="+content.value;
		xmlHttpRequest.open("GET",url,true);
		xmlHttpRequest.onreadystatechange=function() {
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
				var result = xmlHttpRequest.responseText;
				var json = eval("("+result+")");
				setContent(json);
			}
		};
		xmlHttpRequest.send();	
	}
	if(value == '设计师'){
		var content = document.getElementById("search");
		if(content.value==""){
			clearContent();
			return;
		}
		var xmlHttpRequest = getXMLHttpRequest();
		var url=contextPath + "/search/des?search="+content.value;
		xmlHttpRequest.open("GET",url,true);
		xmlHttpRequest.onreadystatechange=function() {
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
				var result = xmlHttpRequest.responseText;
				var json = eval("("+result+")");
				setContent(json);
			}
		};
		xmlHttpRequest.send();
	}
	if(value == '建材商'){
		var content = document.getElementById("search");
		if(content.value==""){
			clearContent();
			return;
		}
		var xmlHttpRequest = getXMLHttpRequest();
		var url=contextPath + "/search/sup?search="+content.value;
		xmlHttpRequest.open("GET",url,true);
		xmlHttpRequest.onreadystatechange=function() {
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
				var result = xmlHttpRequest.responseText;
				var json = eval("("+result+")");
				setContent(json);
			}
		};
		xmlHttpRequest.send();
	}
	if(value == '建材商品'){
		var content = document.getElementById("search");
		if(content.value==""){
			clearContent();
			return;
		}
		var xmlHttpRequest = getXMLHttpRequest();
		var url=contextPath + "/search/pro?search="+content.value;
		xmlHttpRequest.open("GET",url,true);
		xmlHttpRequest.onreadystatechange=function() {
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
				var result = xmlHttpRequest.responseText;
				var json = eval("("+result+")");
				setContent(json);
			}
		};
		xmlHttpRequest.send();
	}
}

//按钮传递数据到后台
function getCreatbtn(){
	var myselect=document.getElementById("selectid");
	var index=myselect.selectedIndex;
	var value=myselect.options[index].value;
	if(value == '搜索类型'){
		window.wxc.xcConfirm("请选择搜索类型", window.wxc.xcConfirm.typeEnum.info);
	}
	if(value == '装修公司'){
		var content = document.getElementById("search");
		if(content.value==""){
			window.wxc.xcConfirm("请输入内容", window.wxc.xcConfirm.typeEnum.info);
			clearContent();
			return;
		}
		window.location = contextPath + "/search/combtn?search="+content.value;
	}
	if(value == '建材商'){
		var content = document.getElementById("search");
		if(content.value==""){
			window.wxc.xcConfirm("请输入内容", window.wxc.xcConfirm.typeEnum.info);
			clearContent();
			return;
		}
		window.location = contextPath + "/search/supbtn?search="+content.value;
	}
	if(value == '设计师'){
		var content = document.getElementById("search");
		if(content.value==""){
			window.wxc.xcConfirm("请输入内容", window.wxc.xcConfirm.typeEnum.info);
			clearContent();
			return;
		}
		window.location = contextPath + "/search/desbtn?search="+content.value;
	}
	if(value == '建材商品'){
		var content = document.getElementById("search");
		if(content.value==""){
			window.wxc.xcConfirm("请输入内容", window.wxc.xcConfirm.typeEnum.info);
			clearContent();
			return;
		}
		window.location = contextPath + "/search/probtn?search="+content.value;
	}
}

//设置关联数据的展示
function setContent(content){
	clearContent();
	//首先获得关联数据的长度，从此确定生成多少tr
	var size=content.length;
	//var str = "";
	for(var i=0;i<size;i++){
		//代表的是json格式数据的第i个元素
		var nextNode = content[i];
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		var text=document.createTextNode(nextNode);
		td.setAttribute("border","0");
		td.setAttribute("bgcolor","#FFFAFA");
		td.onmouseover=function (){
			this.className="mouseOver";
		};
		td.onmouseout=function (){
			this.className="mouseOut"
		};
		var myselect=document.getElementById("selectid");
		var index=myselect.selectedIndex;
		var value=myselect.options[index].value
		if(value == '装修公司'){
			td.onclick=function (){
				var value1 = this.innerHTML;
				 window.location = contextPath + "/search/combtn?search="+value1;
			};
		}
		if(value == '建材商'){
			td.onclick=function (){
				var value1 = this.innerHTML;
				 window.location = contextPath + "/search/supbtn?search="+value1;
			};		
		}
		if(value == '设计师'){
			td.onclick=function (){
				var value1 = this.innerHTML;
				 window.location = contextPath + "/search/desbtn?search="+value1;
			};
		}
		if(value == '建材商品'){
			td.onclick=function (){
				var value1 = this.innerHTML;
				 window.location = contextPath + "/search/probtn?search="+value1;
			};
		}
		td.appendChild(text);
		tr.appendChild(td);
		document.getElementById("content_table_body").appendChild(tr);
	}
}

//清空之前的数据
function clearContent(){
	var contentTableBody = document.getElementById("content_table_body");
	var size = contentTableBody.childNodes.length;
	for(var i=size-1;i>=0;i--){
		contentTableBody.removeChild(contentTableBody.childNodes[i]);
	}
}
//当输入框焦失去时清空
function keywordBlur(){
	clearContent();
}

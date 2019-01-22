function getValue(data){
	return data==undefined?"":data;
}

function getNull(data){
	return data==""?null:data;
}

function successOperater(data,pageSize,func){
	if(data.code==1){
		insertTable(data);
		$("#ad_count").text(data.data.total);
		if(func!=null){
			func(data,pageSize);
		}
	}
}

function showPage(data,nums){
	var Count = data.data.total;
	var pages = Math.ceil(Count / nums); //得到总页数
	var $page = $("#page").page({
		pages: pages, //页数
		curr: 1, //当前页 
		theme: 'default', //主题
		groups: 5, //连续显示分页数
		prev: false, //若不显示，设置false即可
		next: false, //若不显示，设置false即可        
		first: "首页",
		last: "尾页", //false则不显示   

		jump: function (context) {
			$(".am-pagination>li").click(function () {
				// alert("dfadfasfd")
				var current = $(this).find("a").attr("data-page");
				$("#pageNo").val(current);
				showTable(parseInt(current),nums,null);
			});
		}
	});
}

function search(){
	$("#page").html("");
	showTable(1,pageSize,showPage);
	
}

function getChannel(){
	var topFrame = window.parent.frames["topFrame"].document;
	var channel = topFrame.getElementById("channle");
	return channel.value;
}

function getCurrentIframeUrlSearch(){
	return window.parent.frames["mainframe"].location.search;
}

function returnLogin(){
	window.parent.location.href="login.html";
}

function getUrlParams(url){
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {  
		var str = url.substr(1);  
		var strs = str.split("&");  
		for(var i = 0; i < strs.length; i ++) {  
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
		}  
    }
	return theRequest;
}
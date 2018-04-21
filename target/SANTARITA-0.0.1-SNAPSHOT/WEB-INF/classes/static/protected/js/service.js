$(function(){
	
	$("#email").val("");
	$("#password").val("");
	$("#name").val("");
	$("#emailreg").val("");
	$("#passwordreg").val("");
	$("#confermpassword").val("");
	
	$("#helpdesk").click(function(){
		$("#loginform").slideUp(1000);
		$("#helpdeskform").slideDown(1000);
		$("#helpdeskdiv").hide();
		$("#logindiv").show();
	});
	
	$("#logindiv").click(function(){
		$("#loginform").slideDown(1000);
		$("#helpdeskform").slideUp(1000);
		$("#helpdeskdiv").show();
		$("#logindiv").hide();
	});
});
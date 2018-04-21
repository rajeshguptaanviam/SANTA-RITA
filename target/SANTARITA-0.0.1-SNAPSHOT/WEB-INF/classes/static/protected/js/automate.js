$(function(){
	
	
	var encendidoDelSistema = $("#encendidodel").html();
	var modoDeOperacionManual = $("#mododeoperacion").html();
	
	//alert("encendidoDelSistema---------------------------->>"+encendidoDelSistema);
	//alert("modoDeOperacionManual---------------------------->>"+modoDeOperacionManual);
	
	if(encendidoDelSistema == 1)	slide.slideifactiveSistema();
	if(modoDeOperacionManual == 1)	slide.slideifactiveManual();
	
	$("#someSwitchOptionSuccess").click(function(){
		var someSwitchOptionSuccess = $("#someSwitchOptionSuccess").is(':checked');
		if(someSwitchOptionSuccess == true){
			$("#someSwitchOptionDanger_2").slideDown(200);
			$("#encendidoDelSistema").val("1");
			
			var parkingcount = $("#parkingcount").html();
			/*$.ajax({
				url : "/parking/activateContador?COUNT="+parkingcount+"&FIELD=encendidoDelSistema",
				async : false,
				type : "GET",
				success : function(data) {}
			});*/
			
		}
		if(someSwitchOptionSuccess == false){
			$("#encendidoDelSistema").val("0");
			$("#errormsg").html("");
			$("#someSwitchOptionDanger_2").slideUp(200);
			$("#parkingmanual").slideUp(200);
			$("#someSwitchOptionDanger").prop('checked', false);
			
			var parkingcount = $("#parkingcount").html();
			/*$.ajax({
				url : "/parking/de-activateContador?COUNT="+parkingcount+"&FIELD=encendidoDelSistema",
				async : false,
				type : "GET",
				success : function(data) {
					//alert(" data encendidoDelSistema : "+data);
					$.ajax({
						url : "/parking/de-activateContador.htm?COUNT="+parkingcount+"&FIELD=modoDeOperacionManual",
						async : false,
						type : "GET",
						success : function(data) {
							//alert(" data modoDeOperacionManual : "+data);
						}
					});
				}
			});*/
		}
	});
	
	$("#someSwitchOptionDanger").click(function(){
		var someSwitchOptionDanger = $("#someSwitchOptionDanger").is(':checked');
		if(someSwitchOptionDanger == true){
			$("#modoDeOperacionManual").val("1");
			$("#parkingmanual").slideDown(200);
			
			var parkingcount = $("#parkingcount").html();
			/*$.ajax({
				url : "/parking/activateContador?COUNT="+parkingcount+"&FIELD=modoDeOperacionManual",
				async : false,
				type : "GET",
				success : function(data) {}
			});*/
		}
		if(someSwitchOptionDanger == false){
			$("#modoDeOperacionManual").val("0");
			$("#errormsg").html("");
			$("#parkingmanual").slideUp(200);
			
			var parkingcount = $("#parkingcount").html();
			/*$.ajax({
				url : "/parking/de-activateContador?COUNT="+parkingcount+"&FIELD=modoDeOperacionManual",
				async : false,
				type : "GET",
				success : function(data) {}
			});*/
		}
	});
});


var warnning = {
	
		searchProjectValidation : function() {
			
			var nuManual = $("#space").val();
			alart("nuManual : "+nuManual);
			if(nuManual == ""){
				$("#errormsg").html("<font color='red'>please provide numeric space value..!</font>");
				return false;
			}
			if(isNaN(nuManual)){
				$("#errormsg").html("<font color='red'>please provide numeric space value..!</font>");
				return false;
			}else{
				$("#errormsg").html("");
				return true;
			}
		}
};


var slide = {
	
		slideifactiveSistema : function(){
			$("#someSwitchOptionSuccess").prop('checked', true);
			$("#someSwitchOptionDanger_2").slideDown(200);
			
		},slideifactiveManual : function(){
			$("#someSwitchOptionDanger").prop('checked', true);
			$("#parkingmanual").slideDown(200);
		}
};
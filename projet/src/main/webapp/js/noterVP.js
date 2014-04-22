$(document).ready(function() {

	$(".value").click(function(){
		var note1 = parseInt($("#qog").val());
		if(note1 > 2){
			alert("le nombre est trop grand");
			var tt = 0;
			document.getElementById("qog").value = tt;
		};
		var note2 = parseInt($("#ga").val());
		var note3 = parseInt($("#ti").val());
		var result = note1 + note2 + note3;
		document.getElementById("resultat").value = result;
	});
	

	
});
$(document).ready(function() {
	
	$("#valider").click(function(){
		var ok = true;
		for(var i=1; i<=10; i++){
			if(parseFloat($("#note"+i).val())>2 || parseFloat($("#note"+i).val())<0){
				ok = false;
			}
		}
		if(ok == true){
			
			var note1 = parseFloat($("#note1").val());
			var note2 = parseFloat($("#note2").val());
			var note4 = parseFloat($("#note3").val());
			var note5 = parseFloat($("#note4").val());
			var note6 = parseFloat($("#note5").val());
			var note7 = parseFloat($("#note6").val());
			var note8 = parseFloat($("#note7").val());
			var note9 = parseFloat($("#note8").val());
			var note10 = parseFloat($("#note10").val());
			
		}
		else{
			document.getElementById("sresultat").innerHTML = "  Il y a un problème";
		}
	});

	
	$(".value").keyup(function(){
		if(this.value >2 || this.value < 2){
			var id = this.id;
			document.getElementById("s"+id).innerHTML = "  La valeur doit être comprise entre 0 et 2";
		}
		else{
			var id = this.id;
			document.getElementById("s"+id).innerHTML = "";
		};
		var result = (parseFloat($("#note1").val()) + parseFloat($("#note2").val()) + parseFloat($("#note3").val()) + parseFloat($("#note4").val()) + parseFloat($("#note5").val()) + parseFloat($("#note6").val()) + parseFloat($("#note7").val()) + parseFloat($("#note8").val()) + parseFloat($("#note9").val()) + parseFloat($("#note10").val()));
		document.getElementById("resultat").value = result;
	});
	
});
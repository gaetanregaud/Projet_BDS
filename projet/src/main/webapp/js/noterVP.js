$(document).ready(function() {
	
	$("#valider").click(function(){
		var ok = true;
		for(var i=1; i<=10; i++){
			if(parseFloat($("#note"+i).val())>2 || parseFloat($("#note"+i).val())<0){
				ok = false;
			}
		}
		if(ok == true){
			var result = (parseFloat($("#note1").val()) + parseFloat($("#note2").val()) + parseFloat($("#note3").val()) + parseFloat($("#note4").val()) + parseFloat($("#note5").val()) + parseFloat($("#note6").val()) + parseFloat($("#note7").val()) + parseFloat($("#note8").val()) + parseFloat($("#note9").val()) + parseFloat($("#note10").val()));
			$.ajax({
				url:"notervp",
				type:"POST",
				data:{id_etudiant:$("#id_etudiant").val(),
					id_vp:$("#id_vp").val(),
					id_equipeSport:$("#id_equipeSport").val(),
					note1:parseFloat($("#note1").val()),
					note2:parseFloat($("#note2").val()),
					note3:parseFloat($("#note3").val()),
					note4:parseFloat($("#note4").val()),
					note5:parseFloat($("#note5").val()),
					note6:parseFloat($("#note6").val()),
					note7:parseFloat($("#note7").val()),
					note8:parseFloat($("#note8").val()),
					note9:parseFloat($("#note9").val()),
					note10:parseFloat($("#note10").val()),
					noteResultat:parseFloat(result)
				}
			}).done(function(){
				window.close();
			});
		}
		else{
			document.getElementById("sresultat").innerHTML = "  Il y a un problème";
		}
	});

	
	$(".value").keyup(function(){
		if(parseFloat(this.value) >2 || parseFloat(this.value) < 0){
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
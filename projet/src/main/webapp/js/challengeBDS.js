$(document).ready(function() {
	
	$(".infoChallenge").hide();
	$(".modifInfoChallenge").hide();
	$("#challenges").hide();
	$("#newChallenge").show();
	$("#suprChallenge").hide();
	$("#modifChallenge").hide();
	$(".connu").hide();
	$("#newadresse").hide();
	
	$(".plus").click(function(){
		$(".adresses").hide();
		$("#newadresse").show();
		$(".ajouterChall").hide();
	});
	
	$(".retour").click(function(){
		$(".adresses").show();
		$("#newadresse").hide();
		$(".ajouterChall").show();
	});
	
	$(".ajouterAdr").click(function(){
		$.ajax({
			url:"ajouteradresse",
			type:"POST",
			data:{nom:$("#nom").val(),
				num:$("#num").val(),
				rue:$("#rue").val(),
				cp:$("#cp").val(),
				ville:$("#ville").val(),
				pays:$("#pays").val()
			}
		});
		
	});
	
	$(".info").click(function(){
		var id_chall = this.id;
		$(".infoChallenge").hide();
		$(".modifInfoChallenge").hide();
		$("#info"+id_chall).show();
	});
	
	$(".modifier").click(function(){
		var id_chall = this.id;
		$(".infoChallenge").hide();
		$(".modifInfoChallenge").hide();
		$("#modifInfo"+id_chall).show();
	});
	
	$("#type").change(function(){
		var id_type = this.value;
		$(".connu").hide();
		$("#new").hide();
		$("#"+id_type).show();
	});
	
	$("#liste_chall").click(function(){
		$("#newChallenge").hide();
		$("#suprChallenge").hide();
		$("#modifChallenge").hide();
		$("#challenges").show();
	});
	
	$("#ajou_chall").click(function(){
		$("#challenges").hide();
		$("#suprChallenge").hide();
		$("#newChallenge").show();
		$("#modifChallenge").hide();
		$("#new").show();
		$(".connu").hide();
		$("#newadresse").hide();
	});
	
	$("#supr_chall").click(function(){
		$("#challenges").hide();
		$("#newChallenge").hide();
		$("#modifChallenge").hide();
		$("#suprChallenge").show();
	});
	
	$("#modif_chall").click(function(){
		$("#challenges").hide();
		$("#newChallenge").hide();
		$("#suprChallenge").hide();
		$("#modifChallenge").show();
		$("#modif_InfoChallenge").hide();
	});
	
	function convertirDate(date){
		var tablmois =["janv", "fevr", "mars", "avr", "mai", "juin", "juil", "aout", "sept", "oct", "nov", "dec"];
		var tablMois =["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11","12"];
		var mois = "";
		var jour = "";
		var annee = "";
		var ladate ="";
		var m = 0;
		var j = 0;
		for(var i=0; i<date.length; i++){
			if(m == 0 && (date.charAt(i) == "." || date.charAt(i) == " ")){
				m = i;
			}
			else if(m != 0 && (date.charAt(i) == ",")){
				j = i;
			}
		}
		var nomM = date.substring(0,m);
		for(var i=0; i<date.substring(0,m).length; i++){
			if(date.substring(0,m).charCodeAt(i) == 233){
				nomM = date.substring(0,i)+"e"+date.substring(i+1,m);
			}
			else if(date.substring(0,m).charCodeAt(i) == 251){
				nomM = date.substring(0,i)+"u"+date.substring(i+1,m);
			}
		}
		
		for(var i=0; i<=tablmois.length; i++){
			if(nomM == (tablmois[i])){
				mois = tablMois[i];
			}
		}
		jour = date.substring(j-2,j);
		if(jour.charAt(0)==" "){
			jour = "0"+jour.charAt(1);
		}
		annee = date.substring(date.length-4, date.length);
		ladate = annee+"-"+mois+"-"+jour;
		return ladate;
	};
	
	function convertirHeure(heure){
		var h = heure.substring(0,2);
		var m = heure.substring(3,5);
		var time = heure.substring(heure.length-2, heure.length);
		if(time == "PM" && h !="00"){
			h = parseInt(h) + 12;
		}
		var heure = h+":"+m;
		return heure;
	}
	
	$("#modifchallenge").change(function(){
		$("#modif_InfoChallenge").show();
		$.ajax({
			url:"challengebds",
			type:"post",
			data:{
				type:"modifChallenge",
				id_challenge:$("#modifchallenge").val()
			}
		}).done(function(data){
			var date = convertirDate(data.date_challenge);
			var heure = convertirHeure(data.heure_challenge);
			document.getElementById("modifid_challenge").value = data.id_challenge;
			document.getElementById("modifnom_challenge").value = data.nom_challenge;
			document.getElementById("modifdate_challenge").value = date;
			document.getElementById("modifheure_challenge").value = heure;
			document.getElementById("modifdescription").value = data.description_challenge;
		});
	});
	
	
});
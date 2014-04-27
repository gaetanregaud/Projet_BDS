$(document).ready(function() {
	
	$(".infoChallenge").hide();
	$(".modifInfoChallenge").hide();
	$("#challenges").show();
	$("#newChallenge").hide();
	$("#suprChallenge").hide();
	$("#modifChallenge").hide();
	
	$(".plus").click(function(){
		$(".adresses").hide();
		$(".newadresse").show();
		$(".ajouterChall").hide();
		$(".btn_modifChallenge").hide();
	});
	
	$(".retour").click(function(){
		$(".adresses").show();
		$(".newadresse").hide();
		$(".ajouterChall").show();
		$(".btn_modifChallenge").show();
	});
	
	$("#btnAjou_ajouterAdr").click(function(){
		$.ajax({
			url:"adresse",
			type:"POST",
			data:{nom:$("#ajounom").val(),
				num:$("#ajounum").val(),
				rue:$("#ajourue").val(),
				cp:$("#ajoucp").val(),
				ville:$("#ajouville").val(),
				pays:$("#ajoupays").val()
			}
		}).done(function(data){
			var select = document.getElementById("ajoulieu");
			var value = data.id;
			var nom = data.nom;
			select.options[select.options.length] = new Option(nom, value);
			select.options[select.options.length-1].selected = true;
			$(".adresses").show();
			$(".newadresse").hide();
			$(".ajouterChall").show();	
		});
	});
	
	$("#btnModif_ajouterAdr").click(function(){
		$.ajax({
			url:"adresse",
			type:"POST",
			data:{nom:$("#modifnom").val(),
				num:$("#modifnum").val(),
				rue:$("#modifrue").val(),
				cp:$("#modifcp").val(),
				ville:$("#modifville").val(),
				pays:$("#modifpays").val()
			}
		}).done(function(data){
			var select = document.getElementById("modiflieu");
			var value = data.id;
			var nom = data.nom;
			select.options[select.options.length] = new Option(nom, value);
			select.options[select.options.length-1].selected = true;
			$(".adresses").show();
			$(".newadresse").hide();
			$(".btn_modifChallenge").show();	
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
		$(".newadresse").hide();
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
		$(".newadresse").hide();
		$(".adresses").hide();
		$(".btn_modifChallenge").hide();
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
		$(".adresses").show();
		$(".btn_modifChallenge").show();
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
			var id_adr = data.adresse.id;
			var adresses = document.getElementById("modiflieu");
			for(var i=0; i<adresses.length; i++){
				adresses.options[i].selected = false;
				if(adresses.options[i].value == id_adr){
					adresses.options[i].selected = true;
				}
			}
		});
	});
	
	$("#ajouchallenge").change(function(){
		if($("#ajouchallenge").val()=="new"){
			document.getElementById("ajouid_challenge").value = "";
			document.getElementById("ajounom_challenge").value = "";
			document.getElementById("ajoudescription").value = "";
		}
		else{
			$.ajax({
				url:"challengebds",
				type:"post",
				data:{
					type:"ajouChallenge",
					nom_challenge:$("#ajouchallenge").val()
				}
			}).done(function(data){
				document.getElementById("ajouid_challenge").value = data.id_challenge;
				document.getElementById("ajounom_challenge").value = data.nom_challenge;
				document.getElementById("ajoudescription").value = data.description_challenge;
				var id_adr = data.adresse.id;
				var adresses = document.getElementById("ajoulieu");
				for(var i=0; i<adresses.length; i++){
					adresses.options[i].selected = false;
					if(adresses.options[i].value == id_adr){
						adresses.options[i].selected = true;
					}
				}
			});
		}
	});
	
	
});
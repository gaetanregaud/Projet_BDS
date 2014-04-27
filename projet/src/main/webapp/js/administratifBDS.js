$(document).ready(function() {
	
	
	$("#admini_etudiantNotOK").show();
	$("#recherche").hide();
	
	$("#affi_listeEtudiantNotOK").click(function(){
		$("#admini_etudiantNotOK").show();
		$("#recherche").hide();
	});
	
	$("#rechercher_etudiant").click(function(){
		$("#admini_etudiantNotOK").hide();
		$("#recherche").show();
		$("#resultat_recherche").hide();
	});

	
	$(".info").click(function(){
		$(".infoEtudiant").hide();
		var id = this.id;
		$("#info"+id).show();
	});
	
	$("#btn_rechercheEtudiant").click(function(){
			$("#resultat_recherche").show();
			$.ajax({
				url:"administratifbds",
				type:"post",
				data:{
					type:"recherche_etudiant",
					id_etudiant:$("#recherche_etudiant").val()
				}
			}).done(function(data){
				document.getElementById("rech_id").innerHTML = data.identifiant;
				document.getElementById("rech_nom").innerHTML = data.nom;
				document.getElementById("rech_prenom").innerHTML = data.prenom;
				document.getElementById("rech_tel").innerHTML = data.telephone;
				document.getElementById("rech_mail").innerHTML = data.mail;
				document.getElementById("rech_classe").innerHTML = data.classe;
				document.getElementById("rech_licence").value = data.licence;
				document.getElementById("post_id").value = data.identifiant;
				var cotisation = "non";
				if(data.cotisation == true){
					cotisation = "oui";
				}
				var certificat = "non";
				if(data.certificat == true){
					certificat = "oui";
				}
				var selectCertif = document.getElementById("rech_certif");
				var selectCotis = document.getElementById("rech_cotis");
				for(var i=0; i<selectCertif.length; i++){
					selectCertif.options[i].selected = false;
					if(selectCertif.options[i].value == certificat){
						selectCertif.options[i].selected = true;
					}
				}
				for(var i=0; i<selectCotis.length; i++){
					selectCotis.options[i].selected = false;
					if(selectCotis.options[i].value == cotisation){
						selectCotis.options[i].selected = true;
					}
				}
				var photo = data.photo;
				if(photo == ""){
					document.getElementById("rech_photo").src = "IMAGE/photo/etudiant/admin.jpg";
				}
				else{
					document.getElementById("rech_photo").src = "IMAGE/photo/etudiant/"+photo+".jpg";
				}
			});
	});
	
	$("#btn_modi_infoEtudiant").click(function(){
		$.ajax({
			url:"administratifbds",
			type:"post",
			data:{
				type:"modif_infoEtudiant",
				id_etudiant:$("#post_id").val(),
				cotisation:$("#rech_cotis").val(),
				certificat:$("#rech_certif").val(),
				licence:$("#rech_licence").val()
			}
		});
	});
	
	
	
});
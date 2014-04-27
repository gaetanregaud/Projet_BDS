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
				document.getElementById("rech_cotis").innerHTML = data.cotisation;
				document.getElementById("rech_certif").innerHTML = data.certificat;
				var photo = data.photo;
				if(photo == ""){
					document.getElementById("rech_photo").src = "IMAGE/photo/etudiant/admin.jpg";
				}
				else{
					document.getElementById("rech_photo").src = "IMAGE/photo/etudiant/"+photo+".jpg";
				}
			});
	});
	
	
	
});
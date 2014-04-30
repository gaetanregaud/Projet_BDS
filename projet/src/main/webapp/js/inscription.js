$(document).ready(function() {
	
	function verifIdentifiant(identifiant){
		if(identifiant.length == 6){
			$.ajax({
				url:"inscription",
				type:"POST",
				data:{
					type:"verifIdentifiant",
					identifiant:$("#identifiant").val(),
				}
			}).done(function(data){
				if(data == "oui"){
					document.getElementById("sidentifiant").innerHTML = "L'identifiant existe déjà";
				}
				else
				{
					document.getElementById("sidentifiant").innerHTML = "ok" ;
				}
			});
		}
		else{
			document.getElementById("sidentifiant").innerHTML = "Votre identifiant doit comporter 6 caractère!";
		};
	}
	
	function verifPassword(passwd1, passwd2){
		for(var i=0; i<passwd1.length; i++){
			var ok = true;
			if(passwd1.charAt(i) != passwd2.charAt(i)){
				ok = false;
			}
		}
		if(ok == true){
			document.getElementById("spsswd2").innerHTML = "ok";
		}
		else{
			document.getElementById("spsswd2").innerHTML = "non";		
		};
	}
	
	function verifMail(mail){
		var regex = /@hei.fr$/;
		var ok = true;
		if(!regex.test(mail)){
			ok = false;
		}
		if(ok == true){
			$.ajax({
				url:"inscription",
				type:"POST",
				data:{
					type:"verifEmail",
					email:$("#email").val(),
				}
			}).done(function(data){
				if(data == "oui"){
					document.getElementById("semail").innerHTML = "L'adresse email existe déjà!";
				}
				else
				{
					document.getElementById("semail").innerHTML = "ok" ;
				}
			});
		}
		else{
			document.getElementById("semail").innerHTML = "email incorrect";
		}
	}
	
	$("#identifiant").keyup(function(){
		var identifiant = this.value;
		verifIdentifiant(identifiant);
		
	});
	
	$("#psswd2").keyup(function(){
		var passwd1 = document.getElementById("psswd1").value;
		var passwd2 = this.value;
		verifPassword(passwd1, passwd2);
	});
	
	$("#email").keyup(function(){
		var email = this.value;
		verifMail(email);
	});
});
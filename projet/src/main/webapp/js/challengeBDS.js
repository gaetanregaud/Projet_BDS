$(document).ready(function() {
	
	$(".infoChallenge").hide();
	$(".modifInfoChallenge").hide();
	$("#challenges").show();
	$("#newChallenge").hide();
	$("#suprChallenge").hide();
	
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
		$("#challenges").show();
	});
	
	$("#ajou_chall").click(function(){
		$("#challenges").hide();
		$("#suprChallenge").hide();
		$("#newChallenge").show();
		$("#new").show();
		$(".connu").hide();
		$("#newadresse").hide();
	});
	
	$("#supr_chall").click(function(){
		$("#challenges").hide();
		$("#newChallenge").hide();
		$("#suprChallenge").show();
	});
	
	
});
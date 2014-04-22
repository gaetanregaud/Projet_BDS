$(document).ready(function() {
	
	$(".month").hide();
	$(".jour").hide();
	$(".info").hide();
	$(".voir").show();
	
	$(".moins").click(function(){
		var mois = this.value;
		$(".month").removeClass("voir");
		$("#"+mois).addClass("voir");
		$(".month").hide();
		$(".voir").show();
	});
	
	$(".plus").click(function(){
		var mois = this.value;
		$(".month").removeClass("voir");
		$("#"+mois).addClass("voir");
		$(".month").hide();
		$(".voir").show();
	});
	
	$(".day").click(function(){
		var date = this.title;
		$(".jour").removeClass("voir");
		$("#"+date).addClass("voir");
		$(".jour").hide();
		$(".voir").show();
	});
	
	$(".voirInfo").click(function(){
		var numinfo = this.value;
		$(".info").hide();
		$("#"+numinfo).show();
	});
	
});
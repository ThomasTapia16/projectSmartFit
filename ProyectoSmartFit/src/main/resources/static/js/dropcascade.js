$(document).ready(function() {
	
   $("#regiones").change(function() {
	console.log("epic faill");
      var region = $(this).val();
      console.log(region);
      var s = '<option value=' + -1 + '>SELECT</option>';
      if (region != "dkklka") {
		console.log("ooooh ajax ");
      	$.ajax({
        url : 'getSedes',
        data : { "region" : region},
        success : function(result) {
	console.log("succes siii");
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
		
        	  s += '<option value="' + result[i][0] + '">'+ result[i][0]+ '</option>';
        	}
        	$('#sedes').html(s);
        }
      });
     }
    $('#sedes').html(s);
     $('#pisos').html(s);
     

   });
   $("#sedes").change(function() {
      var nombresede = $(this).val();
      var s = '<option value=' + -1 + '>SELECT</option>';
      if (nombresede != "jaskda") {
      	$.ajax({
        url : 'getPiso',
        data : {"nombresede" : nombresede},
        success : function(result) {
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
        		s += '<option value="' + result[i][0] + '">'+ result[i][0]+ '</option>';
        	}
        	$('#pisos').html(s);
        }
       });
     }
     //reset data
     $('#pisos').html(s);
   
})
  
});
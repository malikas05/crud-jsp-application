function readCookie(name) {
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
  for(var i=0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') c = c.substring(1,c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
  }
  return null;
}

(function IIFEupdateGreeting(){
  var greeting = document.querySelector("span#greeting");
  var username = readCookie("user_greeting");
  if(!!username){
    greeting.innerText = greeting.innerText.replace("USER",username);
  }
})();

function fillEmployeesByDep() {
	for (var i = 1; i < 7; i++){
		$('#employee' + i).find('option').remove();
		$('#employee' + i).append($('<option>',{
			value: 0,
			text: "No Employee"
		}));
	}
	$.ajax({
	    type: "GET",
	    url: "employeesDepServlet?departmentID=" + $department.val(),
	    dataType: 'json',
	    contentType: 'application/json',
	    success: function(responseText){
	    		var data = responseText;
	
	    		$.each(data, function(index, tmp) {
	        		console.log(tmp.firstname);
	        		for (var i = 1; i < 7; i++){
	            		$('#employee' + i).append($('<option>',{
	            			value: tmp.id,
	            			text: tmp.firstname + " " + tmp.lastname
	            		}));
	        		}
	    		}); 
	    }
	});
	
	$department.removeClass('is-invalid');
}

$(document).ready(function() {
	var currentLocation = document.location.pathname.split("/").pop();
	var menuElement = $('.nav-item' + ' [href*="'+currentLocation +'"]');
	if( !!currentLocation && !!menuElement){
		menuElement.addClass('active');
	}
	
	$department = $('#departmentName');
	
	if (!!$department){
		fillEmployeesByDep()
	    $department.change (function(){
	    		fillEmployeesByDep()
	    });
	}
});

$(document).ready(function(){
	function confirmPassword(form,password){
		$(form).validate({
		    rules: {
		        password: {
		                        required: true,
		                        minlength: 5
		        },
		        password_confirm: {
		                        required: true,
		                        minlength: 5,
		                        equalTo: password
		        }
		    }

		}); 
	}
});
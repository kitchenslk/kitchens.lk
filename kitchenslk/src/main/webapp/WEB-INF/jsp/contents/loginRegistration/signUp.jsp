<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div>
	<form:form action="register.htm" modelAttribute="tempSystemUser"  method="post" onsubmit="return validateForm()">
		<table>			
			<tr>
				<td>User Name *</td>
				<td>:</td>
				<td><form:input path="tempUserName" id="tempUserName" name="tempUserName"/> &nbsp;&nbsp;<span id="usernameValidate"></span></td>
			</tr>
			<tr>
				<td>Password *</td>
				<td>:</td>
				<td><form:password path="tempPassword" id="tempPassword" name="tempPassword"/></td>
			</tr>
			<tr>
				<td>Confirm Password *</td>
				<td>:</td>
				<td><input type="password" id="confirmPassword"/></td>
			</tr>
			<tr>
				<td>Email Address</td>
				<td>:</td>
				<td><form:input path="emailAddress" id="emailAddress" name="emailAddress"/></td>
			</tr>
			<tr>
				<td>Contact Number</td>
				<td>:</td>
				<td><form:input path="contactNumber" id="contactNumber" name="contactNumber"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="submit" value="Register"/></td>
			</tr>
		</table>
	</form:form>

</div>

<script type="text/javascript">
	$(document).ready(function(){
		$('#tempUserName').blur(function(){
			checkIfUserNameExists($(this).val());
		});
		
		$('#confirmPassword').blur(function(){
			checkIfPasswordsMatch();
		});
	});
	function validateForm(){
		return true;
	}
	
	var userNameValid=false;
	var passwordsMatch=false;
	function checkIfUserNameExists(val){
		val=$.trim(val);
		if(!(val==null || val=="")){
			$.getJSON('checkifusernameexist.htm',{val:val},function(data){
				if(data.status==true){
					$('#usernameValidate').html('User Name alreday exists! please enter another one');
					userNameValid =false;
				}else{
					$('#usernameValidate').html('');
					userNameValid =true;
				}
			});
		}
	}
	
	function checkIfPasswordsMatch(){
		var password=$.trim($('#tempPassword').val());
		var confirmPassword=$.trim($('#confirmPassword').val());
		if(password==null || password==''){
			passwordsMatch=false;
			return false;
		}
		if(password==confirmPassword){
			passwordsMatch=true;
		}else{
			passwordsMatch=false;
		}
	}
</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">

</script>


<div class="right">
	
	<div>Hi <c:out value="${tempSystemUser.tempUserName}"></c:out> Congratulations! you have successfully registered with kitchenslk. 
	We have sent you a confirmation message to 
	<c:choose>
		<c:when test="${! empty  tempSystemUser.emailAddress}">
			<c:out value="${tempSystemUser.emailAddress}"></c:out>
		</c:when>
		<c:otherwise>
			<c:out value="${tempSystemUser.contactNumber}"></c:out>
		</c:otherwise>
	</c:choose>
	. 
	Please confirm your account by following the instructions given in the message. </div>	
	
</div>

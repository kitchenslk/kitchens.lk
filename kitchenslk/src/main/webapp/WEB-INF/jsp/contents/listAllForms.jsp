<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<a href="createNewForm.htm">Create New Form</a>

<ul> 
  <c:forEach items="${formList}" var="form">
	<li><a href="editForm.htm?formId=${form.formId}">${form.formName}</a></li>
  </c:forEach>
</ul>


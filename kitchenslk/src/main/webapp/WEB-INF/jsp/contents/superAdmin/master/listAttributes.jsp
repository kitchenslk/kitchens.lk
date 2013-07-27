<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div><a href="addattribute.htm">Create new Attribute</a></div>

<c:if test="${!empty message}">
	<h1>${message}</h1>
</c:if>
<br /><br />
<table>
	<tr>
		<td>Attribute Name</td>
		<td>Description</td>
		<td>Status</td>
		<td>Action</td>
	</tr>
<c:forEach items="${attributeList}" var="attribute">	
	<tr>
		<td><c:out value="${attribute.attributeName}"></c:out></td>
		<td><c:out value="${attribute.attributeDescription}"></c:out></td>
		<td><c:out value="${attribute.status}"></c:out></td>
		<td><a href="modifyattribute.htm?id=${attribute.attributeId}">Modify</a></td>
	</tr>
</c:forEach>
</table>
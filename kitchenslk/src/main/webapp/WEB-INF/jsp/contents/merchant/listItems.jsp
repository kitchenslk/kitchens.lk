<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div><a href="items/createitem.htm">create new item</a></div>

<c:forEach items="${itemList}" var="item">
	<c:out value="${item.itemName}"></c:out>
	<c:out value="${item.iitemDescription}"></c:out>
	
</c:forEach>
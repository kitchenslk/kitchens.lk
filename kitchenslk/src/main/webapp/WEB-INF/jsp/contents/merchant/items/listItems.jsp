<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div><a href="createitem.htm">create new item</a></div>

<table>
	<tr>
		<td>Item name</td>
		<td>Description</td>
		<td>Image</td>
		<td>Types</td>
	</tr>
	<c:forEach items="${itemList}" var="item">
		<tr>
			<td><c:out value="${item.itemName}"></c:out></td>
			<td><c:out value="${item.itemDescription}"></c:out></td>
			<td><img src="../${item.itemImage.fileUrl}" height="50" width="50" /></td>
			<td>
				<c:forEach items="${item.itemTypes}" var="itemType">
					<c:out value="${itemType.itemTypeName}"></c:out>,
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

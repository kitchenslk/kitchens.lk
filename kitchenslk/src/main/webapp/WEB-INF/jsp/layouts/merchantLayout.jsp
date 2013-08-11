<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:insertAttribute name="title" ignore="true" /></title>
   <title>Form Wizard</title>
    <link rel="stylesheet" href='<c:url  value="/css/jquery-ui.min.css" />' />
    <script type="text/javascript" src="<c:url  value="/js/jquery-1.9.1.min.js" />"></script>
    <script type="text/javascript" src="<c:url  value="/js/jquery-ui.min.js" />"></script>
    <script type="text/javascript" src="<c:url  value="/js/jquery.tmpl.min.js" />">"></script>   
    <script type="text/javascript" src="<c:url value="/js/ajaxupload.js"/>"></script> 
    
   
</head>
<body>
	<tiles:insertAttribute name="body" />	
</body>
</html>
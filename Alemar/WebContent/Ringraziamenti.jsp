

<%
	Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti");
	AccountBean account= (AccountBean) session.getAttribute("currentSessionUser");   
	ProductBean prodotto = (ProductBean) request.getAttribute("prodotto");
	Cart cart = (Cart) request.getAttribute("Cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.*"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script type='text/javascript' src="gradient.js"></script> 
	
</head>

<body>
	<jsp:include page="header.jsp"/>
	
	<% if(account==null){ %>
		<jsp:include page="navigationNull.jsp"/>
	<% }else { 
			if(account.getAdmin()==0){ %>
				<jsp:include page="navigation.jsp"/>
		<% }if(account.getAdmin()==1){ %>	
				<jsp:include page="navigationAdmin.jsp"/>
		<%  }}  %>	
		
	<div id="gradient" >
		<h1>Grazie per l'acquisto!!</h1>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>

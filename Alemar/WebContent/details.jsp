
<%
	ProductBean prodotto = (ProductBean) request.getAttribute("prodotto");
	AccountBean account= (AccountBean) session.getAttribute("currentSessionUser");
%>

<!DOCTYPE html>
<html>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.*"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<link href="menu.css" rel="stylesheet" type="text/css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script type='text/javascript' src="gradient.js"></script> 
	<title>AlemarSport DS/BF</title>
</head>

<body>
	<jsp:include page="header.jsp"/>
	<% if(account==null){ %>
		<jsp:include page="navigationNull.jsp"/>
	<% }else { 
			if(account.getAdmin()==0){ %>
				<jsp:include page="navigation.jsp"/>
		<% } else if(account.getAdmin()==1){ %>	
				<jsp:include page="navigationAdmin.jsp"/>
		<%  }}  %>	
	<div id="gradient" >
	
		<br><br><br><br><br>
				<br><img src="./GetImmaginiServlet?id=<%=prodotto.getCode()%>" onerror="scarpe" width="200px" height="200px">
				<br>Nome: <%=prodotto.getName()%>
				<br>Descrizione: <%=prodotto.getDescription()%>
				<br>Prezzo: <%=prodotto.getPrice()%>
				<br>Quantità: <%=prodotto.getQuantity()%>
				<br>Sconto: <%=prodotto.getSconto()%>
				<br>
				<br><a href="prodotto?action=addC&IDProdotto=<%=prodotto.getCode()%>">INSERIMENTO CARRELLO</a>

	</div>	
		<jsp:include page="footer.jsp"/>
			
	</body>
</html>
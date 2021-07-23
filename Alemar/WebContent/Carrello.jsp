
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
	<link href="new.css" rel="stylesheet" type="text/css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script type='text/javascript' src="gradient.js"></script> 
	<title>ACQUISTO</title>
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
		<h2>Carrello</h2>
		<table class="container2">
		<tr>
			<th>Nome</th>
			<th>Costo totale</th>
			<th>Quantità</th>
			<th>Action</th>
		</tr>
		<%
		
		try{
		List<CartProduct> ProdottiCarrello= cart.getProducts();
		   for(CartProduct beancart: ProdottiCarrello) {
			  
		%>
		<tr>
			<% if(beancart.getPezzi()>0){ %>
			<td><%=beancart.getNome() %></td>
			<td><%=beancart.Costototale() %>0</td>
			<td><%=beancart.getPezzi() %></td>
			<td><a href="carrello?action=deleteC&IDProdotto=<%=beancart.getIDProdotto()%>">CANCELLA UN ARTICOLO</a></td>
			<%}}%>
			
			<%if(account!=null){ %>
			<td><a href="carrello?action=acquista">ACQUISTA</a></td>
			<%}%>
		<%if(account==null) {%><td><a href="LoginPage.jsp">ACQUISTA</a></td>
		<%}}
		catch(NullPointerException e){
			e.printStackTrace();%>
			<td>vuoto</td>
			<td>vuoto</td>
			<td>vuoto</td>
			<td>vuoto</td>
		<% }%>
		</tr>
		
	</table>		
	</div>
	<jsp:include page="footer.jsp"/>
		
		
	
</body>
</html>
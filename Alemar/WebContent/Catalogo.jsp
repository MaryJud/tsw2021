

<%
	Collection<?> prodotti = (Collection<?>) request.getAttribute("Vcatalogo");
	if(prodotti == null) {
		response.sendRedirect("prodotto");	
		return;
	}
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
		<br>
		<h2>GESTIONE PRODOTTI</h2>
		
		<%
		int i=0;
		int j=0;
	
		String[] nomi= {"column side","column middle","column side"};
		if (prodotti != null && prodotti.size() != 0) {
	               Iterator<?> it = prodotti.iterator();
	               ArrayList<ProductBean> ls= new ArrayList<ProductBean>();
	               while (it.hasNext()) {
	                   ProductBean bean = (ProductBean) it.next();
	                   ls.add(bean);
	                   System.out.println(ls.get(i).toString());
	                   i++;
	                   }
	               
	             %>
	      <div class= "row">
	            <% for(i=0;i<ls.size();i++){%>
	           		

	 			<div class="<%=nomi[j]%>">
	    		 <img src="./GetImmaginiServlet?id=<%=ls.get(i).getCode()%>" onerror="scarpe" width="200px" height="200px">	
				<br>
				<%=ls.get(i).getName()%>
				<br>
				<%=ls.get(i).getPrice()%>0
				<br>
				<a href="prodotto?action=read&IDProdotto=<%=ls.get(i).getCode()%>">DETTAGLI</a>
				<br>
				<a href="carrello?action=addC&IDProdotto=<%=ls.get(i).getCode()%>">INSERIMENTO CARRELLO</a>
				
	 	 		</div>
 	 			
 
		<%
		if(j<2) j++;
			else j=0;
		
	            }
			} 
		%>
		</div>
		
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
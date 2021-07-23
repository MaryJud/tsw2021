<%
    OrdineBean ordine = (OrdineBean) request.getAttribute("ordine");
	AccountBean account= (AccountBean) session.getAttribute("currentSessionUser");

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
    <title>Dettagli Ordine</title>
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
	<br><br><br><br><br><br><br>
        <h2>DETTAGLI ORDINE</h2>

        <table class="container2">
        <tr>
            <th>IDOrdine</th>
            <th>DataOrdine</th>
            <th>CostoTotaleOrdine</th>
            

        </tr>
        <tr>
            <td><%=ordine.getIDOrdine()%></td>
            <td><%=ordine.getDataOrdine()%></td>
            <td><%=ordine.getPrezzoFinale()%></td>

        </tr>

        </table>
   		
        
      <jsp:include page="footer.jsp"/>
    </div>
    </body>
</html>

<%

	Collection<?> Vordini = (Collection<?>) request.getAttribute("Vordini");
	if(Vordini == null) {
		response.sendRedirect("./ordini");	
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
	<link href="new.css" rel="stylesheet" type="text/css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script type='text/javascript' src="gradient.js"></script> 
	<title>Ordini</title>
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
	
	<div id="gradient" style="text-align: center">
	<h2>ORDINI</h2>
	<table class="container2">
		<% if(account.getAdmin()==1){ %>
				<form action="ordini" method="get">
				<input type="hidden" name="action" value="ricerca"> 
				<label for="IDAccount"> ID account: </label><br> 
				<input name="IDAccount" type="text" placeholder="inserisci Id account"><br> 
				<input type="submit" value="submit"><input type="reset" value="Reset">
				</form>
				<br>
				<form action="ordini" method="get">
				<input type="hidden" name="action" value="ricercaData"> 
				<label for="DataOrdine1">Data inizio: </label><br> 
				<input name="DataOrdine1" type="date" maxlength="20"  placeholder="inserisci data inizio"><br> 
				<label for="DataOrdine2">Data fine: </label><br>
				<input name="DataOrdine2" type="date" maxlength="20"  placeholder="inserisci data fine"><br> 
				<input type="submit" value="submit"><input type="reset" value="Reset">
				</form>
			
		<%} %>
		
		<thead>
		<tr>
			<th>IDOrdine</th>
			<th>NumeroProdotti </th>
			<th>DescrizioneProdotti</th>
			<th>StatoOrdine</th>
			<th>DataSpedizione</th>
			<th>DataConsegna</th>
			<th>DataOrdine</th>
			<th>CostoTotaleOrdine</th>
			<th>Action</th>
			</tr>
		</thead>
		<%
			if (Vordini != null && Vordini.size() != 0) {
				Iterator<?> it = Vordini.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
					
		%>
		
			<tbody>
				<tr>
				<td><%=bean.getIDOrdine()%></td>
				<td><%=bean.getNumProdotti()%></td>
				<td><%=bean.getDescrizioneProdotti() %></td>
				<td><%=bean.getStatoOrdine() %></td>
				<td><%=bean.getDataSpedizione()%></td>
				<td><%=bean.getDataConsegna() %></td>
				<td><%=bean.getDataOrdine()%></td>
				<td><%=bean.getPrezzoFinale()%></td>
				<td><a href="ordini?action=readOrdine&IDOrdine=<%=bean.getIDOrdine()%>">DETTAGLI</a>
				</tr>
			</tbody>
		<%	}}
		 	
			else {
		%>
		<tr>
			<td colspan="6">Non ci sono ordini</td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<jsp:include page="footer.jsp"/>
	
</body>
</html>

<% AccountBean account= (AccountBean) session.getAttribute("currentSessionUser"); %>
<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.*"%>
	<meta charset="ISO-8859-1">
	<title>Modifica Prodotti</title>
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">	
	<link href="new.css" rel="stylesheet" type="text/css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script type='text/javascript' src="gradient.js"></script> 
	</head>
<body>

  	<div id="gradient">
	
	<jsp:include page="header.jsp"/>
		
	
	<% if(account==null){ %>
		<jsp:include page="navigationNull.jsp"/>
	<% }else { 
			if(account.getAdmin()==0){ %>
				<jsp:include page="navigation.jsp"/>
		<% } else if(account.getAdmin()==1){ %>	
				<jsp:include page="navigationAdmin.jsp"/>
		<%  }}  %>	
	
	<div class="form">
	  <div class="form-toggle"></div>
	  <div class="form-panel one">
	    <div class="form-header">
		<h1>Modifica prodotti</h1>
	</div>
	<div class="form-content">
  	<form action="prodotto" method="get">
  		<div class="form-group">
			<input type="hidden" name="action" value="modifica"> 
			<label for="IDProdotto">Codice a barre prodotto da modificare: </label><br> 
			<input name="IDProdotto" type="text" maxlength="20"  placeholder="inserisci codice a barre"><br> 
		</div>
		<div class="form-group">
			<label for="CodiceCategoria">CodiceCategoria: </label><br>
		
			<textarea name="CodiceCategoria" maxlength="3" rows="3" placeholder="inserire codice categoria prodotto"></textarea><br>
		</div>
		<div class="form-group">
			<label for="Nome">Nome:</label><br>
		
			<textarea name="Nome" maxlength="45" rows="3" placeholder="inserire nome prodotto"></textarea><br>
		</div>
		<div class="form-group">
			<label for="Descrizione">Descrizione: </label><br>
		
			<textarea name="Descrizione" maxlength="100" rows="3"  placeholder="inserire descrizione prodotto"></textarea><br>
		</div>
		<div class="form-group">
			<label for="PrezzoNoIVA">Prezzo:</label><br> 
	
			<input name="PrezzoNoIVA" type="number" min="0" value="0" ><br>
		</div>
		<div class="form-group">
			<label for="Sconto">Sconto: </label><br> 
		
			<input name="Sconto" type="number" min="0" value="0" ><br>
		</div>
		<div class="form-group">
			<label for="Disponibilita">Disponibilità: </label><br> 
		
			<input name="Disponibilita" type="number" min="0" value="0" ><br>
		</div>
		<div class="form-group">
			<button type="submit" value="submit">SUBMIT</button>
			<button type="reset" value="Reset">RESET</button>
		</div>
		</form>
	</div>
	</div>
	</div>
	<jsp:include page="footer.jsp"/>
	</div>
	
	
</body>
</html>
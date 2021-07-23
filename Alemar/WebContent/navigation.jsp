<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.*"%>
    
<% Cart cart = (Cart) request.getAttribute("Cart"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="new.css" rel="stylesheet" type="text/css">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">  	
	<title>Menù</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'><link rel="stylesheet" href="./style.css">
	
</head>
<body>
				<div class="dropdown">
			  <img class="dropbtn" src="menu.png" width="50px">
			  <div class="dropdown-content">
			   		<a href="./ProductV.jsp"><img src="home.png" width="30px" height="22px"></a>
		   			<a href="LoginPage.jsp">LOGIN</a>
		   			<a href="Registrazione.jsp">REGISTRAZIONE</a>
					<a href="ordini?action=Vordini">ORDINI</a>
					<a href="carrello?action=viewC"><img src="carrello.png" width="30px" height="22px"></a>
			</div>
			</div>
			<div class="container teal borderYtoX">
				
					<a href="./ProductV.jsp"><img src="home.png" width="30px" height="22px"></a>
		   			<a href="LoginPage.jsp">LOGIN</a>
		   			<a href="Registrazione.jsp">REGISTRAZIONE</a>
					<a href="ordini?action=Vordini">ORDINI</a>
					<a href="carrello?action=viewC"><img src="carrello.png" width="30px" height="22px"></a>
				
				<br>
				<br>
			</div>
		
	
</body>
</html>
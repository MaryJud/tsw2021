
<% AccountBean account= (AccountBean) session.getAttribute("currentSessionUser");   %>
<!DOCTYPE html>

<html>
	<head>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.*"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="new.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src="login.js"></script> 	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script type='text/javascript' src="gradient.js"></script>  
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'><link rel="stylesheet" href="./new.css">
	<title>Pagina di Login</title>
	
	<style>
  			.alert {
			  padding: 20px;
			  background-color: #f44336;
			  color: white;
			}
			
			.closebtn {
			  margin-left: 15px;
			  color: white;
			  font-weight: bold;
			  float: right;
			  font-size: 22px;
			  line-height: 20px;
			  cursor: pointer;
			  transition: 0.3s;
			}
			
			.closebtn:hover {
			  color: black;
			}
		</style>
</head>
	
	<body style= "background-image:url('corridore.jpg');">
	
	
	
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
	      <h1>Registrazione Account</h1>
	    </div>
	    <div class="form-content">
	      <form action="Registrazione" method="get">
	      
	        <div class="form-group">
	          	<label for="Username">Username:</label><br> 
        		<input name="Username" type="text"   placeholder="Inserisci l'Username"><br> 
	        </div>
	        <div class="form-group">
	         	 <label for="Password">Password:</label><br>
	         	 <br>
        		 <input name="Password" type="password"  placeholder="Inserisci la password " id="pw"  ><br>
	        </div>
	        <div class="form-group">
	         	 <label for="Nome">Nome:</label><br> 
        		 <br><input name="Nome" type="text"   placeholder="Inserisci il Nome"><br> 
	        </div>
	        <div class="form-group">
	         	 <label for="Cognome">Cognome:</label><br>
       			 <input name="Cognome" type="text"  placeholder="Inserisci il Cognome"><br>
	        </div>
	        <div class="form-group">
	         	 <label for="Email">Email:</label><br> 
	         	 <br>
      			 <input name="Email" type="text"  placeholder="Inserisci l' Email" id="em" ><br>
      			 
	        </div>
	        <div class="form-group">
	         	  <label for="CF">Codice Fiscale:</label><br> 
       			  <input name="CF" type="text"  placeholder="Inserisci il Codice Fiscale"><br>
	        </div>
	        <div class="form-group">
	        
	       <button type="submit">Registrati</button>
	        <% System.out.println("Siamo qui"); %>	  
	        </div>
	      </form>
	    </div>
	  </div>
	</div>
	
	<br><br><br><br><br><br><br>
	
	<jsp:include page="footer.jsp"/>
		
	</body>
	
</html>

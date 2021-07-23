
<%AccountBean account= (AccountBean) session.getAttribute("currentSessionUser"); %>
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
	      <h1>Account Login</h1>
	    </div>
	    <div class="form-content">
	     <form action="Login" method="get">
	        <div class="form-group">
	        
	          <label for="uname">Username</label>
	          <input type="text" id="username" name="Username" required="required"/>
	        </div>
	        <div class="form-group">
	          <label for="psw">Password</label>
	          <input type="password" id="password" name="Password" required="required"/>
	        </div>
	        <div class="form-group">
	          <label class="form-remember">
	            <input type="checkbox"/>Remember Me
	          </label>
	        </div>
	        <div class="form-group">
	          <button type="submit">Log In</button>
	        </div>
	      </form>
	    </div>
	  </div>
		<% if(request.getAttribute("loginResult") != null && request.getAttribute("loginResult") == "true"){%>		

		<div class="alert">
		  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		  <strong>Danger!</strong> Indicates a dangerous or potentially negative action.
		</div>

  		<%}%>
	 
	</div>
		<jsp:include page="footer.jsp"/>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='https://codepen.io/andytran/pen/vLmRVp.js'></script><script  src="./login.js"></script>
	</body>
	
</html>

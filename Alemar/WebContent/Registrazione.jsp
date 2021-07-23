
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
	
</head>
	
	<body style= "background-image:url('corridore.jpg');">
	<script type="text/javascript">
	function check(obj){
                var valid1=true;
                var valid2=true;
                var valid3=true;
                var valid4=true;
                valid1=checkemail();
                valid2=checkpassword();
                valid3=checkcvv();
                valid4=checkcard();
                if(valid1==true && valid2==true && valid3==true && valid4==true ){
                    obj.submit();
                }
                
            }
            
            function checkemail(ema){
                var email=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                var ema=document.getElementById("em");
                if(ema.value.match(email)){
                    ema.classList.remove("error");
                    document.getElementById("emerr").innerHTML = "";
                    return true;
                }
                else{
                    ema.classList.add("error");
                    document.getElementById("emerr").innerHTML = "inserire un email valida";
                    return false;
                }
                    
                }
            
            function checkpassword(pw){
                
                var password=/^(?=.{10,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W).*$/;
                var pw=document.getElementById("pw");
                if(pw.value.match(password)){
                    pw.classList.remove("error");
                    document.getElementById("pwerr").innerHTML = " ";
                    return true;
                }
                else{
                    
                    pw.classList.add("error");
                    document.getElementById("pwerr").innerHTML = "Almeno 10 caratteri, Almeno 1 maiuscolo, Almeno 1 minusco Almeno, 1 speciale";
                    return false;
                }
                    
                }

            function checkcvv(cvv){
                
                var name=/[0-9]{3}/;
                var cvv=document.getElementById("cvv");
                if(cvv.value.match(name)){
                    cvv.classList.remove("error");
                    document.getElementById("cvverr").innerHTML = " ";
                    return true;
                }
                else{
                    
                    cvv.classList.add("error");
                    document.getElementById("cvverr").innerHTML = "Almeno 3 numeri";
                    return false;
                }
                    
                }
            function checkcard(card){
                
                var name=/[0-9]{5}/;
                var card=document.getElementById("card");
                if(card.value.match(name)){
                    card.classList.remove("error");
                    document.getElementById("carderr").innerHTML = " ";
                    return true;
                }
                else{
                
                    card.classList.add("error");
                    document.getElementById("carderr").innerHTML = "Almeno 16 numeri";
                    return false;
                }
                    
                }
	
    </script>
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
	        <span id="pwerr" class="errormx"></span> <br> <br>
	         	 <label for="Password">Password:</label><br>
	         	 <br>
        		 <input name="Password" type="password"  placeholder="Inserisci la password " id="pw"   required onblur="checkpassword()"><br>
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
	        <span id="emerr" class="errormx"></span> <br>
	         	 <label for="Email">Email:</label><br> 
	         	 <br>
      			 <input name="Email" type="text"  placeholder="Inserisci l' Email" id="em" required onblur="checkemail()"><br>
      			 
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

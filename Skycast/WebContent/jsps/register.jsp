<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setBundle basename="com.skycast.nl.resources.Skycast"/>
<html>
	<head>
    	<title>
        	Weather App
    	</title>

    	<!-- Bootstrap Core CSS -->
    	<link href="http://www.tutorialspoint.com/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    	<!-- Custom CSS -->
    	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	</head>

	<body>
		<form id="register" action="${pageContext.request.contextPath}/RegisterController" method="post">
        	<h1 align="center">
        		<fmt:message key="register.heading"/>
      		</h1>
        	<div class="card card-container">
            	<div style="color: #FF0000;">${errorMessage}</div>
                <span class="username"></span>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username">
                <br>
	            <input type="button" class="btn" value="Register" onclick="submitForm();">
	            <br><br>
	            
            </div>
        </form>
	</body>
	
	<script type="text/javascript">
		function submitForm(){
			var username = document.getElementById("username");
			if(""!=username.value.trim()){
				document.getElementById("register").submit();
			}else{
				alert("Please enter your username");
			}
		}
	</script>
	


</html>
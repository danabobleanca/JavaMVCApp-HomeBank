<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@ include file="style.css" %> </style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<header><h1>Home Bank</h1></header>
		<section id="home">
			<div id="home_box1">
			<h4> <i class="fa fa-lock"></i>Please login</h4>
			<h5>Are you a customer and do you have a Home Bank account? Login to access your account!</h5>
			<form:form action="/HomeBank/loginSuccess" method="post" modelAttribute="loginDetails">
				<label for="username">Username</label>
				<form:input path="username" name="username" id="username" /><br/>
				<form:errors path="username" class="error"/>
				
				<label for="password">Password</label>
				<form:input type="password" path="password" name="password" id="password" />
				<form:errors path="password" class="error"/>
				
				<input type="submit" value="LOGIN">
			</form:form>
			</div>
			<div id="home_box2">
			
			</div>
		</section>
	</div>
</body>
</html>
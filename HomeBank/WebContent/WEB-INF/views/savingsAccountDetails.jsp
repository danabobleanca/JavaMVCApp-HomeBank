<html>
<head>
<style><%@ include file="style.css" %> </style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<header><h1>Home Bank</h1></header>
		<section id="accountDetailsPage">
			
			
			
										<!-- MENU PORTOFOLIO -->
						
			<div id="welcome_box2">
				<div class="payments_details" id="accountDetails">
					<h3>ACCOUNT DETAILS</h3><br/>
					<div id="containerSuccessTransaction">
						<p>IBAN: </p><span>${iban}</span><br/><br/>
						<p>ACCOUNT HOLDER: </p><span>${name}</span><br/><br/>
						<p>BANK: </p><span>Name of the bank</span><br/><br/>
						<p>TYPE OF ACCOUNT: </p><span>Savings Account</span><br/><br/>
						<p>CURRENCY: </p><span>RON</span><br/><br/>
						<p>SWIFT: </p><span>BANKROBU</span><br/><br/>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
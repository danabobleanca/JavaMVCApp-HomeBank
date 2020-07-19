<html>
<head>
<style><%@ include file="style.css" %> </style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<meta charset="ISO-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<header><h1>Home Bank</h1></header>
		<section id="welcome">
			<div id="welcome_box1">
			
						<!-- MENU WELCOME -->
						
						
				<div id="portofolio" class="nav_welcome">
					<a href="/HomeBank/portofolio"><h4>PORTOFOLIO <i class="fa fa-angle-double-right"></i></h4></a>
					<div class="portofolio_account">
						<h5> <i class="fa fa-bank"></i> Current Accounts</h5>
						<div class="portofoliu_box">
						<hr/>
						<h6>${iban}<span id="balance">${balance} RON</span></h6>
						</div>
					</div>
					<div class="portofolio_account">
						<h5><i class='fas fa-piggy-bank'></i>Savings account</h5>
						<div class="portofoliu_box">
						<hr/>
						<h6>${ibanSavings}<span id="balance">${balanceSavings} RON</span></h6>
						</div>
					</div>
					<div class="portofolio_account">
						<h5> <i class="fa fa-credit-card"></i>Credit Account</h5>
						<div class="portofoliu_box">
						<hr/>
						<h6>${ibanCredit}<span id="balance">${balanceCredit} RON</span></h6>
						</div>
					</div>
				</div>
				<div id="payments" class="nav_welcome">
					<h4>PAYMENTS</h4>
					<div id="payment_my_accounts">
						<a href="/HomeBank/paymentBetweenMyAccounts"><h5><i class="fa fa-refresh" aria-hidden="true"></i>Payment between my accounts<i class="fa fa-angle-double-right"></i></h5></a>
					</div>
					<div id="payment_ron">
						<a href="/HomeBank/paymentInRon"><h5><i class="fa fa-money" aria-hidden="true"></i>Payment in RON<i class="fa fa-angle-double-right"></i></h5></a>
					</div>
				</div>
			</div>
			
			
										<!-- MENU PORTOFOLIO -->
			
						
			<div id="welcome_box2">
				<div class="payments_details" id="successfulPaymentBetweenAccounts">
					<h3>TRANSACTION SUCCESSFULLY COMPLETED!!</h3><br/>
					<div id="containerSuccessTransaction">
						<p>From IBAN: </p><span>${myIban}</span><br/><br/>
						<p>To IBAN: </p><span>${otherIban}</span><br/><br/>
						<p>Value of transaction: </p><span>${payment}</span>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
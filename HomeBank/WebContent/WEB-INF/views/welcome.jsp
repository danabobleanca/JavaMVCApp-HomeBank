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
							<!-- MENU WELCOME -->
						
			<div id="welcome_box1">
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
				<div class="welcome_details">
					<div id="welcome_details_current-account" class="welcome_details_box">
						<h3><i class="fa fa-bank"></i>Current Accounts<i class="fa fa-plus-square"></i></h3>
						<h4>Current account<span id="balance">${balance}</span></h4>
						<h5>${iban}<span id="balance">RON</span></h5>
						<div class="account_options">
							<div class="option">
								<a href="/HomeBank/paymentInRon">
								<i class="fa fa-wallet" aria-hidden="true"></i>
								<p>Payment in RON</p>
								</a>
							</div>
							<div class="option">
								<a href="/HomeBank/currentAccountReport">
								<i class="fa fa-list" aria-hidden="true"></i>
								<p>Transaction report</p>
								</a>
							</div>
							<div class="option">
								<a href="/HomeBank/currentAccountInformations">
								<i class="fa fa-code-fork" aria-hidden="true"></i>
								<p>My IBAN</p>
								</a>
							</div>
						</div>
					</div>
					
					
					<div id="welcome_details_savings-account" class="welcome_details_box">
						<h3><i class='fas fa-piggy-bank'></i>Savings Accounts<i class="fa fa-plus-square"></i></h3>
						<h4>Savings account<span id="balance">${balanceSavings}</span></h4>
						<h5>${ibanSavings}<span id="balance">RON</span></h5>
						<div class="account_options">
							<div class="option">
							<a href="/HomeBank/saving">
								<i class='fas fa-piggy-bank'></i>
								<p>Saving</p>
							</a>
							</div>
							<div class="option">
								<a href="/HomeBank/savingsAccountReport">
								<i class="fa fa-list" aria-hidden="true"></i>
								<p>Transaction report</p>
								</a>
							</div>
							<div class="option">
								<a href="/HomeBank/savingsAccountInformations">
								<i class="fa fa-code-fork" aria-hidden="true"></i>
								<p>My IBAN</p>
								</a>
							</div>
						</div>
					</div>
					
					
					<div id="welcome_details_credit-account" class="welcome_details_box">
						<h3><i class="fa fa-credit-card"></i>Credit Accounts<i class="fa fa-plus-square"></i></h3>
						<h4>Credit account<span id="balance">${balanceCredit}</span></h4>
						<h5>${ibanCredit}<span id="balance">RON</span></h5>
						<div class="account_options">
							<div class="option">
								<a href="/HomeBank/refund">
								<i class="fa fa-database" aria-hidden="true"></i>
								<p>Refund</p>
								</a>
							</div>
							<div class="option">
								<a href="/HomeBank/creditAccountReport">
								<i class="fa fa-list" aria-hidden="true"></i>
								<p>Transaction report</p>
								</a>
							</div>
							<div class="option">
								<a href="/HomeBank/ratesGraph">
								<i class="fa fa-list-alt" aria-hidden="true"></i>
								<p>To reimburse</p>
								</a>
							</div>
						</div>
					</div>
					
					
					
					<div id="welcome_details_payments" class="welcome_details_box">
						<h3>Payments<i class="fa fa-refresh" aria-hidden="true"></i></h3>
						<a href="/HomeBank/paymentBetweenMyAccounts"><h4>Payments between my accounts <i class="fa fa-plus-square"></i></h4></a>
						<hr/>
						<a href="/HomeBank/paymentInRon"><h4>Payments in RON<i class="fa fa-plus-square"></i></h4></a>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
										<!-- MENU PORTOFOLIO -->
			
			<div id="report">
			<p> Rates to pay for credit account ${ibanCredit}</p> 
				<table>
					<tr><td class="amount">AMOUNT REMAINING TO PAY ${remainingToPay} </td></tr>
					<tr><td id="amount_paid" class="amount">AMOUNT PAID ${totalPaid} </td></tr>
					<tr>
						<td class="thead">TRANSACTION ID</td>
						<td class="thead">TRANSACTION AMOUNT</td>
						<td class="thead">DATE TO PAY THE RATE</td>
					</tr>
					<tbody>
					<c:forEach items="${ratesList}" var="rate">
					<tr>
						<td> <c:out value="${rate.id}" /></td>
						<td> <c:out value="${rate.rateToPay}" /></td>
						<td> <c:out value="${rate.dateOfPayRate}" /></td>
					</tr>
					</c:forEach>
					</tbody>
			</table>
		</div>
	</div>
</body>
</html>

<%@page import="com.rays.bean.PaymentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<%
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	PaymentBean bean = (PaymentBean) request.getAttribute("bean");
	%>

	<div align="center">
		<%
		if (bean != null && bean.getId() > 0) {
		%>
		<h3>Update Payment</h3>
		<%
		} else {
		%>
		<h2>Add Payment</h2>
		<%
		}
		%>

		<%
		if (successMsg != null) {
		%>
		<h3 style="color: green;"><%=successMsg%></h3>
		<%
		}
		%>

		<%
		if (errorMsg != null) {
		%>
		<h3 style="color: red;"><%=errorMsg%></h3>
		<%
		}
		%>

		<form action="PaymentCtl.do" method="post">
			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>">
			<table>
				<tr>
					<th>Name</th>
					<td><input type="text" name="Name"
						value="<%=bean != null ? bean.getName() : ""%>"
						placeholder="enter Customer name"></td>
				</tr>
				<tr>
					<th>Account</th>
					<td><input type="text" name="Account"
						value="<%=bean != null ? bean.getAccount() : ""%>"
						placeholder="Enter payment mode"></td>
				</tr>
				<tr>
					<th>Upi_id</th>
					<td><input type="text" name="upi_id"
						value="<%=bean != null ? bean.getUpi_id() : ""%>"
						placeholder="enter your id"></td>
				</tr>
				<tr>
					<th>Total_amount</th>
					<td><input type="text" name="Total_Amount"
						value="<%=bean != null ? bean.getTotal_amount() : ""%>"
						placeholder="enter Total_Amount"></td>
				</tr>
				<tr>
					<th>Payment date </th>
					<td><input type="text" name="dob"
						value="<%=bean != null ? bean.getDob() : ""%>"
						placeholder="Enter date of birth"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "update" : "save"%>"></td>
				</tr>
			</table>

		</form>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>

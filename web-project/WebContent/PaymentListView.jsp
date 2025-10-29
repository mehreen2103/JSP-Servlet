<%@page import="com.rays.bean.PaymentBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		List list = (List) request.getAttribute("list");
		List nextlist = (List) request.getAttribute("nextList");
		String successMsg = (String) request.getAttribute("successMsg");
		String errorMsg = (String) request.getAttribute("errorMsg");
		int pageNo = (int) request.getAttribute("pageNo");
	%>

	<%@ include file="Header.jsp"%>
	<div align="center">

		<h3>Payment List</h3>
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
		<form action="PaymentListCtl.do" method="post">

			<table>
				<tr>
					<th>Name</th>
					<td><input type="text" name="tName" value=""
						placeholder="search by first name"></td>
					<td><input type="submit" name="operation" value="search"></td>

					<th>Dob</th>
					<td><input type="date" name="dob" value=""></td>

					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>

			<table border="1px" width="100%">

				<tr style="background-color: skyblue">
					<th>Delete</th>
					<th>id</th>
					<th>name</th>
					<th>account</th>
					<th>upi_id</th>
					<th>total_amount</th>
					<th>dob</th>
					<th>Edit</th>
				</tr>

				<%
					Iterator<PaymentBean> it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						PaymentBean bean = it.next();
				%>
				<tr align="center" style="background-color: #D3D3D3">
					<td><input type="checkbox" value="<%=bean.getId()%>"
						name="ids"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getAccount()%></td>
					<td><%=bean.getUpi_id()%></td>
					<td><%=bean.getTotal_amount()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="PaymentListCtl.do?id=<%=bean.getId()%>">edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width="100%">
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="previous"
						<%=pageNo == 1 ? "disabled" : ""%>></td>
					<td><input type="submit" name="operation" value="delete"></td>
					<td align="right"><input type="submit" name="operation"
						value="next" <%=nextlist.size() == 0 ? "disabled" : ""%>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">

		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
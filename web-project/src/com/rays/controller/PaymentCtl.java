package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.PaymentBean;
import com.rays.bean.UserBean;
import com.rays.model.PaymentModel;
import com.rays.model.UserModel;


@WebServlet("/PaymentCtl.do")
public class PaymentCtl extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PaymentModel model = new PaymentModel();
		PaymentBean bean = new PaymentBean();

		String id = request.getParameter("id");
		System.out.println("id >>>>-- " + id);

		if (id != null) {
			try {
				bean = model.findById(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("PaymentView.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		PaymentBean bean = new PaymentBean();
		PaymentModel model = new PaymentModel();

		String Name = request.getParameter("Name");
		String Account = request.getParameter("Account");
		String Upi_id = request.getParameter("Upi_id ");
		String Total_amount = request.getParameter("Total_amount");
		String dob = request.getParameter("dob");

		try {
			bean.setName(Name);
			bean.setAccount(Account);
			bean.setUpi_id(Upi_id);
			bean.setTotal_amount(Total_amount);
			bean.setDob(sdf.parse(dob));

			if (op.equals("update")) {
				bean.setId(Integer.parseInt(request.getParameter("id")));
				model.update(bean);
				request.setAttribute("successMsg", "Payment updated Successfully");
			} else {
				model.add(bean);
				request.setAttribute("successMsg", "payment added Successfully");
			}

		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("PaymentView.jsp");
		rd.forward(request, response);

	}

}

package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.model.User;
import com.project1.service.impl.Project1ServiceImpl;

/**
 * Servlet implementation class TransferServlet
 */
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project1ServiceImpl p1s = new Project1ServiceImpl();
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		String returnMessage;
		
		ObjectMapper mapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.html");
		} else {
			User user = (User) session.getAttribute("user");
			Account acc = (Account) session.getAttribute("acc");
			try {
				int targetId = Integer.parseInt(bodyObj.get("targetAccount").textValue());
				double amount = Double.parseDouble(bodyObj.get("amount").textValue());
				p1s.transferMoney(user, acc.getAccountId(), targetId, amount);
				returnMessage = "Transfer successful";
				pw.write(returnMessage);
			} catch (NumberFormatException e) {
				returnMessage = "Invalid input(s) - please try again";
				pw.write(returnMessage);
			} catch (BusinessException e) {
				returnMessage = e.getMessage();
				pw.write(returnMessage);
			}
		}
	}

}

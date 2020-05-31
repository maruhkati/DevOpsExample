package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.Project1Service;
import com.project1.service.impl.Project1ServiceImpl;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In get for some reason");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project1Service p1s = new Project1ServiceImpl();	
		ObjectMapper mapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		PrintWriter pw = response.getWriter();
		
		User u = null;
		String username = bodyObj.get("userName").textValue();
		String password = bodyObj.get("password").textValue();
		
		try {
			u = p1s.getUser(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			System.out.println(session.getAttribute("user"));
			if(u.isEmployee()) {
				pw.write("employee_page.html");
			} else {
				pw.write("customer_page.html");
			}
		} catch (BusinessException e) {
			pw.write("index.html");
		}
		
	}

}

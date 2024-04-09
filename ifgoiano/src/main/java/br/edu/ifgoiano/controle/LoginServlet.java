package br.edu.ifgoiano.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String email = req.getParameter("email");
		final String password = req.getParameter("password");
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		
		if (email.equals("pweb@gmail.com") && password.equals("09042024")) {
			req.getRequestDispatcher("autenticado.jsp").forward(req, resp);
		}else {
			req.setAttribute("valoresIncorretos", true);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}

package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifgoiano.entidade.Usuario;
import br.edu.ifgoiano.repositorio.UsuarioRepositorio;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
//	@Override
//	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		final String email = req.getParameter("email");
//		final String password = req.getParameter("password");
//		
//		if (password != null && password.equals("123456")) {
//			try {
//				UsuarioRepositorio repositorio = new UsuarioRepositorio();
//				
//				List<Usuario> lstUsuario = repositorio.listarUsuarios();
//				
//				req.setAttribute("usuarios", lstUsuario);
//				
//				req.getRequestDispatcher("listaUsuarios.jsp").forward(req, resp);
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new ServletException("Erro na listagem de usu�rios");
//			}
//		}else {
//			req.setAttribute("valoresIncorretos", true);
//			req.getRequestDispatcher("index.jsp").forward(req, resp);
//		}
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UsuarioRepositorio repositorio = new UsuarioRepositorio();

			req.setAttribute("usuarios", repositorio.listarUsuarios());
			
			req.getRequestDispatcher("listaUsuarios.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro no servlet");
		}
		
	}
}

package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifgoiano.entidade.Usuario;
import br.edu.ifgoiano.repositorio.UsuarioRepositorio;

@WebServlet("/pesquisar")
public class PesquisaServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("pesquisaNome");
		String email = req.getParameter("pesquisaEmail");
		try {
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			req.setAttribute("usuarios", repositorio.searchByNameOrEmail(nome, email));
			req.getRequestDispatcher("listaUsuarios.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
}

package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoiano.repositorio.AtividadesRepositorio;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			
			AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
			Atividades atividades = new Atividades();
			atividades.setUsuario_id((Integer) session.getAttribute("usuarioId"));
			atividades.setAcao("exclusão");
			atividadeRepositorio.insertAtividades(atividades);
			
			session.invalidate();
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

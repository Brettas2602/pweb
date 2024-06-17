package br.edu.ifgoiano.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoiano.repositorio.AtividadesRepositorio;
import br.edu.ifgoiano.repositorio.UsuarioRepositorio;

@WebServlet("/excluir")
public class ExcluirUsuarioServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("excluir"));
		
		try {
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			repositorio.deleteUsuario(id);
			
			HttpSession session = req.getSession();
			
			AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
			Atividades atividades = new Atividades();
			atividades.setUsuario_id((Integer) session.getAttribute("usuarioId"));
			atividades.setAcao("exclusão");
			atividadeRepositorio.insertAtividades(atividades);
			
			if ((Integer) session.getAttribute("usuarioId") == id) {
				Atividades atividades2 = new Atividades();
				atividades2.setUsuario_id((Integer) session.getAttribute("usuarioId"));
				atividades2.setAcao("logout");
				atividadeRepositorio.insertAtividades(atividades2);
				session.invalidate();
				req.setAttribute("mensagem", "Você excluiu o seu próprio usuário!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}else {
				req.setAttribute("usuarios", repositorio.listUsuarios());
				resp.sendRedirect("listaUsuarios.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

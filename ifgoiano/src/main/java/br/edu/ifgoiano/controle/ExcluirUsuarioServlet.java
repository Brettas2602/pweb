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
			
			req.setAttribute("usuarios", repositorio.listUsuarios());
			req.getRequestDispatcher("listaUsuarios.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

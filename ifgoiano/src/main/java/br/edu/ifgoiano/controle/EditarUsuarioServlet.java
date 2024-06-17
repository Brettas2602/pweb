package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoiano.entidade.Usuario;
import br.edu.ifgoiano.repositorio.AtividadesRepositorio;
import br.edu.ifgoiano.repositorio.UsuarioRepositorio;

@WebServlet("/editar")
public class EditarUsuarioServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("editar"));
		
		try {
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			req.setAttribute("usuario", repositorio.getUsuarioById(id));
			req.getRequestDispatcher("editarUsuario.jsp").forward(req, resp);
		} catch (SQLException e) {
			System.out.println("Erro na consulta de usuário por ID");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(req.getParameter("id")));
		usuario.setNome(req.getParameter("nome"));
		usuario.setEmail(req.getParameter("email"));
		usuario.setSenha(req.getParameter("senha"));
		
		String senha2 = req.getParameter("senha2");
		
		try {
			if (usuario.getSenha().equals(senha2)) {
				UsuarioRepositorio repositorio = new UsuarioRepositorio();
				repositorio.updateUsuario(usuario);
				
				HttpSession session = req.getSession();
				
				AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
				Atividades atividades = new Atividades();
				atividades.setUsuario_id((Integer) session.getAttribute("usuarioId"));
				atividades.setAcao("atualização");
				atividadeRepositorio.insertAtividades(atividades);
				
				req.setAttribute("usuarios", repositorio.listUsuarios());
				req.getRequestDispatcher("listaUsuarios.jsp").forward(req, resp);
			}else {
				String mensagem = "As senhas informadas não são iguais!";
				
				req.setAttribute("mensagem", mensagem);
				req.setAttribute("usuario", usuario);
				req.getRequestDispatcher("editarUsuario.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("Erro na verificação de senhas");
			e.printStackTrace();
		}
		
	}
}

package br.edu.ifgoiano.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		try {
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			Usuario usuario = new Usuario();
			usuario = repositorio.getUsuarioByEmail(email);
			
			if (usuario.getEmail() != null && usuario.getEmail().equals(email) && usuario.getSenha() != null && usuario.getSenha().equals(senha) ) {
				req.setAttribute("usuarios", repositorio.listUsuarios());
				HttpSession session = req.getSession();
				
				AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
				Atividades atividades = new Atividades();
				atividades.setUsuario_id(repositorio.getUsuarioByEmail(req.getParameter("email")).getId());
				atividades.setAcao("login");
				atividadeRepositorio.insertAtividades(atividades);
				
				session.setAttribute("usuario", usuario);
				session.setAttribute("usuarioId", repositorio.getUsuarioByEmail(req.getParameter("email")).getId());

				req.getRequestDispatcher("listaUsuarios.jsp").forward(req, resp);
			}else {
				String mensagem = "Email ou senha incorretos!";
				
				req.setAttribute("mensagem", mensagem);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no login de usuário");
			e.printStackTrace();
		}
	}
}

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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import br.edu.ifgoiano.entidade.Atividades;
import br.edu.ifgoiano.entidade.Usuario;
import br.edu.ifgoiano.repositorio.AtividadesRepositorio;
import br.edu.ifgoiano.repositorio.UsuarioRepositorio;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet{
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Usuario usuario = new Usuario();
			usuario.setNome(req.getParameter("name"));
			usuario.setEmail(req.getParameter("email"));
			usuario.setSenha(req.getParameter("password"));

			try {
				UsuarioRepositorio repositorio = new UsuarioRepositorio();
				
				if (repositorio.getUsuarioByEmail(usuario.getEmail()).getEmail() != null) {
					String mensagem = "Já existe um usuário com esse email!";
					
					req.setAttribute("mensagem", mensagem);
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				}else {
					repositorio.insertUsuario(usuario);
					
					AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
					Atividades atividades = new Atividades();
					atividades.setUsuario_id(repositorio.getUsuarioByEmail(req.getParameter("email")).getId());
					atividades.setAcao("cadastro");
					atividadeRepositorio.insertAtividades(atividades);
					
					req.setAttribute("usuarios", repositorio.listUsuarios());
					HttpSession session = req.getSession();
					session.setAttribute("usuario", usuario);
					session.setAttribute("usuarioId", repositorio.getUsuarioByEmail(req.getParameter("email")).getId());
					
					resp.sendRedirect("listaUsuarios.jsp");
				}
				

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro na autenticação dos usuários");
			}
		}
}

package br.edu.ifgoiano.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifgoiano.entidade.Usuario;

public class UsuarioRepositorio {
	
	private Connection conn;

	public UsuarioRepositorio() throws SQLException{
		this.conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "sa");
	}
	
	public List<Usuario> listarUsuarios(){
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		
		String sql = "select id, nome, email, senha from usuario";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet resultSet = pst.executeQuery();
			
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				
				lstUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na consulta de usu�rios");
		}
		
		return lstUsuarios;
	}
	
	public void incluirUsuario(Usuario usuario) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into usuario (nome, email, senha) ");
		sql.append("values (?,?,?)");
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na inclusão de usuários");
		}
	}
}

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
		conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "sa");
	}
	
	public List<Usuario> listarUsuarios(){
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		
		String sql = "select id, nome, email, senha, data_nascimento from usuario";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet resultSet = pst.executeQuery();
			
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setDataNascimento(resultSet.getDate("data_nascimento"));
				
				lstUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na consulta de usuários");
		}
		
		return lstUsuarios;
	}
}

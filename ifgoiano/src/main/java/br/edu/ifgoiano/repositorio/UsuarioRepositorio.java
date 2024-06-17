package br.edu.ifgoiano.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.edu.ifgoiano.entidade.Usuario;

public class UsuarioRepositorio {
	
	private Connection conn;

	public UsuarioRepositorio() throws SQLException{
		this.conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "sa");
	}
	
	public List<Usuario> listUsuarios(){
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
	
	public void insertUsuario(Usuario usuario) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into usuario (nome, email, senha) ");
		sql.append("values (?,?,?)");
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.execute();
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na inclusão de usuários");
		}
	}
	
	public Usuario getUsuarioById(int id) {
		String sql = "select id, nome, email from usuario where id = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, id);
			
			ResultSet resultSet = pst.executeQuery();
			
			if (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				
				return usuario;
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta de usuários");
			e.printStackTrace();
		}
		
		throw new RuntimeException("Usuário não encontrado!");
	}
	
	public Usuario getUsuarioByEmail(String email) {
		String sql = "select id, nome, email, senha from usuario where email = ?";
		Usuario usuario = new Usuario();
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			
			ResultSet resultSet = pst.executeQuery();
			
			if (resultSet.next()) {
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(email);
				usuario.setSenha(resultSet.getString("senha"));
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta de usuários");
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public void updateUsuario(Usuario usuario) {
		String sql = "update usuario set nome = ?, email = ?, senha = ? where id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setInt(4, usuario.getId());
			pst.execute();
			
			conn.commit();
			
		} catch (Exception e) {
			System.out.println("Erro na alteração de usuários");
			e.printStackTrace();
		}
	}
	
	public void deleteUsuario(int id) {
		String sql = "delete from usuario where id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, id);
			pst.execute();
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("Erro na exclusão de usuário");
			e.printStackTrace();
		}
	}
	
	public List<Usuario> searchByNameOrEmail(String nome, String email) {
		List<Usuario> lstUsuarios = this.listUsuarios();
		List<Usuario> lstResultado = new ArrayList<Usuario>();
		
		for (Usuario usuario : lstUsuarios) {
			if (usuario.getNome().toLowerCase().contains(nome.toLowerCase()) && usuario.getEmail().contains(email)) {
				lstResultado.add(usuario);
			}
		}
		
		return lstResultado;
	}
}


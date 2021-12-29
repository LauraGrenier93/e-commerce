package fr.doranco.ecommerce.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.ecommerce.entity.User;
import fr.doranco.ecommerce.model.connection.ConnexionDB;
import fr.doranco.ecommerce.model.dao.IUserDao;

public class UserDao implements IUserDao {

	public UserDao() {}
	@Override
	public User getUserByEmail(String email) throws Exception  {
		Connection connection = null;
		PreparedStatement ps = null;
		User user = new User();
		try {
			String requete = "SELECT * FROM e_commerce.user WHERE email =?";
			connection = ConnexionDB.getConnection();
			ps = connection.prepareStatement(requete);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			user.setId(rs.getInt(1));
			user.setNom(rs.getString(2));
			user.setPrenom(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setPassword(rs.getString(5));
			}

		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
		return user;
	}
	@Override
	public List<User> getUsers() throws Exception {

		Connection connection = null;
		PreparedStatement ps = null;
		List<User> users = new ArrayList<User>();
		try {
			String requete = "SELECT * FROM e_commerce.user";
			connection = ConnexionDB.getConnection();
			ps = connection.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setNom(rs.getString(2));
				user.setPrenom(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));

				users.add(user);
			}

		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
		return users;
	}

	@Override
	public User addUser(User user) throws Exception {

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			String requete = "INSERT INTO user(nom, prenom, email, password, date_naissance, genre, niveau_service, telephone, fonction_actuelle, disponible,nb_experience, langages) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			connection = ConnexionDB.getConnection();
			ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getNom());
			ps.setString(2, user.getPrenom());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			
			ps.executeUpdate(); // ps.executeQuery() => pour uniquement lire les données.

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
		return user;
	}
}
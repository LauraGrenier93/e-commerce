package fr.doranco.ecommerce.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnexionDB {

	private ConnexionDB() {}

	public static final Connection getConnection() {

		String url = "jdbc:mysql://127.0.0.1:3306/nomDeLaBase";
		String user = "login";
		String password = "motDePasse";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
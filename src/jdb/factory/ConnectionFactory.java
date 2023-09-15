package jdb.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	/**
	 * VALORES PARA ACESSO DA DATABASE
	 */
	private String url = System.getenv("HOTEL-ALURA_URL");
	private String user = System.getenv("HOTEL-ALURA_USER");
	private String password = System.getenv("HOTEL-ALURA_PASSWORD");

	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);

		this.dataSource = comboPooledDataSource;
	}

	public Connection recoverConnection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException ex) {
			throw new RuntimeException("Conexão falhou! " + ex.getMessage());
		}
	}


}

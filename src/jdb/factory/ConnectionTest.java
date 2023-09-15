package jdb.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.recoverConnection();

		con.close();
		System.out.println("Conexão fechada!");
	}





}

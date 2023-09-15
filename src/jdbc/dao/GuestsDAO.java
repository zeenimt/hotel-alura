package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.model.Guest;

public class GuestsDAO {

	private Connection connection;

	public GuestsDAO(Connection connection) {
		this.connection = connection;
	}

	public void save(Guest guest) {
		try {
			String sql = "INSERT INTO guests "
					+ "(name, lastname, birthdate, nacionality, telephone, idreservation) "
					+ "values (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, guest.getName());
				pstm.setString(2, guest.getLastName());
				pstm.setDate(3, guest.getBirthdate());
				pstm.setString(4, guest.getNacionality());
				pstm.setString(5, guest.getTelephone());
				pstm.setInt(6, guest.getIdReservation());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						guest.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao salvar convidado! " + ex.getMessage());
		}
	}
}

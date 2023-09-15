package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.model.Guest;

public class GuestsDAO {

	private Connection connection;

	public GuestsDAO(Connection connection) {
		this.connection = connection;
	}

	public void save(Guest guest) {
		try {
			String sql = "INSERT INTO guests " + "(name, lastname, birthdate, nacionality, telephone, idreservation) "
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

	public List<Guest> listGuests() {
		List<Guest> guests = new ArrayList<>();
		try {

			String sql = "SELECT id, name, lastname, birthdate, nacionality, telephone, idreservation FROM guests";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			changeResultSetToGuest(guests, pstm);
		}
		return guests;
	} catch (SQLException ex) {
		throw new RuntimeException("Falha ao listar os hóspedes! " + ex.getMessage());
	}
}

	public List<Guest> findBydId(String id) {
		List<Guest> guests = new ArrayList<>();
		try {
			String sql = "SELECT id, name, lastname, birthdate, nacionality, telephone, idreservation FROM guests WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.executeUpdate();

				changeResultSetToGuest(guests, pstm);
			}
			return guests;
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao buscar por id! " + ex.getMessage());
		}

	}

	private void changeResultSetToGuest(List<Guest> reservations, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Guest guests = new Guest(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4),
						rst.getString(5), rst.getString(6), rst.getInt(7));
				reservations.add(guests);
			}
		}
	}
}

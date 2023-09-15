package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.model.Reservation;

public class ReservationsDAO {

	private Connection connection;

	public ReservationsDAO(Connection connection) {

		this.connection = connection;

	}

	public void save(Reservation reservation) {
		try {
			String sql = "INSERT INTO reservations "
					+ "(entryDate, exitDate, value, paymentMethod) "
					+ "VALUES (?, ?, ?, ?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setDate(1, reservation.getEntryDate());
				pstm.setDate(2, reservation.getExitDate());
				pstm.setDouble(3, reservation.getValue());
				pstm.setString(4, reservation.getPaymentMethod());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reservation.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao definir valores! " + ex.getMessage());
		}
	}

	public void delete(Integer id) {
		try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM reservations WHERE id = ?")) {
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao apagar a reserva! " + ex.getMessage());
		}
	}

}


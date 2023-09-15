package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				pstm.setString(3, reservation.getValue());
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
/**
 * Método de apagar reserva
 */
	public void delete(Integer id) {
		try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM reservations WHERE id = ?")) {
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao apagar a reserva! " + ex.getMessage());
		}
	}

	/**
	 * Método de buscar reserva
	 */
	public List<Reservation> find() {
		List<Reservation> reservations = new ArrayList<>();

		try {
			String sql = "SELECT id, entryDate, exitDate, value, paymentMethod FROM reservations";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				changeResultSetToReservation(reservations, pstm);
			}
			return reservations;
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao buscar reserva! " + ex.getMessage());
		}
	}

	/**
	 * Método de buscar reserva por ID
	 */
	public List<Reservation> findById(String id) {
		List<Reservation> reservations = new ArrayList<>();

		try {
			String sql = "SELECT id, entryDate, exitDate, value, paymentMethod FROM reservations WHERE id = ?";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				changeResultSetToReservation(reservations, pstm);
			}
			return reservations;
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao buscar por ID! " + ex.getMessage());
		}
	}

	/**
	 * Método de atualizar reserva por ID
	 */
	public void update(Date entryDate, Date exitDate, String value, String paymentMethod, Integer id) {
		try (PreparedStatement pstm = connection.prepareStatement
				("UPDATE reservations SET dataEntry = ?, dataExit = ?, value = ?, paymentMethod = ? WHERE id = ?")) {
			pstm.setDate(1, entryDate);
			pstm.setDate(2, exitDate);
			pstm.setString(3, value);
			pstm.setString(4, paymentMethod);
			pstm.setInt(5, id);

			pstm.executeUpdate();

		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao atualizar reserva! " + ex.getMessage());
		}
	}

	private void changeResultSetToReservation(List<Reservation> reservations, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reservation reservation = new Reservation
						(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));
				reservations.add(reservation);
			}
		}
	}

}


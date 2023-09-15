package jdbc.controller;

import java.sql.Connection;
import java.util.List;

import jdb.factory.ConnectionFactory;
import jdbc.dao.ReservationsDAO;
import jdbc.model.Reservation;

public class ReservationController {

	private ReservationsDAO reservationDao;

	public ReservationController() {
		Connection connection = new ConnectionFactory().recoverConnection();
		this.reservationDao = new ReservationsDAO(connection);
	}

	public void save(Reservation reservation) {
		this.reservationDao.save(reservation);
	}

	public List<Reservation> reservations() {
		return this.reservationDao.find();
	}
}

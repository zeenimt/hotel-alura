package jdbc.controller;

import java.sql.Connection;
import java.util.List;

import jdb.factory.ConnectionFactory;
import jdbc.dao.GuestsDAO;
import jdbc.model.Guest;

public class GuestsController {
	private GuestsDAO guestDao;

	public GuestsController() {
		Connection connection = new ConnectionFactory().recoverConnection();
		this.guestDao = new GuestsDAO(connection);
	}

	public void save(Guest guest) {
		this.guestDao.save(guest);
	}

	public List<Guest> listGuests() {
		return this.guestDao.listGuests();
	}

	public List<Guest> findById(String id) {
		return this.guestDao.findBydId(id);
	}

}

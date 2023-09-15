package jdbc.model;

import java.sql.Date;

public class Reservation {

	private Integer id;
	private Date entryDate;
	private Date exitDate;
	private String value;
	private String paymentMethod;
	// Substituir para enum com valores padrão de métodos de pagamento

	public Reservation(Date entryDate, Date exitDate, String value, String paymentMethod) {
		super();
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.value = value;
		this.paymentMethod = paymentMethod;
	}

	public Reservation(int id, Date entryDate, Date exitDate, String value, String paymentMethod) {
		this.id = id;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.value = value;
		this.paymentMethod = paymentMethod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idReservation) {
		this.id = idReservation;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}




}

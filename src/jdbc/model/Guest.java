package jdbc.model;

import java.sql.Date;

public class Guest {

	private Integer id;
	private String name;
	private String lastName;
	private Date birthdate;
	private String nacionality;
	private String telephone;
	private Integer idReservation;

	public Guest(String name, String lastName, Date birthdate, String nacionality, String telephone, Integer idReservation) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.nacionality = nacionality;
		this.telephone = telephone;
		this.idReservation = idReservation;
	}

	public Guest(Integer id, String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Integer idReInteger) {
		this.id = id;
		this.name = nome;
		this.lastName = sobrenome;
		this.birthdate = dataNascimento;
		this.nacionality = nacionalidade;
		this.telephone = telefone;
		this.idReservation = idReInteger;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

}

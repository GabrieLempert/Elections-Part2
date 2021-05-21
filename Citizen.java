package id_206215311_id_207497561;

import java.io.Serializable;

public class Citizen implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String id;
	protected int yearOfBirth;
	protected BallotBox<?> ballotBox;
	protected Party chosenParty;

	public Citizen(String name, String id, int yearOfBirth) {
		this.name = name;
		this.id = setId(id);
		this.yearOfBirth = yearOfBirth;
		this.ballotBox = null;
		this.chosenParty = null;
	}

	public String setId(String id) throws IllegalArgumentException {
		if (id.length() == 9) {
			return id;
		} else {
			throw new IllegalArgumentException(" ID must have 9 digits in it");
		}

	}

	public Citizen(Citizen copyCitizen) {
		this.name = copyCitizen.name;
		this.id = setId(copyCitizen.id);
		this.yearOfBirth = copyCitizen.yearOfBirth;
		this.ballotBox = copyCitizen.ballotBox;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Citizen) {
			Citizen temp = (Citizen) obj;
			return temp.id.equals(this.id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Citizen name is: " + name + "\nid :" + id + "\nhe was born in:  " + yearOfBirth + ", he votes in:\n"
				+ ballotBox;
	}

	public Party getChosenParty() {
		return chosenParty;
	}

	public void setChosenParty(Party chosenParty) {
		this.chosenParty = chosenParty;
	}

}

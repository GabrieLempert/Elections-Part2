package id_206215311_id_207497561;

import java.io.Serializable;

public class CitizenCorona extends Citizen implements Corona, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected int dayOfCovid;
	protected boolean isDressed;

	public CitizenCorona(String name, String id, int yearOfBirth, int dayOfCovid) {
		super(name, id, yearOfBirth);
		this.dayOfCovid = dayOfCovid;
	}

	public CitizenCorona(Citizen c) {
		super(c);

	}

	@Override
	public String toString() {
		return super.toString() + " \nHe is sick with corona - this number of days: " + dayOfCovid;
	}

}

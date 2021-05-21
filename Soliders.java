package id_206215311_id_207497561;

import java.io.Serializable;

public class Soliders extends Citizen implements CarryWeapon, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public Soliders(String name, String id, int yearOfBirth, Party party,
			boolean isVoted) {
		super(name, id, yearOfBirth);
	}


	public Soliders(Citizen copyCitizen) {
		super(copyCitizen);
	}
	@Override
	public String toString() {
		return super.toString() + "And he is a " + getClass().getSimpleName();

	}

}

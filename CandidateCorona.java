package id_206215311_id_207497561;

import java.io.Serializable;

public class CandidateCorona extends Candidates implements Corona, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected int dayOfCovid;

	public CandidateCorona(String name, String id, int yearOfBirth, Party party, int dayOfCovid) {
		super(name, id, yearOfBirth, party);
		this.dayOfCovid = dayOfCovid;
	}

	public CandidateCorona(Citizen copyCandidate) {
		super(copyCandidate);
	}

	@Override
	public String toString() {
		return super.toString() + " /nHe is sick with corona - this number of days: " + dayOfCovid;
	}

}

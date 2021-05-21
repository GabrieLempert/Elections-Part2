package id_206215311_id_207497561;

import java.io.Serializable;

public class Candidates extends Citizen implements Serializable, Comparable<Candidates> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Party partyHeIsRunningFor;
	private int hisPlaceInTheParty;

	public Candidates(String name, String id, int yearOfBirth, Party party) {
		super(name, id, yearOfBirth);
		this.setPartyHeIsRunningFor(party);
		setHisPlaceInTheParty(hisPlaceInTheParty);
	}

	@Override
	public void setChosenParty(Party chosenParty) {
		super.setChosenParty(chosenParty);
	}

	public Candidates(Citizen copyCandidate) {
		super(copyCandidate);

	}

	@Override
	public String toString() {
		if (this.partyHeIsRunningFor != null) {
			return super.toString() + "\nhis political opinion is:\n" + getPartyHeIsRunningFor().PoliticalOpinion
					+ "\nHis place in the party is:\n" + (getHisPlaceInTheParty() + 1);
		} else {
			return super.toString() + "\nHe is a Candidate";
		}
	}

	public Party getPartyHeIsRunningFor() {
		return partyHeIsRunningFor;
	}

	public void setPartyHeIsRunningFor(Party partyHeIsRunningFor) {
		this.partyHeIsRunningFor = partyHeIsRunningFor;
	}

	public int getHisPlaceInTheParty() {
		return hisPlaceInTheParty;
	}

//sets candidates place in the party and checks if there is another cadidate with the same place;
	public void setHisPlaceInTheParty(int hisPlaceInTheParty) {
		int hisPlace = (int) (Math.random() * 20);
		try {
			for (Candidates element : partyHeIsRunningFor.candidates) {
				if (element.hisPlaceInTheParty == hisPlace) {
					throw new IllegalArgumentException();
				}
			}
			this.hisPlaceInTheParty = hisPlace;
		} catch (IllegalArgumentException e) {
			hisPlace = (int) (Math.random() * 20);
		}
	}

	@Override
	public int compareTo(Candidates o) {
		return o.hisPlaceInTheParty - this.hisPlaceInTheParty;
	}


}

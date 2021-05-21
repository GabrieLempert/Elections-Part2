package id_206215311_id_207497561;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Party implements Comparable<Party>, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected enum PoliticalOpinion {
		RIGHT, LEFT, CENTER;
	}

	protected String name;
	protected PoliticalOpinion PoliticalOpinion;
	protected LocalDate dateOfEstablishment;
	protected ArrayList<Candidates> candidates;
	protected int numOfCandidates;
	protected int numOfVoters;

	public Party(String name, PoliticalOpinion politicalOpinion) {
		this.name = name;
		this.PoliticalOpinion = politicalOpinion;
		this.dateOfEstablishment = LocalDate.now();
		this.candidates = new ArrayList<Candidates>();
		this.numOfCandidates = 0;
		this.numOfVoters = 0;
	}

	public boolean addCandidate(Candidates newCandidate) {
		// contains-checks if there is already a candidate in the list;
		if (candidates.contains(newCandidate)) {
				return false;
			}
			// checks if the candidate has the same party as this party;
		if (equals(newCandidate.getPartyHeIsRunningFor())) {
			numOfCandidates++;
			candidates.add(newCandidate);
			PartySortingElections();
			return true;
		}
		return false;
	}


	public void PartySortingElections() {
		Collections.sort(candidates);
	}
	@Override
	public boolean equals(Object obj) {
		if (getClass().equals(obj.getClass())) {
			Party temp = (Party) obj;
			return temp.name == this.name;
		}
		return false;
	}

	@Override
	public String toString() {
		if (numOfVoters != 0) {
			StringBuffer sb = new StringBuffer(name + ": " + "\nThe political opinion of the party is: "
					+ PoliticalOpinion + "\nEstablished in:\n" + dateOfEstablishment + "\nNumber of candidates is:\n "
					+ numOfCandidates + "\nThe number of votes:\n" + numOfVoters);
			for (int i = 0; i < numOfCandidates; i++) {
				sb.append("\n" + candidates.get(i));

			}
			return sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(
					name + ": " + "\nThe political opinion of the party is: " + PoliticalOpinion + "\nEstablished in:\n"
							+ dateOfEstablishment + "\nNumber of candidates is:\n" + numOfCandidates);
			for (int i = 0; i < numOfCandidates; i++) {

				sb.append("\n" + candidates.get(i));

			}
			return sb.toString();
		}

	}

	@Override
	public int compareTo(Party p) {
		return p.numOfVoters - this.numOfVoters;
	}

}

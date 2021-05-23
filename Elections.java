package id_206215311_id_207497561;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Elections implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected int monthOfElection;
	protected int yearOfElection;
	protected Set<Citizen> voters;
	protected ArrayList<Party> parties;
	protected ArrayList<ArrayList<BallotBox>> ballotBoxes;
	private int citizenCounter;
	private int ballotBoxCounter;
	private int partyCounter;

	public Elections(int yearOfElection, int monthOfElection) {
		this.setMonthOfElection(monthOfElection);
		this.setYearOfElection(yearOfElection);
		this.voters = new Set<Citizen>();
		this.ballotBoxes = new ArrayList<ArrayList<BallotBox>>();
		this.ballotBoxes.add(new ArrayList<BallotBox>());
		this.ballotBoxes.add(new ArrayList<BallotBox>());
		this.ballotBoxes.add(new ArrayList<BallotBox>());
		this.ballotBoxes.add(new ArrayList<BallotBox>());
		this.parties = new ArrayList<Party>();
	}

//Citizen Methods
	public boolean checkAge(Citizen c) {
		if ((yearOfElection - c.yearOfBirth) < 18 || (yearOfElection - c.yearOfBirth) < 0) {
			System.out.println("You are not in the age to vote!");
			return false;
		}
		return true;
	}

	public <T extends Citizen> boolean addCitizens(T c) {
		if (checkAge(c) == true) {
			if (voters.contains(c)) {
				System.out.println("This citizen already exists");
				return false;
			}
		} else {
			System.out.println("Error!,you are not at the age to vote");
			return false;

		}
		if ((this.getYearOfElection() - c.yearOfBirth <= 21)) {
			if (c instanceof SoldiersCorona) {
				voters.add(c);
				System.out.println(
						"You added a citizen that is a soldier with corona to the elections to the voters list");
				citizenCounter++;
				return true;
			} else {
				voters.add(c);
				System.out.println("You added a citizen that is a soldier to the elections to the voters list");
				citizenCounter++;
				return true;
			}
		}

		if (c instanceof CandidateCorona) {
			voters.add(c);
			System.out
					.println("You added a citizen that is a candidate with corona to the elections to the voters list");
			citizenCounter++;
			return true;
		}
		if (c instanceof Candidates && !(c instanceof CandidateCorona)) {
			voters.add(c);
			System.out.println("You added a citizen that is a candidate to the elections to the voters list");
			citizenCounter++;
			return true;
		} else {
			if (c instanceof CitizenCorona) {
				voters.add(c);
				System.out.println("You added a citizen with corona  to the elections to the voters list");
				citizenCounter++;
				return true;
			}
			voters.add(c);
			System.out.println("You added a citizen  to the elections to the voters list");
			citizenCounter++;
			return true;
		}

	}

//addCandidate method;
	public boolean addCandidate(Candidates newCandidate, Party candidateParty) {
		if (checkAge(newCandidate) == true) {
			if (voters.contains(newCandidate)) {
				System.out.println("You can not create this candidate because there is a citizen with the same id");
				return false;
			}
			for (int i = 0; i < partyCounter; i++) {
				if (candidateParty.equals(parties.get(i))) {
					for (int j = 0; j < parties.get(i).numOfCandidates; j++) {
						if (parties.get(j).candidates.contains(newCandidate)) {
							System.out.println("You can not add a candidate to two parties");
							return false;
						}
					}
					parties.get(i).addCandidate(newCandidate);
					addCitizens(newCandidate);
					return true;
				}
			}
		}
		return false;
	}

//Party Methods:
	public boolean addParty(Party p) {
		if (parties.contains(p)) {
			System.out.println("You can't add a party with the same name");
			return false;
		}
		parties.add(p);
		partyCounter++;
		return true;
	}

//voters choose party
	public Party parrtySelector(int numberOfParty) {
		for (int i = 0; i < getPartyCounter(); i++) {
			if (parties.get(numberOfParty - 1).equals(parties.get(i))) {
				parties.get(numberOfParty - 1).numOfVoters = +1;
				return parties.get(numberOfParty - 1);
			}
		}
		return null;
	}

	public void results() {
		boolean checker;
		int counter = 0;
		for (int i = 0; i < citizenCounter; i++) {
			if (voters.get(i).getChosenParty() != null) {
				counter++;
			}
		}
		if (counter == citizenCounter) {
			checker = true;
		} else {
			checker = false;
		}

		if (checker == true) {
			System.out.println("The results are: ");
			for (int a = 0; a < 4; a++) {
				for (BallotBox element : ballotBoxes.get(a)) {
					System.out.println("This ballotbox results are: " + element);
					for (int j = 0; j < partyCounter; j++) {
						System.out.println(element.voteCounter(parties.get(j)) + " - " + parties.get(j).name);
					}
				}
			}
			Collections.sort(parties);
			System.out.println("The party that won is:\n" + parties.get(0));
		}
		if (checker == false) {
			System.out.println("Elections didn't start yet");
		}
	}

	public void showPartiesForElection() {
		for (int i = 0; i < partyCounter; i++) {
			System.out.println("Party number " + (i + 1) + " is: " + parties.get(i).name);
		}

	}

	public int getMonthOfElection() {
		return monthOfElection;
	}

	public int getYearOfElection() {
		return yearOfElection;
	}

	public int getCitizenCounter() {
		return citizenCounter;
	}

	public int getBallotBoxCounter() {
		return ballotBoxCounter;
	}

	public int getPartyCounter() {
		return partyCounter;
	}

	public void votersToEachBallotBox() {
		for (int i = 0; i < 4; i++) {
			for (BallotBox element : ballotBoxes.get(i)) {
				for (int j = 0; j < citizenCounter; j++) {
					if (element.equals(voters.get(j).ballotBox)) {
						element.addCitizen(voters.get(j));
					}
				}
			}
		}
	}

//BallotBox methods
	public void voteToBallotBox(int voter) {
		for (int i = 0; i < 4; i++) {
			for (BallotBox element : ballotBoxes.get(i)) {
				if (element.equals(voters.get(voter).ballotBox)) {
					element.votesCounter++;
				}
			}
		}
	}

	public <T extends Citizen> boolean addBallotBox(BallotBox<T> b, int choice) {
		for (int i = 0; i < 4; i++) {
			if (ballotBoxes.get(i).contains(b)) {
				System.out.println("there is a ballotbox in this address");
				return false;
			}
		}
		switch (choice) {
		case 1:
			System.out.println("You added a normal ballot box");
			ballotBoxes.get(0).add(b);
			ballotBoxCounter++;
			break;
		case 2: {
			System.out.println("You added a ballot box for Covid");
			ballotBoxes.get(1).add(b);
			ballotBoxCounter++;
			break;
		}
		case 3: {
			System.out.println("You added a ballot box for soldiers");
			ballotBoxes.get(2).add(b);
			ballotBoxCounter++;

			break;

		}
		case 4: {
			System.out.println("You added a ballot box for soldiers with covid");
			ballotBoxes.get(3).add(b);
			ballotBoxCounter++;

			break;

		}
		}
		return true;
	}

	public void addBallotBoxToCitizens(Citizen voter) {
		int index = 0;
		int size = voters.getCurrentSize();
		for (int i = 0; i < size; i++) {
			if (voters.get(i).equals(voter)) {
				index = i;
			}
		}
		if (voter instanceof Soliders && !(voter instanceof SoldiersCorona)) {
			while (voter.ballotBox == null) {
				int random = (int) (Math.random() * ballotBoxes.get(2).size());
				voters.get(index).ballotBox = ballotBoxes.get(2).get(random);
				voter.ballotBox = ballotBoxes.get(2).get(random);
			}
		}
		if (voter instanceof CandidateCorona || voter instanceof CitizenCorona) {
			while (voter.ballotBox == null) {
				int random = (int) (Math.random() * ballotBoxes.get(1).size());
				voters.get(index).ballotBox = ballotBoxes.get(1).get(random);
				voter.ballotBox = ballotBoxes.get(1).get(random);

			}
		}
		if (voter instanceof SoldiersCorona) {
			while (voter.ballotBox == null) {
				int random = (int) (Math.random() * ballotBoxes.get(3).size());
				voters.get(index).ballotBox = ballotBoxes.get(3).get(random);
				voter.ballotBox = ballotBoxes.get(3).get(random);
			}
		}

		else {
			while (voter.ballotBox == null) {
				int random = (int) (Math.random() * ballotBoxes.get(0).size());
				voters.get(index).ballotBox = ballotBoxes.get(0).get(random);
				voter.ballotBox = ballotBoxes.get(0).get(random);

			}
		}

	}

	public void addBallotBoxToThisElections() {
		for (int i = 0; i < citizenCounter; i++) {
			addBallotBoxToCitizens(voters.get(i));
		}
	}

//Show methods
	void showBallotBoxes() {
		for (int i = 0; i < ballotBoxCounter; i++) {
			System.out.println("ballot box number " + (i + 1) + " - " + ballotBoxes.get(i));
		}
	}

	public void showCitizens() {
		for (int i = 0; i < this.citizenCounter; i++) {
			System.out.println("Citizen number " + (i + 1) + " - " + voters.get(i));
		}

	}

	public void showParties() {
		for (int i = 0; i < partyCounter; i++) {
			System.out.println(parties.get(i));
		}

	}

//Elections Reset
	public void electionsCleaner() {
		for (int a = 0; a < 4; a++) {
			for (BallotBox<?> element : ballotBoxes.get(a)) {
				element.votesCounter = 0;
			}
		}
		for (int i = 0; i < partyCounter; i++) {
			parties.get(i).numOfVoters = 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass().equals(obj.getClass())) {
			Elections temp = (Elections) obj;
			return temp.monthOfElection == this.monthOfElection && temp.yearOfElection == this.yearOfElection;
		}
		return false;

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(
				"This elections coducted in: " + this.monthOfElection + "/" + this.yearOfElection);
		sb.append("\nThere were :" + citizenCounter + " Citizens");
		for (int i = 0; i < voters.getCurrentSize(); i++) {
			sb.append("\n" + voters.get(i));
		}
		sb.append("\nThere were: " + ballotBoxCounter + " Ballotboxes");
		for (int i = 0; i < 4; i++) {
			for (BallotBox element : ballotBoxes.get(i)) {
				sb.append("\n" + element);
			}
		}
		sb.append("\nThere were: " + partyCounter + " Parties");
		for (Party party : parties) {
			sb.append("\n" + party);
		}
		results();
		return sb.toString();
	}

	public void setMonthOfElection(int monthOfElection) {
		if (monthOfElection > 12 || monthOfElection < 1) {
			throw new IllegalArgumentException();
		} else {
			this.monthOfElection = monthOfElection;
		}
	}

	public void setYearOfElection(int yearOfElection) {
		this.yearOfElection = yearOfElection;
	}
}

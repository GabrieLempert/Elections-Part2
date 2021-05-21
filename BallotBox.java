package id_206215311_id_207497561;

import java.io.Serializable;
import java.util.ArrayList;

public class BallotBox<T extends Citizen> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected int serialNumber;
	protected static int serialCounter = 1000;
	protected String address;
	protected ArrayList<T> ballotBoxVoters;
	protected int numberOfVoters;
	protected int votesCounter;

	public BallotBox(BallotBox<T> b) {
		this.serialNumber = b.getSerialNumber();
		this.address = b.address;
		this.ballotBoxVoters = b.ballotBoxVoters;
		this.votesCounter = b.votesCounter;
		this.numberOfVoters = b.numberOfVoters;
	}

	public BallotBox(String address) {
		this.serialNumber = serialCounter++;
		this.ballotBoxVoters = new ArrayList<T>();
		this.address = address;
		this.votesCounter = 0;
		this.numberOfVoters = 0;
	}

	public boolean addCitizen(Citizen newCitizen) {
			if (ballotBoxVoters.contains(newCitizen)) {
				return false;
			}
		if (equals(newCitizen.ballotBox)) {
			ballotBoxVoters.add((T) newCitizen);
			numberOfVoters++;
			return true;
		} else {
			return false;
		}
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public int voteCounter(Party electionParty) {
		int counter = 0;
		for (int i = 0; i < numberOfVoters; i++) {
			if (ballotBoxVoters.get(i).getChosenParty().equals(electionParty)) {
				counter++;
			}
		}
		votesCounter = counter;
		System.out.println("This party got " + counter + " votes in this ballot");
		return counter;

	}

	public double votersPracentage() {
		double votersPracentage = votesCounter / numberOfVoters;
		return votersPracentage * 100;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BallotBox) {
			BallotBox<?> temp = (BallotBox<?>) obj;
			if (temp.serialNumber == this.serialNumber || temp.address.equals(address) == true) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		if (votesCounter != 0) {
			return "BallotBox serialNumber:\n" + serialNumber + "\nThe address is " + address
					+ "\nNumber of people that voted:\n " + votesCounter + "\nThe vote pracentage is:"
					+ votersPracentage();

		}
		return "BallotBox serialNumber:\n" + serialNumber + "\nThe address is " + address
				+ "\nNumber of voters in this ballot is:\n" + numberOfVoters;

	}

}

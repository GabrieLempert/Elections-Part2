package id_206215311_id_207497561;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import id_206215311_id_207497561.Party.PoliticalOpinion;

public class ElectionsSave implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<Elections> electionSaver;
	protected int numberOfElections;

	ElectionsSave() {
		this.electionSaver = new ArrayList<Elections>();
		this.numberOfElections = 0;
	}

	public boolean addElections(Elections newElections) {
		if (electionSaver.contains(newElections)) {
			return false;
		} else {
			electionSaver.add(newElections);
			numberOfElections++;
			return true;
		}
	}

	static Scanner sc = new Scanner(System.in);

	// addPartyCase
	public void addPartyCase(Elections currentElections) {
		int index = getElectionsIndex(currentElections);
		System.out.println("Your party name: ");
		String name = "";
		name += sc.nextLine();
		System.out.println("Choose your political opinion: ");
		System.out.println("1.RIGHT");
		System.out.println("2.CENTER");
		System.out.println("3.LEFT");
		PoliticalOpinion temp = null;
		int key = sc.nextInt();
		while (key > 3 || key < 1) {
			System.out.println("There is no such option Enter again between 1-3: ");
			key = sc.nextInt();
		}
		switch (key) {
		case 1:
			temp = PoliticalOpinion.RIGHT;
			break;
		case 2:
			temp = PoliticalOpinion.CENTER;
			break;
		case 3:
			temp = PoliticalOpinion.LEFT;
			break;

		default:
			System.out.println("There is no such option choose again: ");
			key = sc.nextInt();
			break;
		}
		Party newParty = new Party(name, temp);

		electionSaver.get(index).addParty(newParty);

	}

	// addBallotBoxCase
	public int Choice() {
		System.out.println("Choose which ballot you want to add: ");
		System.out.println("Number 1: Regular ballotbox");
		System.out.println("Number 2: Covid ballotbox");
		System.out.println("Number 3: Soldiers ballotbox");
		System.out.println("Number 4: Soldiers Covid ballotbox");
		System.out.println("Enter your choice must be between 1-3: ");
		int choice = Integer.parseInt(sc.nextLine());
		if (choice > 4 || choice < 1) {
			throw new IllegalArgumentException();
		}
		return choice;
	}

	public void chooseYesOrNo(String choose) throws IllegalArgumentException {
		if (!(choose.equalsIgnoreCase("no")) && !(choose.equalsIgnoreCase("yes"))) {
			System.out.println("Invalid!Enter again: ");
			throw new IllegalArgumentException("You were asked to put yes\no choose again");
		}

	}

	public int getElectionsIndex(Elections currentElections) {
		int index = 0;
		for (int i = 0; i < electionSaver.size(); i++) {
			if (currentElections.equals(electionSaver.get(i))) {
				index = i;
			}
		}
		return index;
	}

	public <T extends Citizen> void addBallotBoxCase(Elections currentElections) {
		boolean check = true;
		int index = getElectionsIndex(currentElections);
		System.out.println("Input address:");
		String address = "";
		address += sc.nextLine();
		int type = 0;
		do {
			try {
				type = Choice();
				check = false;
			} catch (IllegalArgumentException ex) {
				check = true;
			}
		} while (check);
		switch (type)

		{
		case 1: {

			BallotBox<Citizen> b = new BallotBox<Citizen>(address);
			electionSaver.get(index).addBallotBox(b, type);
			break;
		}
		case 2: {
			BallotBox<CitizenCorona> b = new BallotBox<CitizenCorona>(address);
			electionSaver.get(index).addBallotBox(b, type);
			break;
		}
		case 3: {
			BallotBox<Soliders> b = new BallotBox<Soliders>(address);
			electionSaver.get(index).addBallotBox(b, type);
			break;

		}
		case 4: {
			BallotBox<SoldiersCorona> b = new BallotBox<SoldiersCorona>(address);
			electionSaver.get(index).addBallotBox(b, type);
			break;

		}
		}
	}

	// addCitizenCase-2
	public void addCitizenCase(Elections currentElections) {// needs to throw exception if there is no ballotbox;
		boolean check = true;
		boolean isQuarineted = false;
		int index = getElectionsIndex(currentElections);
		System.out.println("Add your name: ");
		String name = "";
		name += sc.nextLine();
		System.out.println("Add your id: ");
		String id = "";
		id += sc.nextLine();
		checkId(id);
		System.out.println("Add you year of birth: ");
		int yearOfBirth = Integer.parseInt(sc.nextLine());
		System.out.println("Add if you are ill with corona: ");
		System.out.println("Choose Yes/No");
		String choose = "";
		choose += sc.nextLine();
		do {
			try {
				chooseYesOrNo(choose);
				check = false;
			} catch (IllegalArgumentException ex) {

				check = true;
			}
		} while (check == true);

		if (choose.equalsIgnoreCase("yes")) {
			isQuarineted = false;
		}
		if (choose.equalsIgnoreCase("yes")) {
			isQuarineted = true;
		}
		if (isQuarineted == true) {
			System.out.println("Enter the number of days you are sick: ");
			int dayOfCovid = sc.nextInt();
			if (electionSaver.get(index).getYearOfElection() - yearOfBirth <= 21) {
				SoldiersCorona newSoldiersCorona = null;
				do {
					try {
						newSoldiersCorona = new SoldiersCorona(name, id, yearOfBirth, dayOfCovid);
						check = false;
					} catch (IllegalArgumentException ex) {
						name = "";
						name += sc.nextLine();
						check = true;
					}
				} while (check);
				electionSaver.get(index).addCitizens(newSoldiersCorona);
				electionSaver.get(index).addBallotBoxToCitizens(newSoldiersCorona);
				electionSaver.get(index).votersToEachBallotBox();

			}
			CitizenCorona newCitizenCorona = null;
			do {
				try {
					newCitizenCorona = new CitizenCorona(name, id, yearOfBirth, dayOfCovid);
					check = false;
				} catch (IllegalArgumentException ex) {
					name = "";
					name += sc.nextLine();
					check = true;
				}
			} while (check);
			electionSaver.get(index).addCitizens(newCitizenCorona);
			electionSaver.get(index).addBallotBoxToCitizens(newCitizenCorona);
			electionSaver.get(index).votersToEachBallotBox();
		} else {
			Citizen newCitizen = null;
			do {
				try {
					newCitizen = new Citizen(name, id, yearOfBirth);
					check = false;
				} catch (IllegalArgumentException ex) {
					name = "";
					name += sc.nextLine();
					check = true;
				}
			} while (check);
			electionSaver.get(index).addCitizens(newCitizen);
			electionSaver.get(index).addBallotBoxToCitizens(newCitizen);
			electionSaver.get(index).votersToEachBallotBox();

		}
	}

	// idChecker(for user input);
	public static void checkId(String id) {
		try {
			if (id.isBlank() || id.isEmpty()) {
				throw new IllegalArgumentException("Can't be blank or empty");
			} else if (id.length() != 9) {
				throw new IllegalArgumentException("Needs 9 digits nor less nor more");
			}
		} catch (IllegalArgumentException e) {

			System.out.println("Enter id again: ");
			id = "";
			id += sc.nextLine();
			checkId(id);
		}

		try {

			for (int i = 0; i < id.length(); i++) {
				if (id.charAt(i) > 9 && id.charAt(i) < 0) {
					throw new IllegalArgumentException("id can have only digits");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Enter id again: ");
			id = "";
			id += sc.nextLine();
			checkId(id);
		}

	}

	public Elections ElectionsCreator() {
		Elections e1 = null;
		boolean check = true;
		do {
			try {
				System.out.println("Set Year: ");
				int electionYear = Integer.parseInt(sc.nextLine());
				System.out.println("Set Month: ");
				int electionMonth = Integer.parseInt(sc.nextLine());
				e1 = new Elections(electionYear, electionMonth);
				check = false;
			} catch (IllegalArgumentException ex) {
				check = true;
			}
		} while (check == true);
		return e1;
	}

	public void electionsConducter(Elections elections) {
		boolean check = true;
		int index = getElectionsIndex(elections);
		electionSaver.get(index).votersToEachBallotBox();
		electionSaver.get(index).electionsCleaner();
		for (int i = 0; i < electionSaver.get(index).getCitizenCounter(); i++) {
			System.out.println(electionSaver.get(index).voters.get(i).name + "- id -" + elections.voters.get(i).id);
			System.out.println("Do you want to vote?");
			System.out.println("Choose Yes/No");
			String temp = "";
			temp += sc.nextLine();
			do {
				try {
					chooseYesOrNo(temp);
					check = false;
				} catch (IllegalArgumentException ex) {

					check = true;
				}
			} while (check);
			if (temp.equalsIgnoreCase("no")) {
				Party blankParty = new Party("Blank", PoliticalOpinion.CENTER);
				electionSaver.get(index).voters.get(i).setChosenParty(blankParty);
				System.out.println("Thank you goodbye!");

			}
			if (temp.equalsIgnoreCase("yes")) {
				electionSaver.get(index).showPartiesForElection();
				electionSaver.get(index).voteToBallotBox(i);
				int temp1 = Integer.parseInt(sc.nextLine());
				while (temp1 > electionSaver.get(index).getPartyCounter() || temp1 < 0) {
					System.out.println("There is no such option choose again: ");
					temp1 = Integer.parseInt(sc.nextLine());
				}
				elections.voters.get(i).setChosenParty(electionSaver.get(index).parrtySelector(temp1));
				System.out.println("You chose: " + electionSaver.get(index).voters.get(i).getChosenParty().name);
				System.out.println("Thank you for your vote!");

			}
		}

	}

	// BallotBoxes
	BallotBox<Citizen> b1 = new BallotBox<Citizen>("Kings Landing");
	BallotBox<CitizenCorona> b2 = new BallotBox<CitizenCorona>("Sesame Street");
	BallotBox<Soliders> b3 = new BallotBox<Soliders>("ella Street");
	BallotBox<SoldiersCorona> b4 = new BallotBox<SoldiersCorona>("gabi Street");

	// HardCoded parties
	Party p1 = new Party("Banana", PoliticalOpinion.RIGHT);
	Party p2 = new Party("Apple", PoliticalOpinion.LEFT);
	Party p3 = new Party("Ananans", PoliticalOpinion.CENTER);

	// Candidates

	Candidates c1 = new Candidates("Ganz", "000000009", 1981, p1);
	Candidates c2 = new Candidates("Yehuda Levi", "000000001", 1984, p1);
	Candidates c3 = new Candidates("Gabi", "000000002", 1981, p2);
	Candidates c4 = new Candidates("Shiran", "000000003", 1995, p2);
	Candidates c5 = new Candidates("Yael", "000000004", 1983, p3);
	Candidates c6 = new Candidates("Elkoubi", "000000005", 1987, p3);
	// Citizens
	Citizen h1 = new Citizen("Bar", "123456789", 1990);
	Citizen h2 = new Citizen("Galya", "123456788", 1990);
	Citizen h3 = new Citizen("Batman", "123456787", 1990);
	Citizen h4 = new Citizen("Superman", "123456786", 1990);
	Citizen h5 = new Citizen("BugsBunny", "123456785", 1990);

	// HardCodded addition to elections;
	void addHardCoded() {
		for (int i = 0; i < numberOfElections; i++) {
			electionSaver.get(i).addParty(p3);
			electionSaver.get(i).addParty(p2);
			electionSaver.get(i).addParty(p1);
			electionSaver.get(i).addBallotBox(b1, 1);
			electionSaver.get(i).addBallotBox(b2, 2);
			electionSaver.get(i).addBallotBox(b3, 3);
			electionSaver.get(i).addBallotBox(b4, 4);
			electionSaver.get(i).addCitizens(h1);
			electionSaver.get(i).addCitizens(h2);
			electionSaver.get(i).addCitizens(h3);
			electionSaver.get(i).addCitizens(h4);
			electionSaver.get(i).addCitizens(h5);
			electionSaver.get(i).addCandidate(c1, c1.getPartyHeIsRunningFor());
			electionSaver.get(i).addCandidate(c2, c2.getPartyHeIsRunningFor());
			electionSaver.get(i).addCandidate(c3, c3.getPartyHeIsRunningFor());
			electionSaver.get(i).addCandidate(c4, c4.getPartyHeIsRunningFor());
			electionSaver.get(i).addCandidate(c5, c5.getPartyHeIsRunningFor());
			electionSaver.get(i).addCandidate(c6, c6.getPartyHeIsRunningFor());
			electionSaver.get(i).addBallotBoxToThisElections();
			electionSaver.get(i).votersToEachBallotBox();
		}
	}

	public void addCandidateCase(Elections currentElections) {
		boolean check = true;
		boolean isQuarineted = false;
		int index = getElectionsIndex(currentElections);
		System.out.println("Add your name: ");
		String name = sc.nextLine();
		System.out.println("Add your id: ");
		String id = sc.next();
		checkId(id);
		sc.nextLine();
		System.out.println("Add your year of birth: ");
		int yearOfBirth = sc.nextInt();
		electionSaver.get(index).showPartiesForElection();
		System.out.println("Choose party number: ");
		int candidatePartyNumber = sc.nextInt();
		System.out.println("Add if you are ill with corona: ");
		System.out.println("Choose Yes/No");
		String choose = "";
		choose += sc.nextLine();
		do {
			try {
				chooseYesOrNo(choose);
				check = false;
			} catch (IllegalArgumentException ex) {
				check = true;
			}
		} while (check == true);
		if (choose.equalsIgnoreCase("yes")) {
			isQuarineted = false;
		}
		if (choose.equalsIgnoreCase("yes")) {
			isQuarineted = true;
		}
		if (isQuarineted == true) {
			System.out.println("Enter the number of days you are sick: ");
			int dayOfCovid = sc.nextInt();
			CandidateCorona newCandidateCorona = new CandidateCorona(name, id, yearOfBirth,

					electionSaver.get(index).parties.get(candidatePartyNumber - 1), dayOfCovid);
			electionSaver.get(index).parties.get(candidatePartyNumber - 1).addCandidate(newCandidateCorona);
			electionSaver.get(index).addCitizens(newCandidateCorona);
			electionSaver.get(index).addBallotBoxToCitizens(newCandidateCorona);
		} else {
			Candidates newCandidate = new Candidates(name, id, yearOfBirth,
					electionSaver.get(index).parties.get(candidatePartyNumber - 1));
			electionSaver.get(index).parties.get(candidatePartyNumber - 1).addCandidate(newCandidate);
			electionSaver.get(index).addCitizens(newCandidate);
			electionSaver.get(index).addBallotBoxToCitizens(newCandidate);
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Number of elections that you saved: " + numberOfElections);
		for (Elections element : electionSaver) {
			sb.append("\n" + element);
		}

		return sb.toString();
	}
}

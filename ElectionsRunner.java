package id_206215311_id_207497561;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class ElectionsRunner implements Serializable, ElectionsUI {

	private static final String F_NAME = "elections.txt";
	private static Scanner sc = new Scanner(System.in);

	public static void printMenu() {
		System.out.println("Choose between the options: ");
		System.out.println("1.add a ballot box");
		System.out.println("2.add a citizen");
		System.out.println("3.add a party");
		System.out.println("4.add a candidate to a party");
		System.out.println("5.show all the ballotboxes");
		System.out.println("6.show all the citizens");
		System.out.println("7.show all the parites");
		System.out.println("8.start the elections");
		System.out.println("9.show election results");
		System.out.println("10.exit the programm and save");
		System.out.println("11.start new elections");
		System.out.println("12.If you wanna read from existing file");
		System.out.println("Enter your choice: ");

	}

	private static ElectionsSave e = new ElectionsSave();
	private static Elections e1;

	public static Elections setE1() {
		e1 = e.ElectionsCreator();
		return e1;
	}

	@Override
	public void addBallotBox() {
		e.addBallotBoxCase(e1);
	}

	@Override
	public void addCitizen() {
		e.addCitizenCase(e1);
	}

	@Override
	public void addParty() {
		e.addPartyCase(e1);
	}

	@Override
	public void addCandidate() {
		e.addCandidateCase(e1);
	}

	@Override
	public void showBallotBox() {
		e.electionSaver.get(e.getElectionsIndex(e1)).showBallotBoxes();

	}

	@Override
	public void showCitizen() {
		e.electionSaver.get(e.getElectionsIndex(e1)).showCitizens();
	}

	@Override
	public void showParties() {
		e.electionSaver.get(e.getElectionsIndex(e1)).showParties();

	}

	@Override
	public void startElections() {
		e.electionsConducter(e.electionSaver.get(e.getElectionsIndex(e1)));
	}

	@Override
	public void showResults() {
		e.electionSaver.get(e.getElectionsIndex(e1)).results();
	}

	@Override
	public void RestartElections() {
		e1 = e.ElectionsCreator();
		runProgramm();
	}

	@Override
	public void exitProgrammAndSaveInFile() {
		System.out.println("GoodBye, and thank you see in the next election");
		sc.close();
		System.out.println("The information has been saved in the file");
		try {
			saveToFile(e);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void readFromExistingFile() {
		try {
			e = readFromFile();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		System.out.println(e);

	}

	@Override
	public void runProgramm() {
		e.addElections(e1);
		e.addHardCoded();

	}

	private static final long serialVersionUID = 1L;

	public static void saveToFile(ElectionsSave a) throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(F_NAME));
		o.writeObject(a);
		o.close();
	}

	public static ElectionsSave readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(F_NAME));
		ElectionsSave a = (ElectionsSave) i.readObject();
		i.close();
		return a;
	}

	public static void main(String[] args) {
		boolean check = true;
		String choose = null;

		ElectionsRunner runner = new ElectionsRunner();
		System.out.println("Do you want to read from an existing file? ");
		do {
			try {
				choose = "";
				choose += sc.nextLine();
				e.chooseYesOrNo(choose);
				check = false;
			} catch (IllegalArgumentException ex) {
				check = true;
			}

		} while (check);
		if (choose.equalsIgnoreCase("yes")) {
			runner.readFromExistingFile();
		}
		setE1();
		runner.runProgramm();

		int choice;
		do {
			printMenu();
			switch (choice = sc.nextInt()) {
			case 1: {
				System.out.println("You chose to add a ballotbox");
				runner.addBallotBox();
				break;
			}
			case 2: {
				System.out.println("You chose to add a citizen");
				runner.addCitizen();
				break;

			}
			case 3: {
				System.out.println("You chose to add a party");
				runner.addParty();
				System.out.println("You added a new party");
			}
				break;
			case 4: {
				System.out.println("You chose to add a candidate");
				runner.addCandidate();
			}
				break;
			case 5: {
				runner.showBallotBox();
			}
				break;

			case 6: {
				runner.showCitizen();
				break;
			}
			case 7: {
				runner.showParties();
			}
				break;

			case 8: {
				runner.startElections();
				break;
			}
			case 9: {
				runner.showResults();
				break;

			}
			case 10: {
				runner.exitProgrammAndSaveInFile();
				break;

			}
			case 11: {
				runner.RestartElections();
				break;
			}
			case 12: {
				runner.readFromExistingFile();
			}
				break;

			default: {
				System.out.println("Error!invalid option, Choose again");
				break;
			}
			}

		} while (choice != 10);

	}

	@Override
	public void createElections() {
		// TODO Auto-generated method stub

	}

}

package id_206215311_id_207497561;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class ElectionsRunner implements Serializable {

	/**
	 *
	 */
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

	/**
	 *
	 */
	private static final String F_NAME = "elections.txt";
	static Scanner sc = new Scanner(System.in);

	public static Elections electionCreator() {
		System.out.println("Add the election year: ");
		int electionYear = sc.nextInt();
		while (electionYear < 2021) {
			System.out.println("You can only add years that start from 2021 and on");
			electionYear = sc.nextInt();
		}
		System.out.println("Add the election month: ");
		int electionMonth = sc.nextInt();
		while (electionMonth > 12 || electionMonth < 1) {
			System.out.println("There are only 12 months in a year choose again");
			electionMonth = sc.nextInt();
		}
		Elections e1 = new Elections(electionYear, electionMonth);
		return e1;
	}

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

	public static void main(String[] args) {
		ElectionsSave e = new ElectionsSave();

			// Elections
			Elections e1 = electionCreator();
			e.addElections(e1);
			e.addHardCoded();

			int choice;
			do {
				printMenu();
				switch (choice = sc.nextInt()) {
				case 1: {
					System.out.println("You chose to add a ballotbox");
					e.addBallotBoxCase(e1);
					break;
				}
				case 2: {
					System.out.println("You chose to add a citizen");
					e.addCitizenCase(e1);
					break;

				}
				case 3: {
					System.out.println("You chose to add a party");
					e.addPartyCase(e1);
					System.out.println("You added a new party");

				}
					break;

				case 4: {
					System.out.println("You chose to add a candidate");
					e.addCandidateCase(e1);
				}
					break;
				case 5: {
					e.electionSaver.get(e.getElectionsIndex(e1)).showBallotBoxes();
				}
					break;

				case 6: {
					e.electionSaver.get(e.getElectionsIndex(e1)).showCitizens();

					break;
				}
				case 7: {
					e.electionSaver.get(e.getElectionsIndex(e1)).showParties();
				}
					break;

				case 8: {
					e.electionsConducter(e.electionSaver.get(e.getElectionsIndex(e1)));

					break;
				}
				case 9: {
					e.electionSaver.get(e.getElectionsIndex(e1)).results();
					break;

				}
				case 10: {
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
					break;

				}
				case 11: {
					electionCreator();
					e.addElections(e1);
					e.addHardCoded();
					break;
				}
				case 12: {
					try {
						e = readFromFile();
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
					System.out.println(e);
					break;

				default: {
					System.out.println("Error!invalid option, Choose again");
					break;
				}
				}

			} while (choice != 10);



	}

}

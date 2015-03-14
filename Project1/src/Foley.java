import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author John Foley
 */
public class Foley {

	public static void main(String[] args) {

		String fileName = "";

		if (args.length == 0) {
			System.out.println("Please give a file argument!");
			System.exit(0);
		}

		fileName = args[0];

		ArrayList<String[]> toDoList = storeTargetFile(fileName);

		if (toDoList.size() == 0) {
			System.out.println("Cannot continue. Nothing in the To Do list.");
			System.exit(0);
		}

		for (String[] processInfo: toDoList) {
			System.out.println(processInfo[0] + " " + processInfo[1]);
		}
	}

	/**
	 * Reads the target file and constructs a list of processes to schedule
	 * @param fileName String filename to open and read
	 * @return Arraylist of string arrays
	 */
	private static ArrayList<String[]> storeTargetFile(String fileName) {

		ArrayList<String[]> toDoList = new ArrayList<String[]>();

		try {

			Scanner reader = new Scanner(new File(fileName));
			String nextLine;
			int lineNumber = 0;

			while (reader.hasNext()) {

				nextLine = reader.nextLine();

				// Split the line according to whitespace
				String[] processInfo = nextLine.split("\\s+");

				// Sanitize
				if (processInfo.length > 2 && processInfo.length == 0) {

					System.out.println("Improperly formatted line on line " + Integer.toString(lineNumber));
					System.exit(0);
				}

				toDoList.add(processInfo);

				lineNumber++;
			}
		} catch (FileNotFoundException e) {

			System.out.println("Cannot open target file!");
			System.exit(0);
			e.printStackTrace();
		}

		return toDoList;
	}
}

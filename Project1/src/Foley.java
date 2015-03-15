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

		ArrayList<SchedulableProcess> toDoList = storeTargetFile(fileName);

		if (toDoList.size() == 0) {
			System.out.println("Cannot continue. Nothing in the To Do list.");
			System.exit(0);
		}

		PrettyOutputPrinter printer = new PrettyOutputPrinter();

		FCFS fcfs = new FCFS(toDoList);

		printer.print("FCFS", toDoList);

		printer.print("SRTF", toDoList);
		printer.print("RR (Q = 1)", toDoList);
	}

	/**
	 * Reads the target file and constructs a list of processes to schedule
	 * @param fileName String filename to open and read
	 * @return Arraylist of string arrays
	 */
	private static ArrayList<SchedulableProcess> storeTargetFile(String fileName) {

		ArrayList<SchedulableProcess> toDoList = new ArrayList<SchedulableProcess>();

		try {

			Scanner reader = new Scanner(new File(fileName));
			String nextLine;
			int lineNumber = 0;
			int processNumber = 0;

			while (reader.hasNext()) {

				nextLine = reader.nextLine();

				// Next line
				lineNumber++;

				// Split the line according to whitespace
				String[] processInfo = nextLine.split("\\s+");

				// Sanitize
				if (processInfo.length != 2) {

					System.out.println("Improperly formatted line on line " + Integer.toString(lineNumber));
					System.exit(0);
				}

				// Create and add the new process
				SchedulableProcess process =
						new SchedulableProcess(processNumber, Integer.parseInt(processInfo[0]),
								Integer.parseInt(processInfo[1]));

				toDoList.add(process);

				processNumber++;
			}
		} catch (FileNotFoundException e) {

			System.out.println("Cannot open target file!");
			System.exit(0);
			e.printStackTrace();
		}

		return toDoList;
	}
}

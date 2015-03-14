import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author John Foley
 *
 * Pretty prints the results from the schedulers
 */
public class PrettyOutputPrinter {

	/**
	 * Prints the scheduled process information in a pretty table
	 * @param algorithm Specified algorithm used on the process list
	 * @param processes ArrayList of processes that were scheduled and ran
	 */
	public void print(String algorithm, ArrayList<SchedulableProcess> processes) {

		System.out.println("Algorithm: *** " + algorithm + " ***");

		System.out.format("%7s%9s%10s%9s%19s%15s", "Process", "Arrival", "Departure",
				"On CPU", "Turnaround Time", "Waiting Time");

		for (SchedulableProcess process: processes) {

			System.out.format("%7d%9d%10d%9d%19d%15d", process.getProcessId(), process.getArrival(),
					process.getDeparture(), process.getExecTime(), process.getTurnaroundTime(),
					process.getWaitingTime());
		}
	}

	private void getAverageWaitingTime(ArrayList<SchedulableProcess> processes) {


	}
}

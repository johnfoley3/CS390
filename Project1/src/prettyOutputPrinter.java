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

		System.out.format("%7s%9s%10s%9s%19s%15s%n", "Process", "Arrival", "Departure",
				"On CPU", "Turnaround Time", "Waiting Time");

		for (SchedulableProcess process: processes) {

			System.out.format("%7d%9d%10d%9d%19d%15d%n", process.getProcessId(), process.getArrival(),
					process.getDeparture(), process.getExecTime(), process.getTurnaroundTime(),
					process.getWaitingTime());
		}

		System.out.println();
		System.out.format("Average waiting time = %f%n", getAverageWaitingTime(processes));
		System.out.format("Average turnaround time = %f%n", getAverageTurnaroundTime(processes));
		System.out.println();
		System.out.println();
	}

	/**
	 * Calculates the average waiting time of the ran processes.
	 * @param processes ArrayList containing the ran processes
	 * @return Returns double average waiting time
	 */
	private static double getAverageWaitingTime(ArrayList<SchedulableProcess> processes) {

		double sumWaitingTimes = 0;
		double numProcesses = (double)processes.size();

		// loop over processes and sum waiting times
		for (SchedulableProcess process: processes) {

			sumWaitingTimes += process.getWaitingTime();
		}

		// return average
		return sumWaitingTimes/numProcesses;
	}

	/**
	 * Calculates the average turn around time for the scheduled processes
	 * @param processes ArrayList containing the scheduled processes
	 * @return Average turnaround time
	 */
	private static double getAverageTurnaroundTime(ArrayList<SchedulableProcess> processes) {

		double sumTurnaroundTimes = 0;
		double numProcesses = (double)processes.size();

		// loop over processes and sum turnaround times
		for (SchedulableProcess process: processes) {

			sumTurnaroundTimes += process.getTurnaroundTime();
		}

		// return average
		return sumTurnaroundTimes/numProcesses;
	}
}

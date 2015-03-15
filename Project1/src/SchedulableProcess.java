/**
 * @author John Foley
 *
 * Represents a process entity
 */
public class SchedulableProcess {

	private int processId;
	private int arrival;
	private int departure;
	private int execTime;

	/**
	 * Constructor
	 * @param processId ID of the process
	 * @param arrival Arrival time for process
	 */
	public SchedulableProcess(int processId, int arrival, int execTime) {

		this.processId = processId;
		this.arrival = arrival;
		this.departure = 0;
		this.execTime = execTime;
	}

	/**
	 * Returns the process number's ID
	 * @return ID of the process
	 */
	public int getProcessId() {
		return processId;
	}

	/**
	 * Returns the process' arrival time
	 * @return Arrival time for the process
	 */
	public int getArrival() {
		return arrival;
	}

	/**
	 * Returns the turnaround time
	 * @return int turnaround time
	 */
	public int getTurnaroundTime() {
		return departure - arrival;
	}

	/**
	 * Returns the waiting time
	 * @return int waiting time
	 */
	public int getWaitingTime() {
		return departure - arrival - execTime;
	}

	/**
	 * Returns the departure time
	 * @return int departure time
	 */
	public int getDeparture() {
		return departure;
	}

	/**
	 * Returns the execution time
	 * @return int execution time
	 */
	public int getExecTime() {
		return execTime;
	}

	/**
	 * Sets the departure time for the process
	 * @param departure int time the process finished executing
	 */
	public void setDeparture(int departure) {
		this.departure = departure;
	}
}

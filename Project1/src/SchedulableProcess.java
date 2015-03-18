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
    private int timeExecuted;

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
        this.timeExecuted = 0;
	}

    /**
     * Creates and returns a reference to a new SchedulableProcess object
     * @return A brand new SchedulableProcess object
     */
    public SchedulableProcess deepCopy() {

        return new SchedulableProcess(processId, arrival, execTime);
    }

    /**
     * Returns the int time executed for this process
     * @return int time executed for process
     */
    public int getTimeExecuted() {
        return timeExecuted;
    }

    /**
     * Increments the time executed by n. N is arbitrary so that any time quantum can be used.
     * @param n time quantum to increment by
     */
    public void incrementTimeExecuted(int n) {
        timeExecuted += n;
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

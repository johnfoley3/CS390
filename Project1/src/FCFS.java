import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author John Foley
 *
 * Short for: First Come, First Serve
 */
public class FCFS {

	private ArrayList<SchedulableProcess> processes;
	private ArrayList<SchedulableProcess> readyQueue;
	private int time;

	/**
	 * Constructs a FCFS object and runs scheduler
	 * @param processes ArrayList of schedulable processes to iterate over
	 */
	public FCFS(ArrayList<SchedulableProcess> processes) {

		this.processes = new ArrayList<SchedulableProcess>(processes);
		this.time = 0;
		this.readyQueue = new ArrayList<SchedulableProcess>();

		runScheduler();
	}

	/**
	 * Runs the scheduler using the First Come, First Serve algorithm.
	 */
	private void runScheduler() {

		boolean done = false;
		int currentExecutionDuration = 0;
		boolean idle = false;

		// Create the initial list
		addToReadyQueue();

		// Get the first one and remove it from the list
		SchedulableProcess currentProcess = readyQueue.get(0);
		readyQueue.remove(0);

		// increment first time step
		currentExecutionDuration++;
		time++;

		while (!done) {

			// Check to see if more processes are ready to be added to the queue
			addToReadyQueue();

			// test to see if current process is finished
			if (currentExecutionDuration == currentProcess.getExecTime()) {

				currentProcess.setDeparture(time);

				// Check if it's the last process on the list
				if (processes.isEmpty() && readyQueue.isEmpty()) {

					done = true;
				}
				if (! readyQueue.isEmpty()) {

					currentProcess = readyQueue.get(0);
					readyQueue.remove(0);

					currentExecutionDuration = 1;
				}
			} else {

				// increment the execution duration
				// needs to be here so that an "idle" state doesn't inflate this time
				currentExecutionDuration++;
			}

			// increment time steps
			time++;
		}
	}

	/**
	 * Adds the next process to arrive (or more) to the ready queue
	 */
	private void addToReadyQueue() {

		for (Iterator<SchedulableProcess> iterator = processes.iterator(); iterator.hasNext(); ) {

			SchedulableProcess process = iterator.next();

			if (process.getArrival() == time) {

				readyQueue.add(process);
				iterator.remove();
			}
		}
	}
}

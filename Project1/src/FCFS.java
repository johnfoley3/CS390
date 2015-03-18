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
     * Constant int representing the time quantum. For this project, it is specified to be 1.
     */
    private final int Q;

	/**
	 * Constructs a FCFS object and runs scheduler
	 * @param processes ArrayList of schedulable processes to iterate over
	 */
	public FCFS(ArrayList<SchedulableProcess> processes) {

		this.processes = new ArrayList<SchedulableProcess>(processes);
		this.time = 0;
		this.readyQueue = new ArrayList<SchedulableProcess>();

        Q = 1;

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
		time += Q;

		while (!done) {

			// Check to see if more processes are ready to be added to the queue
			addToReadyQueue();

            // Check to see if we're idling
            if (idle) {

                // We are! Check to see if there is anything that needs to be worked on
                if (readyQueue.isEmpty()) {

                    // Nope. Undo the last execution counter
                    currentExecutionDuration--;
                } else {

                    // Woohoo! Some work!
                    currentProcess = readyQueue.get(0);
                    readyQueue.remove(0);
                    idle = false;
                }
            // We are not idling. Check to see if we're finished with current job
            } else if (currentExecutionDuration == currentProcess.getExecTime()) {

                // We are. Chalk up the time and reset the counter
                currentProcess.setDeparture(time);
                currentExecutionDuration = 0;

                // Check if it's the last process on the list
                if (processes.isEmpty() && readyQueue.isEmpty()) {

                    done = true;
                }

                if (readyQueue.isEmpty()) {

                    // Welp. We're idling.
                    currentExecutionDuration--;
                    idle = true;
                } else {

                    currentProcess = readyQueue.get(0);
                    readyQueue.remove(0);
                }
            }

			// increment time steps
            currentExecutionDuration++;
			time += Q;
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

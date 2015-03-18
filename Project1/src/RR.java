import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author John Foley
 *
 * Short for: Round Robin
 */
public class RR {

    private ArrayList<SchedulableProcess> processes;
    private ArrayList<SchedulableProcess> readyQueue;
    private int time;

    /**
     * Constant int representing the time quantum. For this project, it is specified to be 1.
     */
    private final int Q;

	public RR(final ArrayList<SchedulableProcess> processes) {

        this.processes = new ArrayList<SchedulableProcess>(processes);
        this.time = 0;
        this.readyQueue = new ArrayList<SchedulableProcess>();

        Q = 1;

        runScheduler();
	}

    /**
     * Runs the scheduler using the Round Robin algorithm
     *
     * Please note! The ready queue in this case is taking on more responsibility than it's name implies. It is not just
     *  a lazy queue that passively holds processes ready to run, but rather there is an implicit juggling of processes
     *  between execution and ready states.
     */
    private void runScheduler() {

        boolean done = false;

        while (!done) {

            // Check to see if more processes are ready to be added to the queue
            addToReadyQueue();

            // Does the ready queue have jobs to schedule
            if (!readyQueue.isEmpty()) {

                for (int i = 0; i < readyQueue.size(); i++) {

                    SchedulableProcess process = readyQueue.get(i);

                    process.incrementTimeExecuted( Q );

                    time += Q;

                    // If the process is finished, then remove it from the ready queue
                    if (process.getTimeExecuted() == process.getExecTime()) {

                        readyQueue.remove(i);
                        process.setDeparture(time);
	                    i--;
                    }

	                // Check to see if more processes are ready to be added to the queue
	                addToReadyQueue();
                }
            } else {

                // All our of jobs to schedule and run.
                if (processes.isEmpty()) {

                    done = true;
                }

                time += Q;
            }
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

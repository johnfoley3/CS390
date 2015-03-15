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
    private final int Q = 1;

	public RR(ArrayList<SchedulableProcess> processes) {

        this.processes = new ArrayList<SchedulableProcess>(processes);
        this.time = 0;
        this.readyQueue = new ArrayList<SchedulableProcess>();

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

                for (Iterator<SchedulableProcess> iterator = readyQueue.iterator(); iterator.hasNext(); ) {

                    SchedulableProcess process = iterator.next();

                    process.incrementTimeExecuted( Q );

                    time += Q;

                    // If the process is finished, then remove it from the ready queue
                    if (process.getTimeExecuted() == process.getExecTime()) {

                        iterator.remove();
                        process.setDeparture(time);
                    }
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

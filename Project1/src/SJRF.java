import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author John Foley
 *
 * Short for: Shortest Job Remaining First
 */
public class SJRF {

    private ArrayList<SchedulableProcess> processes;
    private ArrayList<SchedulableProcess> readyQueue;
    private int time;

    /**
     * Constant int representing the time quantum. For this project, it is specified to be 1.
     */
    private final int Q = 1;

    /**
     * Constructor
     * @param processes List of processes to schedule
     */
	public SJRF(final ArrayList<SchedulableProcess> processes) {

        this.processes = new ArrayList<SchedulableProcess>(processes);
        this.time = 0;
        this.readyQueue = new ArrayList<SchedulableProcess>();

        runScheduler();
	}

    /**
     * Schedules processes using the Shortest Job Remaining First algorithm.
     */
    private void runScheduler() {

        boolean done = false;
        SchedulableProcess currentProcess;

        while (!done) {

            addToReadyQueue();

            if (!readyQueue.isEmpty()) {
                currentProcess = lookForShortestProcess();

                currentProcess.incrementTimeExecuted(Q);

                if (currentProcess.getTimeExecuted() == currentProcess.getExecTime()) {

                    currentProcess.setDeparture(time + 1);
                    readyQueue.remove(currentProcess);
                }
            } else if (processes.isEmpty()) {

                done = true;
            }

            time += Q;
        }
    }

    /**
     * Looks for the process with the shortest execution time remaining. Will be FCFS in a tie.
     * @return Process with the shortest remaining execution time left.
     */
    private SchedulableProcess lookForShortestProcess() {

        // Initialize to first element. Makes the for loop redudendent, but it works.
        SchedulableProcess nextProcess = readyQueue.get(0);
        int min = nextProcess.getExecTime() - nextProcess.getTimeExecuted();

        // Only continue if there is another element in the list, otherwise we'll continue and return the first
        // and only element in the list
        if (readyQueue.size() > 1) {

            // Start at the second element in the list
            for (int i = 1; i < readyQueue.size(); i++) {

                SchedulableProcess process = readyQueue.get(i);

                int timeRemaining = process.getExecTime() - process.getTimeExecuted();

                // Strictly less than forces FCFS in the algorithm
                if (timeRemaining < min) {

                    nextProcess = process;
                    min = timeRemaining;
                }
            }
        }

        return nextProcess;
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

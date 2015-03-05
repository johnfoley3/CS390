# CS390
CS390 Operating Systems' projects.

## P1
Write a program that will simulate the FCFS (First Come First Serve), Shortest Job Remaining First, and Round Robin
(time quantum, Q, = 1) scheduling algorithms. For each algorithm, your program should calculate and print the process
number, arrival, departure, execution, turn around, and waiting time of each process. It should then calculate and print
the average turn around time and average waiting time of the schedule achieved using the algorithm.

Your program should take no input from the keyboard. Instead, it should read the name of a text file containing a
schedule of processes from the command line, and then read all the information it needs to simulate the algorithms from
that file. The file will be formatted as a series of one or more lines, and each line will contain information two
integers representing the arrival time and total execution time of a process. The first process being P0.

You may assume that the processes are ordered in the file by arrival time.

FCFS is straightforward. SRTF should reschedule every clock tick, and use FCFS to break ties. Round robin should also
reschedule every clock tick, but if a new process arrives, your program may place it anywhere in the ready queue that
you find convenient. In the sample implementation, the ready queue is kept in FCFS order, and the new process is only
scheduled after it's predecessor in the queue runs.

Turn in the source files as an attachment in an email. Package the files properly for your implementation language.
The submission should only contain the files needed to build and execute your program. Include in the email instructions
on how to build and execute your program from the Sand command line. Then print out hard copies of the source, stapled
in main/ function/ class order. Submit within 24 hours of the electronic submission.

package com.company;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Magdalena Polak on 28.03.2016.
 */
public class Task
{
    int arrival;
    int cylinderNumber;
    int deadline;
    int done;
    public Task(int arrival,  int cylinderNumber)
    {
        this.arrival = arrival;
        this.cylinderNumber = cylinderNumber;
    }
    public Task(int arrival,  int cylinderNumber, int deadline, int done)
    {
        this.arrival = arrival;
        this.cylinderNumber = cylinderNumber;
        this.deadline = deadline;
        this.done = done;
    }
    public Task(int arrival,  int cylinderNumber, int deadline)
    {
        this.arrival = arrival;
        this.cylinderNumber = cylinderNumber;
        this.deadline = deadline;

    }
    public Task(Task t)
    {
        this.arrival = t.arrival;
        this.deadline = t.deadline;
        this.cylinderNumber = t.cylinderNumber;
        this.done = t.done;
    }
    public int getArrival()
    {
        return arrival;

    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getCylinderNumber() {
        return cylinderNumber;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public void setCylinderNumber(int cylinderNumber) {
        this.cylinderNumber = cylinderNumber;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public static void compareCylinderWithCurrentBlock(final int currentBlock, ArrayList a)
    {
          Comparator<Task> closestTaskComparator= new Comparator<Task>()
          {

            public int compare(Task o1, Task o2)
            {
                return Math.abs(o1.cylinderNumber - currentBlock) - Math.abs(o2.cylinderNumber - currentBlock);
            }
         };
        Collections.sort(a,closestTaskComparator );
    }

    public static void compareWithCurrentBlockForwards(final int currentBlock, ArrayList a) {


        Comparator<Task> closestTaskComparator1 = new Comparator<Task>()
        {


            public int compare(Task o1, Task o2) {


                if (o1.cylinderNumber - currentBlock > 0) {
                    if (o2.cylinderNumber - currentBlock > 0) {

                        return (o1.cylinderNumber - currentBlock) - (o2.cylinderNumber - currentBlock);
                    }
                    return -1;
                } else if (o1.cylinderNumber - currentBlock > 0) {
                    return 1;
                } else
                    return 0;



            }

            //return Math.abs(o1.cylinderNumber - currentBlock) - Math.abs(o2.cylinderNumber - currentBlock);

        };
            Collections.sort(a, closestTaskComparator1);

    }
    public static void compareWithCurrentBlockBackwards(final int currentBlock, ArrayList a) {


        Comparator<Task> closestTaskComparator1 = new Comparator<Task>()
        {


            public int compare(Task o1, Task o2) {


                if (o1.cylinderNumber - currentBlock < 0) {
                    if (o2.cylinderNumber - currentBlock < 0) {

                        return Math.abs(o1.cylinderNumber - currentBlock) - Math.abs(o2.cylinderNumber - currentBlock);
                    }
                    return -1;
                } else if (o1.cylinderNumber - currentBlock < 0) {
                    return 1;
                } else
                    return 0;



            }

            //return Math.abs(o1.cylinderNumber - currentBlock) - Math.abs(o2.cylinderNumber - currentBlock);

        };
        Collections.sort(a, closestTaskComparator1);

    }
    public static void compareWithCurrentBlock(final int currentBlock, ArrayList a)
    {
        Comparator<Task> closestTaskComparator= new Comparator<Task>()
        {

            public int compare(Task o1, Task o2)
            {

                return Math.abs(o1.cylinderNumber - currentBlock) - Math.abs(o2.cylinderNumber - currentBlock);
            }
        };
        Collections.sort(a,closestTaskComparator );
    }

    public static  Comparator<Task> closestTaskComparator = new Comparator<Task>() {

        public int compare(Task o1, Task o2) {
            return o1.cylinderNumber - o2.cylinderNumber;
        }
    };

    public static Comparator<Task> deadlineComparator = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.deadline - o2.deadline;
        }
    };
    public static Comparator<Task> deadlineArrivalComparator = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            int result =  o1.deadline - o2.deadline;
            if(result != 0)
                return  result;
            else
            {
                return o1.arrival - o2.arrival;
            }
        }
    };
    public static Comparator<Task> arrivalComparator = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.arrival - o2.arrival;
        }
    };


    public String toString()
    {
        return arrival + " " + cylinderNumber + " " + deadline;
    }



/*dwie listy zlecen: normalne i czasu rzeczywistego; na jednym obszarze dysku jedno zlecenie; id do konca dysku(scan) dochodzi do sciany,
ustalay czas ruchu dyskyu, zakadamy ze wykonanie trwa 0, czas dostepu liczymy, przejscie od konca do poczatku to jeden ruch glowicy,
 30 procent rzeczywistego
 czas wejscia, nr  bloku, (deadline),
 */
}

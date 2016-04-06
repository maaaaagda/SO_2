package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {


      // System.out.println(a.FCFS_EDF());
      // System.out.println(a.FCFS_FD_SCAN());
       // System.out.println("SSTF_EDF: " + a.SSTF_EDF());
       //System.out.println(a.SSTF_FD_SCAN());
        // System.out.println(a.SCAN_EDF());
        //System.out.println(a.SCAN_FD_SCAN());
       // System.out.println(a.C_SCAN_EDF());
       // System.out.println(a.C_SCAN_FD_SCAN());

   /*     ArrayList <Task> priorityQueue = new ArrayList<>();
        priorityQueue.add(new Task(34, 35, 200));
        priorityQueue.add(new Task(32, 80, 180));
        priorityQueue.add(new Task(35, 45, 300));
        priorityQueue.add(new Task(85, 55, 20));
        ArrayList <Task> queue = new ArrayList<>();
        queue.add(new Task(10, 30));
        queue.add(new Task(20, 50));
        queue.add(new Task(30, 10));
        queue.add(new Task(40, 5));
        queue.add(new Task(50, 100));
        queue.add(new Task(400, 17));
        queue.add(new Task(500, 80));
        queue.add(new Task(600, 77));
        Algorithms a = new Algorithms(queue, priorityQueue);
        a.SSTF_EDF();   */
       Results r = new Results(1, 500, 20);
       System.out.println(r.averageResults());
    }
}

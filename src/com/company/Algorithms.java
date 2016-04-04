package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Magdalena Polak on 29.03.2016.
 */
public class Algorithms {

    final int DISK_SIZE = 100;
    ArrayList<Task> priorityQueue = new ArrayList<Task>();
    ArrayList<Task> queue = new ArrayList<Task>();

    public Algorithms() {
        queue.add(new Task(10, 30));
        queue.add(new Task(20, 50));
        queue.add(new Task(30, 10));
        queue.add(new Task(40, 5));
        queue.add(new Task(50, 100));
        queue.add(new Task(400, 17));
      /*  queue.add(new Task(230, 10, 50, 0));
        queue.add(new Task(240, 70, 50, 0));
        queue.add(new Task(250, 49, 50, 0));
        queue.add(new Task(300, 0, 50, 200));
        queue.add(new Task(340, 5, 50, 0));
        queue.add(new Task(350, 90, 50, 0)); */
        priorityQueue.add(new Task(34, 35, 200));
        priorityQueue.add(new Task(32, 80, 180));
        priorityQueue.add(new Task(35, 45, 300));

    }

    public int FCFS() {

        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(aTask);
        }

        Collections.sort(tasks, Task.arrivalComparator);
        int blocks = tasks.get(0).cylinderNumber;
        for (int i = 0; i < tasks.size() - 1; i++) {
            blocks += Math.abs(tasks.get(i + 1).cylinderNumber - tasks.get(i).cylinderNumber);
        }
        return blocks;
    }

    public int SSTF() {

        int blocks = 0;
        int currentBlock = 0;
        int moved = 0;
        int currentTime = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(aTask);
        }

        Collections.sort(tasks, Task.arrivalComparator);


        do {
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("dodany" + waitingTasks);
                }


            }
            if (moved == 0 && waitingTasks.size() != 0) {
                System.out.println("break");
                if (waitingTasks.get(0).done == 1) {
                    waitingTasks.get(0).setDone(0);
                    waitingTasks.remove(0);
                }
                if (waitingTasks.size() != 0) {
                    Task.compareWithCurrentBlock(currentBlock, waitingTasks);
                    System.out.println(waitingTasks);
                    moved = Math.abs(waitingTasks.get(0).cylinderNumber - currentBlock);
                    currentBlock = waitingTasks.get(0).cylinderNumber;
                    waitingTasks.get(0).setDone(1);
                }
            }
            if (waitingTasks.size() != 0) {
                moved--;
                blocks++;
                System.out.println(moved + " " + blocks);
            }
            currentTime++;


        }
        while (currentTime != 100000);
        return blocks;

    }

    public int SCAN() {
        int blocks = 0;
        int currentBlock = 0;
        int moved = 0;
        int currentTime = 0;
        boolean forwards = true;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(new Task(aTask));
        }

        Collections.sort(tasks, Task.arrivalComparator);
        System.out.println("TTTTTT" + tasks);

        do {
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);
                    System.out.println(moved);
                }


            }
            if (moved == 0 && waitingTasks.size() != 0) {
                System.out.println(blocks);
                if (waitingTasks.get(0).done == 1) {
                    System.out.println("removed" + waitingTasks.get(0));
                    //   waitingTasks.get(0).setDone(0);
                    waitingTasks.remove(0);

                }
                if (waitingTasks.size() != 0) {
                    if (forwards == true) {
                        Task.compareWithCurrentBlockForwards(currentBlock, waitingTasks);
                        System.out.println("forwards" + waitingTasks);
                        if (waitingTasks.get(0).cylinderNumber > currentBlock) {
                            moved = Math.abs(waitingTasks.get(0).cylinderNumber - currentBlock);
                            currentBlock = waitingTasks.get(0).cylinderNumber;
                            waitingTasks.get(0).setDone(1);
                        }
                    } else {
                        Task.compareWithCurrentBlockBackwards(currentBlock, waitingTasks);
                        System.out.println("backwards" + waitingTasks);
                        if (waitingTasks.get(0).cylinderNumber < currentBlock) {
                            moved = Math.abs(waitingTasks.get(0).cylinderNumber - currentBlock);
                            currentBlock = waitingTasks.get(0).cylinderNumber;
                            waitingTasks.get(0).setDone(1);
                        }
                    }


                    if (currentBlock == DISK_SIZE) {
                        forwards = false;
                    }
                    if (currentBlock == 0) {
                        forwards = true;
                    }
                }
            }
            if (waitingTasks.size() != 0 && moved != 0) {
                moved--;
                blocks++;
            }

            currentTime++;


        }
        while (currentTime != 1000);
        return blocks;

    }

    public int C_SCAN() {
        int blocks = 0;
        int currentBlock = 0;
        int moved = 0;
        int currentTime = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(aTask);
        }

        Collections.sort(tasks, Task.arrivalComparator);
        System.out.println(tasks);

        do {
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);
                    System.out.println(moved);
                }
            }
            if (moved == 0 && waitingTasks.size() != 0) {
                System.out.println(blocks);
                if (waitingTasks.get(0).done == 1) {
                    System.out.println("removed" + waitingTasks.get(0));
                    waitingTasks.get(0).setDone(0);
                    waitingTasks.remove(0);

                }
                if (waitingTasks.size() != 0) {

                    Task.compareWithCurrentBlockForwards(currentBlock, waitingTasks);
                    System.out.println("forwards" + waitingTasks);
                    if (waitingTasks.get(0).cylinderNumber >= currentBlock) {
                        moved = Math.abs(waitingTasks.get(0).cylinderNumber - currentBlock);
                        currentBlock = waitingTasks.get(0).cylinderNumber;
                        waitingTasks.get(0).setDone(1);
                    }


                    if (currentBlock == DISK_SIZE) {
                        currentBlock = 0;
                        blocks += 1;
                    }

                }
            }
            if (waitingTasks.size() != 0 && moved != 0) {
                moved--;
                blocks++;
            }
            currentTime++;
        }
        while (currentTime != 1000);
        return blocks;

    }


    public int FCFS_EDF() {
        int blocks = 0;
        int currentBlock = 0;
        int currentTime = 0;
        int rejected = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> priorityTasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        ArrayList<Task> waitingPriorityTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(new Task(aTask));
        }
        for (Task aTask : priorityQueue) {
            priorityTasks.add(new Task(aTask));
        }
        System.out.println("proritytasks" + priorityTasks);
        do {
            /**
             * copying to waiting queues when current time equals arrival time
             */
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);

                }
            }
            for (int i = 0; i < priorityTasks.size(); i++)

            {
                if (currentTime == (priorityTasks.get(i)).getArrival()) {

                    waitingPriorityTasks.add(priorityTasks.get(i));
                    System.out.println("waitingPrority" + waitingPriorityTasks);

                }
            }
            if (waitingPriorityTasks.size() != 0) {
                System.out.println("Priority: " + waitingPriorityTasks);
                /**
                 * if pointer reaches task cylinder number its being removed
                 */
                if (waitingPriorityTasks.get(0).cylinderNumber == currentBlock) {
                    System.out.println("---------------------------------------------Priority done" + waitingPriorityTasks.get(0));
                    waitingPriorityTasks.remove(0);

                }
                /**
                 * when new task has come or last task has been done
                 */
                Collections.sort(waitingPriorityTasks, Task.deadlineComparator);
                if (waitingPriorityTasks.size() != 0 && currentBlock == waitingPriorityTasks.get(0).cylinderNumber) {

                    /**
                     * if pointer wont meet deadline is rejected
                     */
                    if (waitingPriorityTasks.get(0).deadline < Math.abs(waitingPriorityTasks.get(0).cylinderNumber - currentBlock)) {
                        System.out.println("rejection");
                        rejected++;
                    }
                }


                if (waitingPriorityTasks.size() != 0) {
                    /**
                     * if pointer is moving forwards
                     */
                    if (waitingPriorityTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    }
                    /**
                     * if pointer is moving backwards
                     */
                    else {
                        currentBlock--;
                    }
                    blocks++;
                    /**
                     * deadline is descanding with time
                     */
                    for (int i = 0; i < waitingPriorityTasks.size(); i++) {
                        waitingPriorityTasks.get(i).setDeadline(waitingPriorityTasks.get(i).deadline - 1);
                    }

                }
            }
            /**
             * if there is no tasks in priority queue but are tasks without deadline
             */
            else if (waitingTasks.size() != 0) {
                System.out.println("Norlmalne: " + waitingTasks);
                /**
                 * if pointer reaches task cylinder number its being removed
                 */
                if (currentBlock == waitingTasks.get(0).cylinderNumber) {
                    System.out.println("-----------------------------------------------Normal task has been done: " + waitingTasks.get(0));
                    waitingTasks.remove(0);
                }
                /**
                 * processing other tasks from normaln queue, sorted by arrival time
                 */
                if (waitingTasks.size() != 0) {
                    Collections.sort(waitingTasks, Task.arrivalComparator);
                    if (waitingTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    } else {
                        currentBlock--;
                    }
                    blocks++;


                }

            }


            currentTime++;
            if (waitingPriorityTasks.size() != 0 || waitingTasks.size() != 0) {
                System.out.println("Currenttime: " + currentTime);
                System.out.println("Currentblock: " + currentBlock);
                System.out.println("Blocks: " + blocks);
            } else {
                System.out.println("Waiting for a task");
            }


        }
        while (currentTime != 1000);
        return blocks;
    }

    public int FCFS_FD_SCAN() {
        int blocks = 0;
        int currentBlock = 0;
        int currentTime = 0;
        int rejected = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> priorityTasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        ArrayList<Task> waitingPriorityTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(new Task(aTask));
        }
        for (Task aTask : priorityQueue) {
            priorityTasks.add(new Task(aTask));
        }
        System.out.println("proritytasks" + priorityTasks);
        do {
            /**
             * copying to waiting queues when current time equals arrival time
             */
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);

                }
            }
            for (int i = 0; i < priorityTasks.size(); i++)

            {
                if (currentTime == (priorityTasks.get(i)).getArrival()) {

                    waitingPriorityTasks.add(priorityTasks.get(i));
                    System.out.println("waitingPrority" + waitingPriorityTasks);

                }
            }
            if (waitingPriorityTasks.size() != 0) {
                System.out.println("Priority: " + waitingPriorityTasks);
                /**
                 * if pointer reaches task cylinder number its being removed
                 */
                if (waitingPriorityTasks.get(0).cylinderNumber == currentBlock) {
                    System.out.println("---------------------------------------------Prorytet wykonany" + waitingPriorityTasks.get(0));
                    waitingPriorityTasks.remove(0);

                }
                /**
                 * when new task has come or last task has been done
                 */
                Collections.sort(waitingPriorityTasks, Task.deadlineComparator);

                if (waitingPriorityTasks.size() != 0 && currentBlock == waitingPriorityTasks.get(0).cylinderNumber) {
                    /**
                     * if pointer wont meet deadline is rejected
                     */
                    if (waitingPriorityTasks.get(0).deadline < Math.abs(waitingPriorityTasks.get(0).cylinderNumber - currentBlock)) {
                        System.out.println("rejection");
                        rejected++;
                    }
                }


                if (waitingPriorityTasks.size() != 0) {
                    /**
                     * when pointer is moving forwards
                     */
                    if (waitingPriorityTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    }
                    /**
                     * when pointer is moving backwards
                     */
                    else {
                        currentBlock--;
                    }
                    blocks++;

                    /**
                     * deadline is descanding with time
                     */
                    for (int i = 1; i < waitingPriorityTasks.size(); i++) {
                        waitingPriorityTasks.get(i).setDeadline(waitingPriorityTasks.get(i).deadline - 1);
                        /**
                         * doing task along the way of pointer
                         */
                        if (currentBlock == waitingPriorityTasks.get(i).cylinderNumber) {
                            System.out.println("--------------------Removed on the way" + waitingPriorityTasks.get(i));
                            waitingPriorityTasks.remove(i);
                        }
                    }
                }
            }
            /**
             * not priority tasks
             */
            else if (waitingTasks.size() != 0) {
                System.out.println("Norlmal tasks: " + waitingTasks);
                /**
                 * removing task that has been done
                 */
                if (currentBlock == waitingTasks.get(0).cylinderNumber) {
                    System.out.println("-----------------------------------------------Normal task has been done: " + waitingTasks.get(0));
                    waitingTasks.remove(0);
                }
                /**
                 *  other tasks sorted by arrival time
                 */
                if (waitingTasks.size() != 0) {
                    Collections.sort(waitingTasks, Task.arrivalComparator);
                    if (waitingTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    } else {
                        currentBlock--;
                    }
                    blocks++;
                }

            }
             currentTime++;
            if (waitingPriorityTasks.size() != 0 || waitingTasks.size() != 0) {
                System.out.println("Currenttime: " + currentTime);
                System.out.println("Currentblock: " + currentBlock);
                System.out.println("Blocks: " + blocks);
            } else {
                System.out.println("Waiting for a task");
            }


        }
        while (currentTime != 1000);
        return blocks;
    }

    public int SSTF_EDF() {
        int blocks = 0;
        int currentBlock = 0;
        int currentTime = 0;
        int rejected = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> priorityTasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        ArrayList<Task> waitingPriorityTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(new Task(aTask));
        }
        for (Task aTask : priorityQueue) {
            priorityTasks.add(new Task(aTask));
        }
        System.out.println("proritytasks" + priorityTasks);
        do {
            /**
             * copying to waiting queues when current time equals arrival time
             */
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);

                }
            }
            for (int i = 0; i < priorityTasks.size(); i++)

            {
                if (currentTime == (priorityTasks.get(i)).getArrival()) {

                    waitingPriorityTasks.add(priorityTasks.get(i));
                    System.out.println("waitingPrority" + waitingPriorityTasks);

                }
            }
            if (waitingPriorityTasks.size() != 0) {
                System.out.println("Priority: " + waitingPriorityTasks);
                /**
                 * if pointer reaches task cylinder number its being removed
                 */
                if (waitingPriorityTasks.get(0).cylinderNumber == currentBlock) {
                    System.out.println("---------------------------------------------Prorytet wykonany" + waitingPriorityTasks.get(0));
                    waitingPriorityTasks.remove(0);

                }
                /**
                 * when new task has come or last task has been done
                 */
                if (waitingPriorityTasks.size() != 0 && currentBlock == waitingPriorityTasks.get(0).cylinderNumber) {
                    Collections.sort(waitingPriorityTasks, Task.deadlineComparator);
                    /**
                     * if pointer wont meet deadline is rejected
                     */
                    if (waitingPriorityTasks.get(0).deadline < Math.abs(waitingPriorityTasks.get(0).cylinderNumber - currentBlock)) {
                        System.out.println("rejection");
                        rejected++;
                    }
                }


                if (waitingPriorityTasks.size() != 0) {
                    /**
                     * when pointer is moving forwards
                     */
                    if (waitingPriorityTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    }
                    /**
                     * when pointer is moving backwards
                     */
                    else {
                        currentBlock--;
                    }
                    blocks++;

                    /**
                     * deadline is descanding with time
                     */
                    for (int i = 1; i < waitingPriorityTasks.size(); i++) {
                        waitingPriorityTasks.get(i).setDeadline(waitingPriorityTasks.get(i).deadline - 1);
                        }
                }
            } else if (waitingTasks.size() != 0)
            {
                System.out.println("Normal tasks: " + waitingTasks);
                /**
                 * removing task that has been done
                 */
                if (currentBlock == waitingTasks.get(0).cylinderNumber) {
                    System.out.println("-----------------------------------------------Normal task has been done: " + waitingTasks.get(0));
                    waitingTasks.remove(0);
                }
                /**
                 *  other tasks sorted by arrival time
                 */
                if (waitingTasks.size() != 0) {
                    Task.compareWithCurrentBlock(currentBlock, waitingTasks);
                    if (waitingTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    }
                    else {
                        currentBlock--;
                    }
                    blocks++;
                }

            }
            currentTime++;
            if (waitingPriorityTasks.size() != 0 || waitingTasks.size() != 0) {
                System.out.println("Currenttime: " + currentTime);
                System.out.println("Currentblock: " + currentBlock);
                System.out.println("Blocks: " + blocks);
            } else {
                System.out.println("Waiting for a task");
            }


        }
        while (currentTime != 1000);
        return blocks;
    }
    public int SSTF_FD_SCAN() {
        int blocks = 0;
        int currentBlock = 0;
        int currentTime = 0;
        int rejected = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> priorityTasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        ArrayList<Task> waitingPriorityTasks = new ArrayList<Task>();
        for (Task aTask : queue) {
            tasks.add(new Task(aTask));
        }
        for (Task aTask : priorityQueue) {
            priorityTasks.add(new Task(aTask));
        }
        System.out.println("proritytasks" + priorityTasks);
        do {
            /**
             * copying to waiting queues when current time equals arrival time
             */
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);

                }
            }
            for (int i = 0; i < priorityTasks.size(); i++)

            {
                if (currentTime == (priorityTasks.get(i)).getArrival()) {

                    waitingPriorityTasks.add(priorityTasks.get(i));
                    System.out.println("waitingPrority" + waitingPriorityTasks);

                }
            }
            if (waitingPriorityTasks.size() != 0) {
                System.out.println("Priority: " + waitingPriorityTasks);
                /**
                 * if pointer reaches task cylinder number its being removed
                 */
                if (waitingPriorityTasks.get(0).cylinderNumber == currentBlock) {
                    System.out.println("---------------------------------------------Prorytet wykonany" + waitingPriorityTasks.get(0));
                    waitingPriorityTasks.remove(0);

                }
                /**
                 * when new task has come or last task has been done
                 */
                if (waitingPriorityTasks.size() != 0 && currentBlock == waitingPriorityTasks.get(0).cylinderNumber) {
                    Collections.sort(waitingPriorityTasks, Task.deadlineComparator);
                    /**
                     * if pointer wont meet deadline is rejected
                     */
                    if (waitingPriorityTasks.get(0).deadline < Math.abs(waitingPriorityTasks.get(0).cylinderNumber - currentBlock)) {
                        System.out.println("rejection");
                        rejected++;
                    }
                }


                if (waitingPriorityTasks.size() != 0) {
                    /**
                     * when pointer is moving forwards
                     */
                    if (waitingPriorityTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    }
                    /**
                     * when pointer is moving backwards
                     */
                    else {
                        currentBlock--;
                    }
                    blocks++;

                    /**
                     * deadline is descanding with time
                     */
                    for (int i = 1; i < waitingPriorityTasks.size(); i++) {
                        waitingPriorityTasks.get(i).setDeadline(waitingPriorityTasks.get(i).deadline - 1);
                        /**
                         * doing task along the way of pointer
                         */
                        if (currentBlock == waitingPriorityTasks.get(i).cylinderNumber) {
                            System.out.println("--------------------Removed on the way" + waitingPriorityTasks.get(i));
                            waitingPriorityTasks.remove(i);
                        }
                    }
                }
            } else if (waitingTasks.size() != 0)
            {
                System.out.println("Normal tasks: " + waitingTasks);
                /**
                 * removing task that has been done
                 */
                if (currentBlock == waitingTasks.get(0).cylinderNumber) {
                    System.out.println("-----------------------------------------------Normal task has been done: " + waitingTasks.get(0));
                    waitingTasks.remove(0);
                }
                /**
                 *  other tasks sorted by arrival time
                 */
                if (waitingTasks.size() != 0) {
                    Task.compareWithCurrentBlock(currentBlock, waitingTasks);
                    if (waitingTasks.get(0).cylinderNumber > currentBlock) {
                        currentBlock++;
                    }
                    else {
                        currentBlock--;
                    }
                    blocks++;
                }

            }
            currentTime++;
            if (waitingPriorityTasks.size() != 0 || waitingTasks.size() != 0) {
                System.out.println("Currenttime: " + currentTime);
                System.out.println("Currentblock: " + currentBlock);
                System.out.println("Blocks: " + blocks);
            } else {
                System.out.println("Waiting for a task");
            }


        }
        while (currentTime != 100.0);
        return blocks;
    }

    public void EDF(ArrayList<Task> waitingPriorityTasks, int currentBlock, int rejected, int blocks)
    {
        System.out.println("Priority: " + waitingPriorityTasks);
        /**
         * if pointer reaches task cylinder number its being removed
         */
        if (waitingPriorityTasks.get(0).cylinderNumber == currentBlock) {
            System.out.println("---------------------------------------------Prorytet wykonany" + waitingPriorityTasks.get(0));
            waitingPriorityTasks.remove(0);

        }
        /**
         * when new task has come or last task has been done
         */
        if (waitingPriorityTasks.size() != 0 && currentBlock == waitingPriorityTasks.get(0).cylinderNumber) {
            Collections.sort(waitingPriorityTasks, Task.deadlineComparator);
            /**
             * if pointer wont meet deadline is rejected
             */
            if (waitingPriorityTasks.get(0).deadline < Math.abs(waitingPriorityTasks.get(0).cylinderNumber - currentBlock)) {
                System.out.println("rejection");
                rejected++;
            }
        }
        if (waitingPriorityTasks.size() != 0) {
            /**
             * if pointer is moving forwards
             */
            if (waitingPriorityTasks.get(0).cylinderNumber > currentBlock) {
                currentBlock++;
            }
            /**
             * if pointer is moving backwards
             */
            else {
                currentBlock--;
            }
            blocks++;
            /**
             * deadline is descanding with time
             */
            for (int i = 0; i < waitingPriorityTasks.size(); i++) {
                waitingPriorityTasks.get(i).setDeadline(waitingPriorityTasks.get(i).deadline - 1);
            }

        }
    }
    public void FD_SCAN(ArrayList<Task> waitingPriorityTasks, int currentBlock, int rejected, int blocks)
    {
        System.out.println("Priority: " + waitingPriorityTasks);
        /**
         * if pointer reaches task cylinder number its being removed
         */
        if (waitingPriorityTasks.get(0).cylinderNumber == currentBlock) {
            System.out.println("---------------------------------------------Prorytet wykonany" + waitingPriorityTasks.get(0));
            waitingPriorityTasks.remove(0);

        }
        /**
         * when new task has come or last task has been done
         */
        if (waitingPriorityTasks.size() != 0 && currentBlock == waitingPriorityTasks.get(0).cylinderNumber) {
            Collections.sort(waitingPriorityTasks, Task.deadlineComparator);
            /**
             * if pointer wont meet deadline is being rejected
             */
            if (waitingPriorityTasks.get(0).deadline < Math.abs(waitingPriorityTasks.get(0).cylinderNumber - currentBlock)) {
                System.out.println("rejection");
                rejected++;
            }
        }


        if (waitingPriorityTasks.size() != 0) {
            /**
             * when pointer is moving forwards
             */
            if (waitingPriorityTasks.get(0).cylinderNumber > currentBlock) {
                currentBlock++;
            }
            /**
             * when pointer is moving backwards
             */
            else {
                currentBlock--;
            }
            blocks++;

            /**
             * deadline is descanding with time
             */
            for (int i = 1; i < waitingPriorityTasks.size(); i++) {
                waitingPriorityTasks.get(i).setDeadline(waitingPriorityTasks.get(i).deadline - 1);
                /**
                 * doing task along the way of pointer
                 */
                if (currentBlock == waitingPriorityTasks.get(i).cylinderNumber) {
                    System.out.println("--------------------Removed on the way" + waitingPriorityTasks.get(i));
                    waitingPriorityTasks.remove(i);
                }
            }
        }
    }
}
/*
    public int LOOK()
    {
        int blocks = 0;
        int currentBlock = 0;
        int moved = 0;
        int currentTime = 0;
        boolean forwards = true;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> waitingTasks = new ArrayList<Task>();
        for(Task aTask : queue)
        {
            tasks.add(aTask);
        }

        Collections.sort(tasks, Task.arrivalComparator);
        System.out.println(tasks);

        do {
            for (int i = 0; i < tasks.size(); i++)

            {
                if (currentTime == (tasks.get(i)).getArrival()) {

                    waitingTasks.add(tasks.get(i));
                    System.out.println("waiting" + waitingTasks);
                    System.out.println(moved);
                }
            }
            if (moved == 0 && waitingTasks.size() != 0 )
            {
                System.out.println(blocks);
                if(waitingTasks.get(0).done == 1) {
                    System.out.println("removed" + waitingTasks.get(0));
                    waitingTasks.get(0).setDone(0);
                    waitingTasks.remove(0);

                }
                if(waitingTasks.size() != 0 )
                {
                    if(forwards == true) {
                        Task.compareWithCurrentBlockForwards(currentBlock, waitingTasks);
                        System.out.println("forwards" + waitingTasks);
                        if(waitingTasks.get(0).cylinderNumber>=currentBlock)
                        {
                            moved = Math.abs(waitingTasks.get(0).cylinderNumber - currentBlock);
                            currentBlock = waitingTasks.get(0).cylinderNumber;
                            waitingTasks.get(0).setDone(1);
                        }
                        else {
                            forwards = false;
                        }
                    }
                    else {
                        Task.compareWithCurrentBlockBackwards(currentBlock, waitingTasks);
                        System.out.println("backwards" + waitingTasks);
                        if(waitingTasks.get(0).cylinderNumber<=currentBlock)
                        {
                            moved = Math.abs(waitingTasks.get(0).cylinderNumber - currentBlock);
                            currentBlock = waitingTasks.get(0).cylinderNumber;
                            waitingTasks.get(0).setDone(1);
                        }
                        else {
                            forwards = true;
                        }
                    }
                    if(currentBlock == DISK_SIZE)
                    {
                        forwards = false;
                    }
                    if(currentBlock == 0)
                    {
                        forwards = true;
                    }
                }
            }
            if(waitingTasks.size() != 0 && moved != 0 )
            {
                moved--;
                blocks++;
            }
            currentTime++;
        }
        while (currentTime != 1000);
        return blocks;

    }
  */

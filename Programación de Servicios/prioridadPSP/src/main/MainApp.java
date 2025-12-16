package main;

import util.MessageClass;

import java.util.concurrent.PriorityBlockingQueue;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {


        PriorityBlockingQueue<MessageClass> queue = new PriorityBlockingQueue<>();

        queue.put(new MessageClass("Uno", 2));
        queue.add(new MessageClass("Dos", 1));
        queue.add(new MessageClass("Tres", 3));

        System.out.println(queue.poll().toString()); // saca el de prioridad 1
        System.out.println(queue.poll().toString()); // saca el de prioridad 2
        System.out.println(queue.take().toString()); // saca el de prioridad 3
        System.out.println(queue.take().toString()); // saca el de prioridad 3


    }
}

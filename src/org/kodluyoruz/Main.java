package org.kodluyoruz;
import  java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Order order = new Order();

        ExecutorService executorTable= Executors.newFixedThreadPool(5);

        Thread chefThread1=new Thread(new Chef(order,"Chef1"));
        Thread chefThread2=new Thread(new Chef(order,"Chef2"));

        Thread waitressThread1= new Thread(new Waitress(order,"Waitress1"));
        Thread waitressThread2= new Thread(new Waitress(order,"Waitress2"));
        Thread waitressThread3= new Thread(new Waitress(order,"Waitress3"));
        chefThread1.start();
        chefThread2.start();

        waitressThread1.start();
        waitressThread2.start();
        waitressThread3.start();



        for(int i=1;i<=50;i++){
            executorTable.submit(new Table(order,"Customer"+i));
        }

        chefThread1.join();
        chefThread2.join();
        waitressThread1.join();
        waitressThread2.join();
        waitressThread3.join();
        executorTable.awaitTermination(1,TimeUnit.DAYS);

        executorTable.shutdown();



    }
}

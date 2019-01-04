package com.joseph.consumer.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test implements Runnable {


        static int i=0;
        public static synchronized void increase(){
            i++;
        }
        @Override
        public void run() {
            for(int j=0;j<1000000;j++){
                increase();
            }
        }
        public static void main(String[] args) throws InterruptedException {
            ExecutorService executorService= Executors.newFixedThreadPool(5);
            Test test=new Test();
            //new新实例
            Thread t1=new Thread(new Test());
            //new新实例
            Thread t2=new Thread(new Test());
            t1.start();
            t2.start();
            //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
            t1.join();
            t2.join();
            System.out.println(i);
        }
}

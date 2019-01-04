package com.joseph.provider.service;

import com.joseph.provider.redis.RedisClientImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestThread extends Thread {

    private int ticket = 100;

    /*Lock lock=new ReentrantLock();
    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();*/

    public RedisClientImpl redisClient;

    TestThread(int ticket,RedisClientImpl redisClient){
        this.ticket=ticket;
        this.redisClient=redisClient;
    }

    @Override
    public  void run() {
        String indentifier =redisClient.lockWithTimeout("resource", 5000, 3000);
        ticket--;
        System.out.println(Thread.currentThread().getName()+"剩余库存***************"+ticket);
        redisClient.releaseLock("resource", indentifier);

    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
}

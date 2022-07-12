package c_DuoXianCheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class demo07 {

    static  int piao=100;
    static ReentrantLock lock = new ReentrantLock();
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();

    static boolean flag=true;
//    static boolean flagB=false;



    public static void main(String[] args) {

        while (true){

            new Thread(()->{
                lock.lock();
                try {

                    while (flag==false){
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(piao>=0){

                        System.out.println("线程1在买票  "+piao);
                        piao--;


                        flag=false;
                        conditionB.signal();

                    }

                }finally {
                    lock.unlock();
                }

            }).start();

            new Thread(()->{
                lock.lock();
                try {
                    while (flag==true){
                        conditionB.await();
                    }
                    if(piao>=0){
                        System.out.println("线程222222在买票   "+piao);
                        piao--;

                        flag=true;
                        conditionA.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();


        }

    }
}

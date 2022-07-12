package c_DuoXianCheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @description :ABC三个线程交替打印abc  5次 【注意】记得标志位要复位
 * @date: 2022/7/12 17:31
 */
public class demo11 {
    public static void main(String[] args) {
        source1 s1 = new source1();
        new Thread(()->{
            for(int i=0;i<5;i++){
//                System.out.println("------------本次是第"+i+"轮");
                s1.printA();
            }
        },"线程A: ").start();
        new Thread(()->{
            for(int i=0;i<5;i++){
                s1.printB();
            }
        },"线程B: ").start();
        new Thread(()->{
            for(int i=0;i<5;i++){
                s1.printC();
            }
        },"线程C: ").start();
    }
}


class source1{
    ReentrantLock lock = new ReentrantLock();
    Condition ca = lock.newCondition();
    Condition cb = lock.newCondition();
    Condition cc = lock.newCondition();
    boolean fa=true;
    boolean fb=false;
    boolean fc=false;

    public void printA(){
        lock.lock();
        try {
            while (fa==false){
                try {
                    ca.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"我是A线程   我打印A----------------");
            fa=false;
            fb=true;
            cb.signal();

        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while (fb==false){
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"我是b线程   我打印b");
            fb=false;
            fc=true;
            cc.signal();

        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (fc==false){
                try {
                    cc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"我是c线程   我打印c");
            fc=false;
            fa=true;
            ca.signal();

        }finally {
            lock.unlock();
        }
    }
}

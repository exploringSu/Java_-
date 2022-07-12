package c_DuoXianCheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class demo08 {
    public static void main(String[] args) {
        source source = new source();
        new Thread(()->{
            for(int i=0;i<10;i++){
                source.xiaofei();
            }

        },"A").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                source.xiaofei();
            }
        },"B").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                source.shengchan();
            }
        },"C").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                source.shengchan();
            }
        },"D").start();
    }
}


class source{
    int product=0;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void shengchan(){
        lock.lock();
        try {
            while (product>0){
                condition.await();
            }
            product++;
            System.out.println(Thread.currentThread().getName()+"生产了"+product);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void xiaofei(){
        lock.lock();
        try {
            while (product==0){
                condition.await();
            }
            product--;
            System.out.println(Thread.currentThread().getName()+"消费了"+product);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
